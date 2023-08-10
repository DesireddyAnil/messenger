package com.protonmoney.messenger.repositories;

import com.protonmoney.messenger.models.MessageModel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageRepository {
	private static final Class<MessageModel> klass = MessageModel.class;
	private final MongoTemplate mongoTemplate;

	public MessageModel save(MessageModel messageModel){
		return mongoTemplate.save(messageModel);
	}

	public List<MessageModel> getUnread(String username){
		Query query = new Query();
		query.addCriteria(Criteria.where("to").is(username));
		query.addCriteria(Criteria.where("is_read").is(false));
		return mongoTemplate.find(query, klass);
	}

	public void markRead(List<MessageModel> messageModels){
		List<ObjectId> objectIds = messageModels.stream()
			.map(messageModel -> new ObjectId(messageModel.getId())).collect(Collectors.toList());
		Query query = new Query();
		query.addCriteria(Criteria.where("id").in(objectIds));
		Update update = new Update();
		update.set("is_read", true);
		mongoTemplate.updateMulti(query, update, klass);
	}

	public List<MessageModel> getHistory(String friend, String user){
		Query query = new Query();
		List<String> filterUsers = Lists.newArrayList();
		filterUsers.add(friend);
		filterUsers.add(user);
		query.addCriteria(Criteria.where("to").in(filterUsers));
		query.addCriteria(Criteria.where("from").in(filterUsers));
		query.with(Sort.by(Sort.Order.asc("time_stamp")));
		return mongoTemplate.find(query, klass);
	}
}
