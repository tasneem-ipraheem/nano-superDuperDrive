package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.util.MessegesUtil;

@Controller
@RequestMapping("/signup")
public class SignUpController {

	private UserService userService;

	public SignUpController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping()
	public String signup() {
		return "signup";
	}

	@PostMapping()
	public String signupUser(@ModelAttribute User user, Model model,RedirectAttributes redirectAttributes) {

		if (!userService.isUsernameAvailable(user.getUsername())) {
			model.addAttribute("signupError", MessegesUtil.SignUpMessages.FAIL_USERNAME_ALREADY_EXISTS);
			return "signup";
		}

		int rowsAdded = userService.createUser(user);

		if (rowsAdded > 0) {
//			model.addAttribute("signupSuccess", true);
//			return "signup";
			
            redirectAttributes.addFlashAttribute("signupSuccess",true);
    		return "redirect:/login";

			
		} else {
			model.addAttribute("signupError", MessegesUtil.SignUpMessages.FAIL);
			return "signup";
		}

	}
}
