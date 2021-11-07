package com.users.web.exception;

public class ApiError extends RuntimeException {
	private static final long serialVersionUID = 6192228648407403450L;
	private final int code;

	public ApiError(int code, String text) {
		super(text);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
