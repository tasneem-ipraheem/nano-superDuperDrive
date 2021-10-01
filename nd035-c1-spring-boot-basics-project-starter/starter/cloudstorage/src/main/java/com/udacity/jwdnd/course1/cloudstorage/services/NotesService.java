package com.udacity.jwdnd.course1.cloudstorage.services;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;

@Service
public class NotesService {
	
	private NoteMapper noteMapper;
	
	public NotesService(NoteMapper noteMapper) {
		this.noteMapper = noteMapper;
	}

    @PostConstruct
    public void postConstruct() {
        System.out.println("********  NotesService *********");
    }


	public void addNote(NoteForm noteForm) {
		// TODO Auto-generated method stub
		
		System.out.println("*****	NotesService : addNote  ******");
		Notes note = new Notes();
		
		note.setNoteDescription("ddd");
		note.setNoteTitle("ttttt");
		note.setUserId(1);
		
		noteMapper.insertNote(note);
		
	}
	
	

}
