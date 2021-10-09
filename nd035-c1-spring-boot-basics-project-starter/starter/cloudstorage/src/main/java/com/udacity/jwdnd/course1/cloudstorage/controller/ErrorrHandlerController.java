package com.udacity.jwdnd.course1.cloudstorage.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController

@Controller
public class ErrorrHandlerController implements ErrorController {

	@RequestMapping("/error")
	public String customError(HttpServletRequest request, Model model) {
		
		model.addAttribute("errorCode", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString());
//		System.err.println( request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString());
		return "/error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}