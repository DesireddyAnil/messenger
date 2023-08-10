package com.protonmoney.messenger.responses;

import com.protonmoney.messenger.dtos.UnreadMessage;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UnreadMessagesResponse {
	private String status;
	private String message;
	private List<UnreadMessage> data;
}
