package com.udacity.jwdnd.course1.cloudstorage.services;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
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


	public int addNote(Notes note) {
		return noteMapper.insertNote(note);
	}
	
	

}
