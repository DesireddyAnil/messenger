package com.protonmoney.messenger.repositories;

import com.protonmoney.messenger.models.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRepository {
	private static final Class<UserModel> klass = UserModel.class;
	private final MongoTemplate mongoTemplate;

	public List<UserModel> getAllUsers(){
		return mongoTemplate.findAll(klass);
	}

	public UserModel save(UserModel userModel){
		return mongoTemplate.save(userModel);
	}

	public List<UserModel> findByUserName(String username){
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		return mongoTemplate.find(query, klass);
	}
}
