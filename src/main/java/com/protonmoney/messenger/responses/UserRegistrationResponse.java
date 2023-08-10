package com.protonmoney.messenger.responses;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class UserRegistrationResponse {
	private String status;
	private String message;
}
