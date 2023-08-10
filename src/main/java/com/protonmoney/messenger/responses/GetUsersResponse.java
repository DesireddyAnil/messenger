package com.protonmoney.messenger.responses;

import lombok.Data;
import lombok.Builder;

import java.util.List;

@Data
@Builder
public class GetUsersResponse {
	private String status;
	private List<String> data;
}
