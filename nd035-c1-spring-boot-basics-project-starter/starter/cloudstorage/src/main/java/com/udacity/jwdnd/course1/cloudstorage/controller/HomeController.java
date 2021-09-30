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
    
//    @GetMapping("/logout")
//    public String logoutView(){
//        return "redirect:/login?logout";
//    }
    
  //////////////////////////////////////////////////////////////////  
//    
//    @PostMapping("/logout")
//    public String logout() {
////    	flage = homeService.logout(homeForm.getUsername(),homeForm.getPassword());
//    	flage = homeService.logout();
//    	if (flage) {
//            return "redirect:login?logedout";
//    		}
//    	else 
//    		return "home";
//
//    }
    
//
//    @PostMapping("/logout")
//    public String logout(Model model) {
//
////    	flage = homeService.logout(homeForm.getUsername(),homeForm.getPassword());
//    	flage = homeService.logout();
//    	if (flage) {
//    		
//            model.addAttribute("logout", true);
//
//            return "redirect:login?logout";
//            
//    		}
//    	else 
//    		return "home";
//
//    }
    
//    @PostMapping("/addNote")
//    public String logout() {
////    	flage = homeService.logout(homeForm.getUsername(),homeForm.getPassword());
//    	flage = homeService.logout();
//    	if (flage) {
//            return "redirect:login?logedout";
//    		}
//    	else 
//    		return "home";
//
//    }
    
    
}
