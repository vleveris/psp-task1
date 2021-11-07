package com.users.web.exception;

public class ErrorResponse {

	private final int code;
	private final String message;
	private final String url;

	public ErrorResponse(int code, String message, String url) {
		this.code = code;
		this.message = message;
		this.url = url;
	}

	public int getCode() {
		return code;

	}

	public String getMessage() {
		return message;
	}

	public String getUrl() {
		return url;
	}
}
