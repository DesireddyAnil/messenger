package com.protonmoney.messenger.controllers;

import com.protonmoney.messenger.dtos.User;
import com.protonmoney.messenger.exceptions.DuplicateUsernameException;
import com.protonmoney.messenger.responses.GetUsersResponse;
import com.protonmoney.messenger.responses.UserRegistrationResponse;
import com.protonmoney.messenger.services.UserService;
import com.protonmoney.messenger.utils.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
	private final UserService userService;

	@GetMapping("/get/users")
	public ResponseEntity<GetUsersResponse> getUsers(){
		try{
			List<String> usernames = userService.getAllUsers();
			return ResponseEntity.ok(ResponseGenerator.createGetUsersSuccessResponse(usernames));
		} catch (Exception e){
			return ResponseEntity.internalServerError().body(ResponseGenerator.createGetUsersFailureResponse());
		}
	}

	@PostMapping("/create/user")
	public ResponseEntity<UserRegistrationResponse> createUser(@RequestBody @NonNull @Valid User user){
		try{
			userService.createUser(user);
			return ResponseEntity.ok(ResponseGenerator.createUserRegistrationSuccessResponse());
		} catch (DuplicateUsernameException e){
			return ResponseEntity.badRequest().body(ResponseGenerator.createUserRegistrationFailureResponse(e.getMessage()));
		} catch (Exception e){
			return ResponseEntity.internalServerError().body(ResponseGenerator.createUserRegistrationFailureResponse(e.getMessage()));
		}
	}
}
