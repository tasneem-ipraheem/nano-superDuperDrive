package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
//		System.out.println("note = " + notes.getNoteTitle() + " : " + notes.getNoteDescription() +" , user : "+notes.getUserId());
//		
		 int rowsAdded = this.notesService.addNote(notes); 
		 
		 if (rowsAdded > 0){
	            redirectAttributes.addFlashAttribute("success",true);
	            redirectAttributes.addFlashAttribute("successMessage", "Note added successfully");
	        } else {
	            redirectAttributes.addFlashAttribute("error", true);
	            redirectAttributes.addFlashAttribute("errorMessage","add note error");
	        }

//		 return "redirect:/home#nav-notes";
		return "redirect:/home";

	}
	

	@PutMapping("/notes")
	public String editNote(Authentication authentication,  @ModelAttribute Notes notes, Model model,RedirectAttributes redirectAttributes) {

		User user = userService.getUser(authentication.getName());
		Integer userId = user.getUserId();
		notes.setUserId(userId);

//		System.out.println("*****	NotesController : editNote  ******");
//		System.out.println("notes = " + notes.getNoteTitle() + " : " + notes.getNoteDescription() +" , user : "+notes.getUserId());
		
		 int rows = this.notesService.editNote(notes); 
//		 
		 if (rows > 0){
	            redirectAttributes.addFlashAttribute("success",true);
	            redirectAttributes.addFlashAttribute("successMessage", "Note edited successfully");
	        } else {
	            redirectAttributes.addFlashAttribute("error", true);
	            redirectAttributes.addFlashAttribute("errorMessage","Edite note error");
	        }

//		return "redirect:/home#nav-notes-tab";
		 
		return "redirect:/home";

	}
	

	@DeleteMapping("/notes")
	public String deleteNote( @ModelAttribute Notes notes, RedirectAttributes redirectAttributes) {

//		System.out.println("*****	NotesController : deleteNote  ******");
//		System.out.println("noteId = " + notes.getNoteId() );
		
		 int rows = this.notesService.deleteNote( notes.getNoteId()); 
//		 
		 if (rows > 0){
	            redirectAttributes.addFlashAttribute("success",true);
	            redirectAttributes.addFlashAttribute("successMessage", "Note deleted successfully");
	        } else {
	            redirectAttributes.addFlashAttribute("error", true);
	            redirectAttributes.addFlashAttribute("errorMessage","Delete note error");
	        }

//		return "redirect:/home#nav-notes-tab";
		 
		return "redirect:/home";

	}

}
