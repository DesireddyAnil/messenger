package com.protonmoney.messenger.responses;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ChatHistoryResponse {
	private String status;
	private List<Map<String, String>> texts;
}
