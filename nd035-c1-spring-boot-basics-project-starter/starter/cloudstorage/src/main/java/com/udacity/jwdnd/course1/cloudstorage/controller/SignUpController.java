package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

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
	public String signupUser(@ModelAttribute User user, Model model) {

		if (!userService.isUsernameAvailable(user.getUsername())) {
			model.addAttribute("signupError", "Username already exists");
			return "signup";
		}

		int rowsAdded = userService.createUser(user);

		if (rowsAdded > 0) {
			model.addAttribute("signupSuccess", true);
			return "signup";
		} else {
			model.addAttribute("signupError", "There was an error signing you up. Please try again.");
			return "signup";
		}

	}
}
