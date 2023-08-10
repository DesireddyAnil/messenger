package com.protonmoney.messenger.requests;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsernameRequest {
	@NonNull
	private String username;
}
