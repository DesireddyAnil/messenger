package com.protonmoney.messenger.requests;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class UserRegistrationResponse {
	private String status;
	private String message;
}
