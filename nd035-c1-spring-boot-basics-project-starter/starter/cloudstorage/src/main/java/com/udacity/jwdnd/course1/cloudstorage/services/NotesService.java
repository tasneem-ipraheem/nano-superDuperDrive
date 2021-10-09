package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

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

	public Notes getOldNote(Notes note) {
		return noteMapper.getOldNote(note);

	}

	public int addNote(Notes note) {

		int rows = 0;

		try {
			rows = noteMapper.insertNote(note);
		} catch (Exception e) {
			if (e instanceof org.springframework.dao.DataIntegrityViolationException) {
				note.setDscrExcced(true);
//				System.out.println(e.getClass());
				System.out.println("Add Note : Note Description exceed 1000 characters");
			}
		}

		return rows;
	}

	public int editNote(Notes note) {

		int rows = 0;

		try {
			rows = noteMapper.updateNote(note);
		} catch (Exception e) {
			if (e instanceof org.springframework.dao.DataIntegrityViolationException) {
				note.setDscrExcced(true);
//				System.out.println(e.getClass());
				System.out.println("Edit Note : Note Description exceed 1000 characters");
			}
		}

		return rows;

	}

	public List<Notes> getNotesByUserId(Integer userId) {
		return noteMapper.getNotsForUserId(userId);
	}

	public int deleteNote(Integer noteId) {
		return noteMapper.deleteNote(noteId);
	}

}
