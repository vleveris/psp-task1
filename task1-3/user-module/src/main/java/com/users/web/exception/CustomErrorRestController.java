package com.users.web.exception;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

@RestController
public class CustomErrorRestController implements ErrorController {

	@Autowired
	private ErrorAttributes errorAttributes;

	@RequestMapping("/error/json")
	public ErrorResponse handleError(HttpServletRequest req) {
		ServletWebRequest webRequest = new ServletWebRequest(req);
		Map<String, Object> errors = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
		String url = (String) errors.get("path");
		int code = (int) errors.get("status");
		String message = (String) errors.get("error");
		ErrorResponse e = new ErrorResponse(code, message, url);
		return e;
	}

	public String getErrorPath() {
		return null;
	}

}