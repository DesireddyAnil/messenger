package com.protonmoney.messenger.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogoutResponse {
	private String status;
}
