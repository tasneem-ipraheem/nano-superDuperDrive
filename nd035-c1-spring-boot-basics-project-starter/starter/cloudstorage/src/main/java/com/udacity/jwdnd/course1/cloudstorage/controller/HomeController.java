package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.udacity.jwdnd.course1.cloudstorage.services.HomeService;
import com.udacity.jwdnd.course1.cloudstorage.utils.URLS;

@Controller
public class HomeController {
	private boolean flage;
    private HomeService homeService;

    public HomeController( HomeService homeService) {
        this.homeService = homeService;
    }
    
    @GetMapping("/home")
    public String getLoginPage() {
        return URLS.HOME_END_POINT;
    }
   
}
