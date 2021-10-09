package com.udacity.jwdnd.course1.cloudstorage.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//@RestController

@Controller
public class ErrorrHandlerController implements ErrorController {

	@RequestMapping("/error")
	public String customError(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		//		errorCode=0, location=/error
		
//		if ("0".equals(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString()) ){
//			redirectAttributes.addFlashAttribute("error", true);
//			redirectAttributes.addFlashAttribute("errorMessage", "The file exceeds the upload limit");
//			return "redirect:/home";
//		}

		
		model.addAttribute("errorCode", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString());
//		System.err.println( request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString());
		return "/error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}