package com.jagratichildrenvidyamandir.dto;

public class ErrorResponse {
	private String message;

	public ErrorResponse(String message) {
		this.message = message;
	}

	// getters/setters
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
