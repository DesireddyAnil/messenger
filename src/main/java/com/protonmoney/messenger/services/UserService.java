package com.protonmoney.messenger.services;

import com.protonmoney.messenger.dtos.User;
import com.protonmoney.messenger.exceptions.DuplicateUsernameException;
import com.protonmoney.messenger.models.UserModel;
import com.protonmoney.messenger.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
	private final UserRepository userRepository;

	public List<String> getAllUsers(){
		return userRepository.getAllUsers().stream().map(UserModel::getUsername).collect(Collectors.toList());
	}

	public UserModel createUser(User user) throws Exception {
		List<UserModel> users = userRepository.findByUserName(user.getUsername());
		if(users.isEmpty()){
			UserModel userModel = UserModel.builder().username(user.getUsername()).password(user.getPassword()).build();
			return userRepository.save(userModel);
		}
		throw new DuplicateUsernameException("User already exists");
	}
}
