package com.colleage.response;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	private String message;
	private int status;
	private LocalDateTime timestamp;
	private String path;
	private Map<String, String> errors;

}
