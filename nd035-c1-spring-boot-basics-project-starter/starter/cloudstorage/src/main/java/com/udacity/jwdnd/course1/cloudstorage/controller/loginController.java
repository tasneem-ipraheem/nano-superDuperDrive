package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.LoginForm;
import com.udacity.jwdnd.course1.cloudstorage.services.LoginService;
import com.udacity.jwdnd.course1.cloudstorage.utils.URLS;

@Controller
public class loginController {
	
    @GetMapping("/login")
    public String getLoginPage() {
        return URLS.LOGIN_END_POINT;
    }
    
    
    private LoginService loginService;

    public loginController(LoginService loginService) {
        this.loginService = loginService;
    }

//    @GetMapping("/home")
//    public String getHomePage(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
//        model.addAttribute("greetings", this.messageListService.getMessages());
//        return "home";
//    }

    @PostMapping("/login")
    public String addMessage(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
    	boolean flage =   loginService.login(loginForm.getUsername(),loginForm.getPassword());
//        model.addAttribute("greetings", messageListService.getMessages());
//        messageForm.setText("");
    	
    	if (flage) 
    		return URLS.HOME_END_POINT;
    	else 
    		return URLS.LOGIN_END_POINT;
    }
    

}
