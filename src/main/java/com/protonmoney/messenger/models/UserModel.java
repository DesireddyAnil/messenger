package com.protonmoney.messenger.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Data
@Document(collection = "additional_taxpayer_info")
public class UserModel {
	@Id
	private String id;
	@Field("username")
	private String userName;
	@Field("password")
	private String password;
}
