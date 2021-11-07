package com.users.web.exception;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

@Controller
public class CustomErrorController implements ErrorController {

	@Autowired
	private ErrorAttributes errorAttributes;

	public String getErrorPath() {
		return "/error"; // mandatory path
	}

	@RequestMapping("/error") // mandatory mapping
	public String handleError(HttpServletRequest req, ModelMap model) {
		ServletWebRequest servletWebRequest = new ServletWebRequest(req);
		Map<String, Object> errors = errorAttributes.getErrorAttributes(servletWebRequest,
				ErrorAttributeOptions.defaults());
		String url = (String) errors.get("path");
		int code = (int) errors.get("status");
		String message = (String) errors.get("error");
		model.put("url", url);
		model.put("code", code);
		model.put("message", message);
		return "error";
	}
}