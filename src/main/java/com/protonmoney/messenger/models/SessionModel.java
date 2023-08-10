package com.protonmoney.messenger.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Data
@Builder
@Document(collection = "sessions")
public class SessionModel {
	@Id
	private String id;
	@Field("username")
	private String username;
	@Field("is_logged_in")
	private boolean isLoggedIn;
	@Field("expiry_time_stamp")
	private Instant expiryTimeStamp;
	@Field("updated_at")
	private Instant updatedAt;
}
