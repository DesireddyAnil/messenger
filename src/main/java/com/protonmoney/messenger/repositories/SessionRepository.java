package com.protonmoney.messenger.repositories;

import com.protonmoney.messenger.models.SessionModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Objects;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SessionRepository {
	private static final Class<SessionModel> klass = SessionModel.class;
	private final MongoTemplate mongoTemplate;

	public void createOrUpdate(String username){
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		SessionModel existingSession = mongoTemplate.findOne(query, klass);
		if(Objects.nonNull(existingSession)){
			Update update = new Update();
			update.set("is_logged_in", true);
			update.set("expiry_time_stamp", Instant.now().plusSeconds(60*30));
			update.set("updated_at", Instant.now());
			mongoTemplate.updateFirst(query, update, klass);
		}
		mongoTemplate.save(SessionModel.builder()
			.username(username)
			.isLoggedIn(true)
			.expiryTimeStamp(Instant.now().plusSeconds(60*30))
			.updatedAt(Instant.now())
			.build());
	}

	public void logout(String username){
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		SessionModel existingSession = mongoTemplate.findOne(query, klass);
		if(Objects.nonNull(existingSession)){
			Update update = new Update();
			update.set("is_logged_in", false);
			mongoTemplate.updateFirst(query, update, klass);
		}
	}

	public SessionModel find(String username){
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		return mongoTemplate.findOne(query, klass);
	}
}
