package com.protonmoney.messenger.dtos;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class Message {
	private String from;
	private String to;
	private String text;
}
