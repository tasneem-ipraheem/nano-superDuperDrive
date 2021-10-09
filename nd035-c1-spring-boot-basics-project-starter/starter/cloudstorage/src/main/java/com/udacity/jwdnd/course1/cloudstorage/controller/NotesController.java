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
import com.udacity.jwdnd.course1.cloudstorage.util.MessegesUtil;

@Controller
public class NotesController {
	private NotesService notesService;
	private UserService userService;

	public NotesController(NotesService noteshomeService, UserService userService) {
		this.notesService = noteshomeService;
		this.userService = userService;
	}

	@PostMapping("/notes")
	public String createNote(Authentication authentication, @ModelAttribute Notes notes, Model model,
			RedirectAttributes redirectAttributes) {

		Notes old = this.notesService.getOldNote(notes);

		if (old != null) {
			redirectAttributes.addFlashAttribute("error", true);
			redirectAttributes.addFlashAttribute("errorMessage", MessegesUtil.NoteMessages.FAIL_ADD_NOTE_ALREADY_EXIST);
			return "redirect:/home";
		}

		User user = userService.getUser(authentication.getName());
		Integer userId = user.getUserId();
		notes.setUserId(userId);
		int rowsAdded = 0;

//		if (notes.getNoteDescription().length() > 1000) {
//			redirectAttributes.addFlashAttribute("error", true);
//			redirectAttributes.addFlashAttribute("errorMessage",
//					"Note can't be saved as description exceed 1000 characters");
//			return "redirect:/home";
//		}

		rowsAdded = this.notesService.addNote(notes);

		if (rowsAdded > 0) {
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("successMessage", MessegesUtil.NoteMessages.SUCCESS_ADD);
		} else if (notes.isDscrExcced()) {
			redirectAttributes.addFlashAttribute("error", true);
			redirectAttributes.addFlashAttribute("errorMessage",
					MessegesUtil.NoteMessages.FAIL_ADD_EXCEED_DESCRIPTION_LIMIT);
		} else {
			redirectAttributes.addFlashAttribute("error", true);
			redirectAttributes.addFlashAttribute("errorMessage", MessegesUtil.NoteMessages.FAIL_ADD_DATABASE);
		}

//		 return "redirect:/home#nav-notes";
		return "redirect:/home";

	}

	@PutMapping("/notes")
	public String editNote(Authentication authentication, @ModelAttribute Notes notes, Model model,
			RedirectAttributes redirectAttributes) {

		Notes old = this.notesService.getOldNote(notes);

		if (old != null) {
			redirectAttributes.addFlashAttribute("error", true);
			redirectAttributes.addFlashAttribute("errorMessage", MessegesUtil.NoteMessages.FAIL_EDIT_NOTE_ALREADY_EXIST);
			return "redirect:/home";
		}
		
		User user = userService.getUser(authentication.getName());
		Integer userId = user.getUserId();
		notes.setUserId(userId);
		int rows = 0;

//		if (notes.getNoteDescription().length() > 1000) {
//			redirectAttributes.addFlashAttribute("error", true);
//			redirectAttributes.addFlashAttribute("errorMessage",
//					"Note can't be saved as description exceed 1000 characters");
//			return "redirect:/home";
//		}

		rows = this.notesService.editNote(notes);

		if (rows > 0) {
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("successMessage", MessegesUtil.NoteMessages.SUCCESS_EDIT);
		} else if (notes.isDscrExcced()) {
			redirectAttributes.addFlashAttribute("error", true);
			redirectAttributes.addFlashAttribute("errorMessage",
					MessegesUtil.NoteMessages.FAIL_EDIT_EXCEED_DESCRIPTION_LIMIT);
		} else {
			redirectAttributes.addFlashAttribute("error", true);
			redirectAttributes.addFlashAttribute("errorMessage", MessegesUtil.NoteMessages.FAIL_EDIT_DATABASE);
		}

//		return "redirect:/home#nav-notes-tab";

		return "redirect:/home";

	}

	@DeleteMapping("/notes")
	public String deleteNote(@ModelAttribute Notes notes, RedirectAttributes redirectAttributes) {

//		System.out.println("*****	NotesController : deleteNote  ******");
//		System.out.println("noteId = " + notes.getNoteId() );

		int rows = this.notesService.deleteNote(notes.getNoteId());
//		 
		if (rows > 0) {
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("successMessage", MessegesUtil.NoteMessages.SUCCESS_DELETE);
		} else {
			redirectAttributes.addFlashAttribute("error", true);
			redirectAttributes.addFlashAttribute("errorMessage", MessegesUtil.NoteMessages.FAIL_DELETE_DATABASE);
		}

//		return "redirect:/home#nav-notes-tab";

		return "redirect:/home";

	}

}
