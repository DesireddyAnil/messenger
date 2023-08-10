package com.protonmoney.messenger.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Data
@Builder
@Document(collection = "messages")
public class MessageModel {
	@Id
	private String id;
	@Field("text")
	private String text;
	@Field("from")
	private String from;
	@Field("to")
	private String to;
	@Field("time_stamp")
	private Instant timeStamp;
	@Field("is_read")
	private boolean isRead;
}
