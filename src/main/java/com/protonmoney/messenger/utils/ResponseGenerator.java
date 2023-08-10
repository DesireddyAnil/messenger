package com.protonmoney.messenger.utils;

import com.protonmoney.messenger.dtos.UnreadMessage;
import com.protonmoney.messenger.responses.ChatHistoryResponse;
import com.protonmoney.messenger.responses.GetUsersResponse;
import com.protonmoney.messenger.responses.LoginResponse;
import com.protonmoney.messenger.responses.LogoutResponse;
import com.protonmoney.messenger.responses.SendMessageResponse;
import com.protonmoney.messenger.responses.UnreadMessagesResponse;
import com.protonmoney.messenger.responses.UserRegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ResponseGenerator {

	public static GetUsersResponse createGetUsersSuccessResponse(List<String> usernames){
		return GetUsersResponse.builder().status("SUCCESS").data(usernames).build();
	}

	public static GetUsersResponse createGetUsersFailureResponse(){
		return GetUsersResponse.builder().status("FAILURE").build();
	}

	public static UserRegistrationResponse createUserRegistrationSuccessResponse(){
		return UserRegistrationResponse.builder().status("SUCCESS").build();
	}

	public static UserRegistrationResponse createUserRegistrationFailureResponse(String message){
		return UserRegistrationResponse.builder().status("FAILURE").message(message).build();
	}

	public static UnreadMessagesResponse createUnreadMessagesSuccessResponse(List<UnreadMessage> unreadMessages){
		String message = "You have message(s)";
		if(unreadMessages.isEmpty()){
			message = "You have no message";
		}
		return UnreadMessagesResponse.builder().status("SUCCESS").message(message).data(unreadMessages).build();
	}

	public static UnreadMessagesResponse createUnreadMessagesFailureResponse(String message){
		return UnreadMessagesResponse.builder().status("FAILURE").message(message).build();
	}

	public static SendMessageResponse createSendMessageSuccessResponse(){
		return SendMessageResponse.builder().status("SUCCESS").build();
	}

	public static SendMessageResponse createSendMessageFailureResponse(String message){
		return SendMessageResponse.builder().status("FAILURE").message(message).build();
	}

	public static ChatHistoryResponse createChatHistorySuccessResponse(List<Map<String, String>> texts){
		return ChatHistoryResponse.builder().status("SUCCESS").texts(texts).build();
	}

	public static ChatHistoryResponse createChatHistoryFailureResponse(){
		return ChatHistoryResponse.builder().status("FAILURE").build();
	}

	public static LogoutResponse createLogoutSuccessResponse(){
		return LogoutResponse.builder().status("SUCCESS").build();
	}

	public static LogoutResponse createLogoutFailureResponse(){
		return LogoutResponse.builder().status("FAILURE").build();
	}

	public static LoginResponse createLoginSuccessResponse(){
		return LoginResponse.builder().status("SUCCESS").build();
	}

	public static LoginResponse createLoginFailureResponse(String message){
		return LoginResponse.builder().status("FAILURE").message(message).build();
	}
}
