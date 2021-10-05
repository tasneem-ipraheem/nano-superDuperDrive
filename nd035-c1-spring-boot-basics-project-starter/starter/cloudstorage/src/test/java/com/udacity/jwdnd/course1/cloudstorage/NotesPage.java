package com.udacity.jwdnd.course1.cloudstorage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

	@FindBy(css = "#noteSubmit")
	private WebElement addSubmit;

	@FindBy(css = "#edit-note-title")
	private WebElement editTitleField;

	@FindBy(css = "#edit-note-description")
	private WebElement editDescriptionField;

	@FindBy(css = "#editNoteSubmit")
	private WebElement editSubmit;

	@FindBy(css = "#deleteNoteSubmit")
	private WebElement deleteSubmit;

	private  WebDriver driver;
	
    public NotesPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.driver=webDriver;
    }

//TODO Check how tofind hidden elements
    /*
     *	this.addNoteButton.click();  -->  element should be in the [this page]
	 *	https://www.edureka.co/community/94723/how-resolve-this-error-element-not-interactable-any-solution
	 *
     * */

	public void addNote(String title, String description) {
//		openNoteTabJS();

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", notesTabField);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", addNoteButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + title + "';", noteTitleField);
		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + description + "';", noteDescriptionField);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", addSubmit);
		
		
//		this.notesTabField.click();
//		openNoteTabJS();
//		this.noteTitleField.sendKeys(title);
//		this.noteDescriptionField.sendKeys(description);
//		this.addSubmit.click();
//		driver.findElement(By.linkText("Save changes")).click(); 

	}

	public void editNote(String title, String desc) {
//		this.notesTabField.click();

		openNoteTabJS();

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + title + "';", editTitleField);
		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + desc + "';", editDescriptionField);
//		this.editTitleField.sendKeys(title);
//		this.editDescriptionField.sendKeys(desc);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", editSubmit);
	}

	public void deleteNote() {
//		this.notesTabField.click();

		openNoteTabJS();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteSubmit);

	}

	public boolean hasNotes() {
		this.notesTabField.click();

		
		List<WebElement> notesList = notesTable.findElements(By.id("title-col"));
		return notesList.size() != 0;
	}

	public void openNoteTabJS() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", notesTabField);
//		this.notesTabField.click();

		
	}
}
