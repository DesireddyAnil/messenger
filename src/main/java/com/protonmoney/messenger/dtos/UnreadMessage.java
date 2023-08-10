package com.protonmoney.messenger.dtos;

import lombok.Data;
import lombok.Builder;

import java.util.List;

@Data
@Builder
public class UnreadMessage {
	private String username;
	private List<String> texts;
}
