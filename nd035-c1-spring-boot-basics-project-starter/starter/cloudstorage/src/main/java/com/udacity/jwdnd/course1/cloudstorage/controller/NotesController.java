package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;

@Controller
public class NotesController {
    private NotesService notesService;

    public NotesController( NotesService noteshomeService) {
        this.notesService = noteshomeService;
    }
    
    @PostMapping("/notes")
//    public String createNote(Authentication authentication, @ModelAttribute NoteForm noteForm, Model model) {
    public String createNote(Authentication authentication, @ModelAttribute Notes notes, Model model) {

    	System.out.println("*****	NotesController : createNote  ******");

//    	User currentUser = (User) authentication.getPrincipal();
//    	int id = currentUser.getUserId();
//    	 noteForm.setUserId(id);
    	
    	System.out.println("*****	NotesController : createNote  ******");
		System.out.println("noteForm = " +notes.getNoteTitle() +" : "+ notes.getNoteDescription() );
      
    	 
    	 
    	 
    	 
    	 
//         model.addAttribute("notelist", this.messageService.getChatMessages());
    	  return "redirect:/home";
    	
    	
//    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 

//    	String userName = authentication.getPrincipal().;


    }
    
//    
//    @PostMapping("/notes")
//    public String createNote(@ModelAttribute("noteForm") NoteForm noteForm) {
//    	System.out.println("*****	NotesController : createNote  ******");
//		System.out.println("noteForm = " +noteForm.getNoteTitle() +" : "+ noteForm.getNoteDescription() );
//        return "home";
//    }
    
    
//
//  @GetMapping("/notes")
//  public String loginView() {
//      return "notes";
//  }
    
    /*
   
    @PostMapping("/notes")
    public String createNote(Authentication authentication, Model model) {
		System.out.println("*****	NotesController : createNote  ******");

		
		this.notesService.addNote(null);
//		 noteForm.setNoteDiscription("");
//		 noteForm.setNoteTitle("");
    	 
//         model.addAttribute("notelist", this.messageService.getChatMessages());
         return "home";
    	
    	
//    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
    
//    	String userName = authentication.getPrincipal().;
    
    
    }
    */
   
}

/*
@PostMapping("/notes")
public String createNote(Authentication authentication, @ModelAttribute("noteForm") NoteForm noteForm, Model model) {
	System.out.println("*****	NotesController : createNote  ******");

//	User currentUser = (User) authentication.getPrincipal();
//	int id = currentUser.getUserId();
//	 noteForm.setUserId(id);
	
	
	 this.notesService.addNote(noteForm);
	 noteForm.setNoteDiscription("");
	 noteForm.setNoteTitle("");
	 
	 
	 
	 
	 
//     model.addAttribute("notelist", this.messageService.getChatMessages());
     return "home";
	
	
//	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 

//	String userName = authentication.getPrincipal().;


}
*/
