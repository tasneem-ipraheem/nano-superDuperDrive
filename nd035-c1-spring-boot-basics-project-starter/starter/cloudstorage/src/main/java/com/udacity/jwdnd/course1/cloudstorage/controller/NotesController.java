package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
public class NotesController {
	private NotesService notesService;
	private UserService userService;

	public NotesController(NotesService noteshomeService, UserService userService) {
		this.notesService = noteshomeService;
		this.userService = userService;
	}

	@PostMapping("/notes")
	public String createNote(Authentication authentication, @ModelAttribute Notes notes, Model model,RedirectAttributes redirectAttributes) {

		User user = userService.getUser(authentication.getName());
		Integer userId = user.getUserId();
		notes.setUserId(userId);

//		System.out.println("*****	NotesController : createNote  ******");
//		System.out.println("noteForm = " + notes.getNoteTitle() + " : " + notes.getNoteDescription() +" , user : "+notes.getUserId());
		
		 int rowsAdded = this.notesService.addNote(notes); 
		 
		 if (rowsAdded >= 0){
	            redirectAttributes.addFlashAttribute("success",true);
	            redirectAttributes.addFlashAttribute("successMessage", "You successfully added a new note");
	        } else {
	            redirectAttributes.addFlashAttribute("error", true);
	            redirectAttributes.addFlashAttribute("errorMessage","There was an error for adding a note. Please try again");
	        }

		 
		return "redirect:/home";

	}

}
