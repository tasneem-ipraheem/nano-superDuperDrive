package com.udacity.jwdnd.course1.cloudstorage.util;

public class MessegesUtil {

	public final class FileMessages {
		public static final String FAIL_NO_SELECTED_FILE = "Select file to upload !";
		public static final String FAIL_UPLOAD_EXIST_FILE = "File already exist !";

		public static final String SUCCESS_UPLOAD = "File added successfully";
		public static final String SUCCESS_DELETED = "File Deleted successfully";

		public static final String FAIL_EXCEED_SIZE = "The File exceeds the upload limit 5M !!";
		public static final String FAIL_MULTIPARTEXCEPTION = "The file face MultipartException";
	}

	public final class NoteMessages {
		public static final String FAIL_ADD_EXCEED_DESCRIPTION_LIMIT = "Note can't be saved as description exceed 1000 characters";
		public static final String FAIL_EDIT_EXCEED_DESCRIPTION_LIMIT = "Note can't be edited as description exceed 1000 characters";

		public static final String FAIL_ADD_DATABASE = "Add note Database error";
		public static final String FAIL_EDIT_DATABASE = "Edite note Database error";
		public static final String FAIL_DELETE_DATABASE = "Delete note error";
		public static final String FAIL_EDIT_NOTE_ALREADY_EXIST = "Edit Fail, Note Already Exist!";
		public static final String FAIL_ADD_NOTE_ALREADY_EXIST = "Add Fail, Note Already Exist!";

		public static final String SUCCESS_ADD = "Note added successfully";
		public static final String SUCCESS_EDIT = "Note edited successfully";
		public static final String SUCCESS_DELETE = "Note deleted successfully";

	}

	public final class CredentialMessages {
		public static final String SUCCESS_ADD = "Credential added successfully";
		public static final String SUCCESS_EDIT = "Credential updated Successfully";
		public static final String SUCCESS_DELETE = "Credential deleted successfully";
		
		public static final String FAIL_ADD_DATABASE = "Credential add fail";
		public static final String FAIL_EDIT_DATABASE = "Credential update Fail";
		public static final String FAIL_DELETE_DATABASE ="Credential delete Fail";
	}

	public final class ErrorControllerMessages {
		public static final String FAIL_INVALID_PAGE="The link you followed may be broken, or the page may have been removed";
		public static final String FAIL_INTERNAL_ERROR = "Server or DataBase issue";
	}
	
	public final class SignUpMessages {
		public static final String FAIL_USERNAME_ALREADY_EXISTS = "Username already exists";
		public static final String FAIL = "There was an error signing you up. Please try again.";
	}
}
