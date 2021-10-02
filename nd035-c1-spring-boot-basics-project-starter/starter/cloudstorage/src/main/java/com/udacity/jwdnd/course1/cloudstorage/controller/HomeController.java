package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.HomeService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
public class HomeController {
	private boolean flage;
	private HomeService homeService;
	private NotesService noteService;
	private UserService userService;
	private EncryptionService encryptionService;
	private CredentialService credentialService;

	public HomeController(CredentialService credentialService, NotesService noteService, UserService userService, EncryptionService encryptionService, HomeService homeService) {
        this.homeService = homeService;
        this.noteService = noteService;
        this.userService = userService;
        this.encryptionService = encryptionService;
        this.credentialService = credentialService;
	}
	
	

	@GetMapping("/home")
	public String getLoginPage( Authentication authentication, Model model){//, @ModelAttribute List<Notes> noteList) {
		User user = userService.getUser(authentication.getName());

		List<Notes> noteList = noteService.getNotesByUserId(user.getUserId());
		model.addAttribute("noteList", noteList);
		
		
		List<Credential> credentialList =  credentialService.getCredentials(user.getUserId());
		model.addAttribute("credentialList", credentialList);
		
		
		 model.addAttribute("encryptionService",encryptionService);

		return "home";
	}

}
