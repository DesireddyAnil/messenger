package com.protonmoney.messenger.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class User {
	@NonNull
	private String username;
	@NonNull
	private String password;
}
