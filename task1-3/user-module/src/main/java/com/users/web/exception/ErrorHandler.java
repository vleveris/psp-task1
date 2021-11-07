package com.users.web.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorHandler {
	@ExceptionHandler(Exception.class)
	public String onException(HttpServletRequest req, Exception ex, ModelMap model) {
		model.put("code", 500);
		model.put("message", ex.getMessage());
		model.put("url", req.getRequestURL());
		return "error";
	}

	@ExceptionHandler(ApiError.class)
	public ModelAndView onApiException(HttpServletRequest req, ApiError e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("code", e.getCode());
		mv.addObject("message", e.getMessage());
		mv.addObject("url", req.getRequestURL());
		mv.setViewName("error");
		return mv;
	}
}
