package com.protonmoney.messenger.controllers;

import com.protonmoney.messenger.dtos.User;
import com.protonmoney.messenger.requests.UsernameRequest;
import com.protonmoney.messenger.responses.LoginResponse;
import com.protonmoney.messenger.responses.LogoutResponse;
import com.protonmoney.messenger.services.SessionService;
import com.protonmoney.messenger.utils.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SessionController {
	private final SessionService sessionService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody @NonNull @Valid User user){
		try{
			sessionService.createSession(user);
			return ResponseEntity.ok(ResponseGenerator.createLoginSuccessResponse());
		} catch (Exception e){
			return ResponseEntity.internalServerError().body(ResponseGenerator.createLoginFailureResponse(e.getMessage()));
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<LogoutResponse> logout(@RequestBody @NonNull @Valid UsernameRequest usernameRequest){
		try{
			sessionService.logout(usernameRequest.getUsername());
			return ResponseEntity.ok(ResponseGenerator.createLogoutSuccessResponse());
		} catch (Exception e){
			return ResponseEntity.internalServerError().body(ResponseGenerator.createLogoutFailureResponse());
		}
	}

}
