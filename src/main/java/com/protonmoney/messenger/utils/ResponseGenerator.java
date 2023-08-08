package com.protonmoney.messenger.utils;

import com.protonmoney.messenger.requests.GetUsersResponse;
import com.protonmoney.messenger.requests.UserRegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ResponseGenerator {

	public static GetUsersResponse createGetUsersSuccessResponse(List<String> userNames){
		return GetUsersResponse.builder().status("SUCCESS").data(userNames).build();
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

}
