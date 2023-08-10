package com.protonmoney.messenger.services;

import com.protonmoney.messenger.dtos.Message;
import com.protonmoney.messenger.dtos.UnreadMessage;
import com.protonmoney.messenger.exceptions.NotFoundException;
import com.protonmoney.messenger.models.MessageModel;
import com.protonmoney.messenger.models.UserModel;
import com.protonmoney.messenger.repositories.MessageRepository;
import com.protonmoney.messenger.repositories.UserRepository;
import com.protonmoney.messenger.requests.ChatHistoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessagingService {
	private final SessionService sessionService;
	private final MessageRepository messageRepository;
	private final UserRepository userRepository;

	public void sendMessage(Message message) throws Exception {
		sessionService.throwExceptionIfSessionIsNotActive(message.getFrom());
		List<UserModel> userModels = userRepository.findByUserName(message.getTo());
		if(userModels.isEmpty()){
			throw new NotFoundException("User does not exist");
		}
		MessageModel messageModel = MessageModel.builder()
			.from(message.getFrom())
			.to(message.getTo())
			.timeStamp(Instant.now())
			.text(message.getText())
			.isRead(false)
			.build();
		messageRepository.save(messageModel);
	}

	public List<UnreadMessage> getUnreadMessages(String username) throws Exception {
		sessionService.throwExceptionIfSessionIsNotActive(username);
		List<MessageModel> messageModels =  messageRepository.getUnread(username);
		Map<String, List<String>> messagesMap = new HashMap<>();
		List<UnreadMessage> unreadMessages = new ArrayList<>();
		for(MessageModel messageModel: messageModels){
			if(messagesMap.containsKey(messageModel.getFrom())){
				messagesMap.get(messageModel.getFrom()).add(messageModel.getText());
			}else{
				List<String> messages = new ArrayList<>();
				messages.add(messageModel.getText());
				messagesMap.put(messageModel.getFrom(), messages);
			}
		}
		for(Map.Entry<String, List<String>> entry: messagesMap.entrySet()){
			unreadMessages.add(UnreadMessage.builder()
				.username(entry.getKey())
				.texts(entry.getValue())
				.build());
		}
		messageRepository.markRead(messageModels);
		return unreadMessages;
	}

	public List<Map<String, String>> getHistory(ChatHistoryRequest chatHistoryRequest) throws Exception {
		sessionService.throwExceptionIfSessionIsNotActive(chatHistoryRequest.getUser());
		List<Map<String, String>> texts = new ArrayList<>();
		List<MessageModel> messageModels = messageRepository.getHistory(chatHistoryRequest.getFriend(), chatHistoryRequest.getUser());
		for(MessageModel messageModel: messageModels){
			texts.add(Map.of(messageModel.getFrom(), messageModel.getText()));
		}
		return texts;
	}

}
