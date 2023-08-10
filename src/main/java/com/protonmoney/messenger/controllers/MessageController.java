package com.protonmoney.messenger.controllers;

import com.protonmoney.messenger.dtos.Message;
import com.protonmoney.messenger.exceptions.NotFoundException;
import com.protonmoney.messenger.exceptions.UnAuthorizedException;
import com.protonmoney.messenger.requests.ChatHistoryRequest;
import com.protonmoney.messenger.requests.UsernameRequest;
import com.protonmoney.messenger.responses.ChatHistoryResponse;
import com.protonmoney.messenger.responses.SendMessageResponse;
import com.protonmoney.messenger.responses.UnreadMessagesResponse;
import com.protonmoney.messenger.services.MessagingService;
import com.protonmoney.messenger.utils.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageController {
	private final MessagingService messagingService;

	@GetMapping("/get/unread")
	public ResponseEntity<UnreadMessagesResponse> getUnread(@RequestBody UsernameRequest usernameRequest){
		try{
			return ResponseEntity.ok(ResponseGenerator.createUnreadMessagesSuccessResponse(messagingService.getUnreadMessages(usernameRequest.getUsername())));
		} catch (UnAuthorizedException e){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(ResponseGenerator.createUnreadMessagesFailureResponse(e.getMessage()));
		} catch (Exception e){
			return ResponseEntity.internalServerError().body(ResponseGenerator.createUnreadMessagesFailureResponse(e.getMessage()));
		}
	}

	@PostMapping("/send/text/user")
	public ResponseEntity<SendMessageResponse> sendMessage(@RequestBody @NonNull @Valid Message message){
		try{
			messagingService.sendMessage(message);
			return ResponseEntity.ok(ResponseGenerator.createSendMessageSuccessResponse());
		} catch (UnAuthorizedException e){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(ResponseGenerator.createSendMessageFailureResponse(e.getMessage()));
		} catch (NotFoundException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(ResponseGenerator.createSendMessageFailureResponse(e.getMessage()));
		} catch (Exception e){
			return ResponseEntity.badRequest().body(ResponseGenerator.createSendMessageFailureResponse(e.getMessage()));
		}
	}

	@GetMapping("/get/history")
	public ResponseEntity<ChatHistoryResponse> getHistory(@RequestBody @NonNull @Valid ChatHistoryRequest chatHistoryRequest){
		try{
			return ResponseEntity.ok(ResponseGenerator.createChatHistorySuccessResponse(messagingService.getHistory(chatHistoryRequest)));
		} catch (UnAuthorizedException e){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(ResponseGenerator.createChatHistoryFailureResponse());
		} catch (Exception e){
			return ResponseEntity.internalServerError().body(ResponseGenerator.createChatHistoryFailureResponse());
		}
	}



}
