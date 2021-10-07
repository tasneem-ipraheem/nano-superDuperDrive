package com.udacity.jwdnd.course1.cloudstorage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotesPage {
	@FindBy(css = "#nav-notes-tab")
	private WebElement notesTabField;

	@FindBy(css = "#userTable")
	private WebElement notesTable;

	@FindBy(css = "#add-note-button")
	private WebElement addNoteButton;

	@FindBy(css = "#edit-note-button")
	private WebElement editButton;

	@FindBy(css = "#delete-note-button")
	private WebElement deleteButton;

	@FindBy(css = "#note-id")
	private WebElement noteIdField;

	@FindBy(css = "#note-title")
	private WebElement noteTitleField;

	@FindBy(css = "#note-description")
	private WebElement noteDescriptionField;

	@FindBy(css = "#note-submit")
	private WebElement addSubmit;

	@FindBy(css = "#edit-note-title")
	private WebElement editTitleField;

	@FindBy(css = "#edit-note-description")
	private WebElement editDescriptionField;

	@FindBy(css = "#editNoteSubmit")
	private WebElement editSubmit;

	@FindBy(css = "#deleteNoteSubmit")
	private WebElement deleteSubmit;

	private static  WebDriver driver;
	
	
	
	String clickStr = "arguments[0].click();";
	String valueStr = "arguments[0].value='" ;
	
	JavascriptExecutor executer;
	WebDriverWait wait ;
	
	
    public NotesPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        driver=webDriver;
    	executer =(JavascriptExecutor) driver;
    	wait = new WebDriverWait (driver, 30);
    }
    


//TODO Check how to find hidden elements
    /*
     *	this.addNoteButton.click();  -->  element should be in the [this page]
	 *	https://www.edureka.co/community/94723/how-resolve-this-error-element-not-interactable-any-solution
	 *
     * */

	public void addNote(String title, String description) {
		
		this.notesTabField.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(addNoteButton));
		this.addNoteButton.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(noteTitleField));
		this.noteTitleField.sendKeys(title);

		wait.until(ExpectedConditions.elementToBeClickable(noteDescriptionField));
		this.noteDescriptionField.sendKeys(description);
		
		executer.executeScript(clickStr, addSubmit);

	}

	public void editNote(String title, String description) {
//		
		this.notesTabField.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(editButton));
		this.editButton.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(editTitleField));
		this.editTitleField.clear();
		this.editTitleField.sendKeys(title);

		wait.until(ExpectedConditions.elementToBeClickable(editDescriptionField));
		this.editDescriptionField.clear();
		this.editDescriptionField.sendKeys(description);
		
		executer.executeScript(clickStr, editSubmit);
	}

	public void deleteNote() {
		
		this.notesTabField.click();
		wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
		this.deleteButton.click();
		executer.executeScript(clickStr, deleteSubmit);

	}
	
	

	public WebElement getNotesTabField() {
		return notesTabField;
	}



	public int getNotesTableSize() {
		this.notesTabField.click();

		
		List<WebElement> notesList = notesTable.findElements(By.id("title-col"));
		return notesList.size() ;
	}

//	public void openNoteTab() {
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", notesTabField);
////		this.notesTabField.click();
//
//		
//	}
}
