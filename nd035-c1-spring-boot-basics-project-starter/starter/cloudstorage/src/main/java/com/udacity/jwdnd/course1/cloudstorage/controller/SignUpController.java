package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.SignUpForm;
import com.udacity.jwdnd.course1.cloudstorage.services.SignUpService;
import com.udacity.jwdnd.course1.cloudstorage.utils.URLS;

@Controller
public class SignUpController {
	private boolean flage;
    private SignUpService signUpService;

    public SignUpController( SignUpService signUpService) {
        this.signUpService = signUpService;
    }
    
    @GetMapping("/signup")
    public String getLoginPage() {
        return URLS.SIGNUP_END_POINT;
    }
    

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("signUpForm") SignUpForm signUpForm, Model model) {
    	flage = signUpService.signUp(signUpForm.getUsername(),signUpForm.getPassword()
    			,signUpForm.getFirstName(),signUpForm.getLastName());
    	
    	if (flage) {
            model.addAttribute("flage", flage);
            return "redirect:signup?success";
    		}
    	else 
    		return "redirect:signup?fail";

    }
    
}
