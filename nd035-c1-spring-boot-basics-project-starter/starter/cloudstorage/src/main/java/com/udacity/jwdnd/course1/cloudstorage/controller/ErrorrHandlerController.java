package com.udacity.jwdnd.course1.cloudstorage.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.util.MessegesUtil;

//@RestController

@Controller
public class ErrorrHandlerController implements ErrorController {

	@RequestMapping("/error")
	public String customError(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {

		String code = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();
		model.addAttribute("errorCode", code);
		
		int range = Integer.parseInt(code);
		if (range >= 400 && range <500) {
			model.addAttribute("errorTxt", MessegesUtil.ErrorControllerMessages.FAIL_INVALID_PAGE);


		}else {
			model.addAttribute("errorTxt", MessegesUtil.ErrorControllerMessages.FAIL_INTERNAL_ERROR);

		}
		return "/error";

	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}