package com.udacity.jwdnd.course1.cloudstorage.model;

public class NoteForm {
	private String noteTitle;
	private String noteDescription;
	private int userId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNoteTitle() {
		return noteTitle;
	}
	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}
	public String getNoteDescription() {
		return noteDescription;
	}
	public void setNoteDescription(String noteDescription) {
		this.noteDescription = noteDescription;
	}
	@Override
	public String toString() {
		return "NoteForm [noteTitle=" + noteTitle + ", noteDescription=" + noteDescription + ", userId=" + userId + "]";
	}
	
	
}
