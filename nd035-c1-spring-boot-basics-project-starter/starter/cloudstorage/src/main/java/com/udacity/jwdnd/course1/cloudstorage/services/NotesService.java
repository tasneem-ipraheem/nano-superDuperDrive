package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

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

//    @PostConstruct
//    public void postConstruct() {
//    }

	public int addNote(Notes note) {
		return noteMapper.insertNote(note);
	}
	
	public int editNote(Notes note) {
		return noteMapper.updateNote(note);
	}

	public List<Notes> getNotesByUserId(Integer userId) {
		 return noteMapper.getNotsForUserId(userId);		
	}

	public int deleteNote(Integer noteId) {
		 return noteMapper.deleteNote(noteId);
	}
	
	

}
