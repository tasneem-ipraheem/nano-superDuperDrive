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
public class LoginController {
	boolean flage = true ;
	
    @GetMapping("/login")
    public String getLoginPage() {
        return URLS.LOGIN_END_POINT;
    }
    
    
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

//    @GetMapping("/home")
//    public String getHomePage(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
//        model.addAttribute("greetings", this.messageListService.getMessages());
//        return "home";
//    }

    @PostMapping("/login")
    public String addMessage(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
    	 flage = loginService.login(loginForm.getUsername(),loginForm.getPassword());
//        messageForm.setText("");
    	
    	if (flage) {
            model.addAttribute("flage", flage);
    		return URLS.HOME_END_POINT;
    		}
    	else 
    		return URLS.LOGIN_END_POINT;
    }
    

}
