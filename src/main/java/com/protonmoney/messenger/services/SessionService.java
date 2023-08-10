package com.protonmoney.messenger.services;

import com.protonmoney.messenger.dtos.User;
import com.protonmoney.messenger.exceptions.RequestFailedException;
import com.protonmoney.messenger.exceptions.UnAuthorizedException;
import com.protonmoney.messenger.models.SessionModel;
import com.protonmoney.messenger.models.UserModel;
import com.protonmoney.messenger.repositories.SessionRepository;
import com.protonmoney.messenger.repositories.UserRepository;
import com.protonmoney.messenger.requests.UsernameRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SessionService {
	private final SessionRepository sessionRepository;
	private final UserRepository userRepository;

	public void createSession(User user) throws Exception {
		List<UserModel> userModels = userRepository.findByUserName(user.getUsername());
		if(!userModels.isEmpty()){
			UserModel userModel = userModels.get(0);
			if(userModel.getPassword().equals(user.getPassword())){
				sessionRepository.createOrUpdate(user.getUsername());
				return;
			}
		}
		throw new RequestFailedException("Failed to create session");
	}

	public void logout(String username){
		sessionRepository.logout(username);
	}

	private boolean isSessionActive(String username){
		SessionModel sessionModel = sessionRepository.find(username);
		if(Objects.nonNull(sessionModel)){
			return sessionModel.isLoggedIn() && sessionModel.getExpiryTimeStamp().isAfter(Instant.now());
		}
		return false;
	}

	public void throwExceptionIfSessionIsNotActive(String userName) throws UnAuthorizedException {
		if(!isSessionActive(userName)){
			throw new UnAuthorizedException("Session is not active");
		}
	}

}
