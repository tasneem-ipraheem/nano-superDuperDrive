package com.udacity.jwdnd.course1.cloudstorage;

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

	/*********** Add ***********/
	@FindBy(css = "#add-note-button")
	private WebElement addNoteButton;

	@FindBy(css = "#note-id")
	private WebElement noteIdField;

	@FindBy(css = "#note-title")
	private WebElement noteTitleField;

	@FindBy(css = "#note-description")
	private WebElement noteDescriptionField;

	@FindBy(css = "#note-submit")
	private WebElement addSubmit;

	/*********** Edit ***********/
	@FindBy(css = "#edit-note-button")
	private WebElement editButton;

	@FindBy(css = "#edit-note-title")
	private WebElement editTitleField;

	@FindBy(css = "#edit-note-description")
	private WebElement editDescriptionField;

	@FindBy(css = "#editNoteSubmit")
	private WebElement editSubmit;

	/*********** Delete ***********/
	@FindBy(css = "#delete-note-button")
	private WebElement deleteButton;

	@FindBy(css = "#deleteNoteSubmit")
	private WebElement deleteSubmit;

	@FindBy(css = "#nav-notes-tab")
	private WebElement notesTabField;

	@FindBy(css = "#notes-table")
	private WebElement notesTable;

	@FindBy(css = "#title-col")
	private WebElement notesTableFirstTitleCol;

	/******************************************/

	List<WebElement> notesList;
//	JavascriptExecutor executer;
//	WebDriverWait wait;
	private  WebDriver driver;
	JavascriptExecutor executer;
	String clickStr = "arguments[0].click();";

//TODO Check how to find hidden elements
	/*
	 * this.addNoteButton.click(); --> element should be in the [this page]
	 * https://www.edureka.co/community/94723/how-resolve-this-error-element-not-
	 * interactable-any-solution
	 *
	 */

	public NotesPage(WebDriver webDriver) {
		
		this.driver = webDriver;
		PageFactory.initElements(webDriver, this);
//		sleep
		executer = (JavascriptExecutor) driver;
		executer.executeScript("arguments[0].click();", notesTabField);

//		wait = new WebDriverWait(driver, 30);
		
//		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(noteTitleText)).sendKeys(title); 
	}

	public void addNote(String title, String description) {
		
		executer = (JavascriptExecutor) driver; 
		executer.executeScript("arguments[0].click();", addNoteButton);
		executer.executeScript("arguments[0].value='" + title + "';", noteTitleField);
		executer.executeScript("arguments[0].value='" + description + "';", noteDescriptionField);
		executer.executeScript("arguments[0].click();", addSubmit);
		
		
//		JavascriptExecutor jse = (JavascriptExecutor) driver; 
//		jse.executeScript("arguments[0].click()", this.driver.findElement(By.id("nav-notes-tab")));
//		jse.executeScript("arguments[0].click()", this.driver.findElement(By.id("add-note-button")));
//		jse.executeScript("arguments[0].value='"+ title + "';", this.driver.findElement(By.id("note-title")));
//		jse.executeScript("arguments[0].value='"+ description + "';", this.driver.findElement(By.id("note-description")));
//		jse.executeScript("arguments[0].click()", this.driver.findElement(By.id("note-submit")));

		
//		this.driver.findElement(By.id("nav-notes-tab")).click();
//		this.driver.findElement(By.id("add-note-button")).click();
//		this.driver.findElement(By.id("note-title")).sendKeys(title);
//		this.driver.findElement(By.id("note-description")).sendKeys(description);
//		this.driver.findElement(By.id("note-submit")).click();

		

//		jse.executeScript("arguments[0].click()", notesTab);
//		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(addNoteBtn)).click(); 
//		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(noteTitleText)).sendKeys(title); 
//		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(noteDescriptionText)).sendKeys(description); noteSubmitBtn.click();



//		this.notesTabField.click();
//		wait.until(ExpectedConditions.elementToBeClickable(addNoteButton));
//		wait.until(ExpectedConditions.elementToBeClickable(noteTitleField));
//		wait.until(ExpectedConditions.elementToBeClickable(noteDescriptionField));
//		this.noteDescriptionField.sendKeys(description);
//		executer.executeScript(clickStr, addSubmit);

	}

	public void editNote(String title, String description) {

		executer = (JavascriptExecutor) driver; 
		executer.executeScript("arguments[0].click();", editButton);
		executer.executeScript("arguments[0].value='" + title + "';", editTitleField);
		executer.executeScript("arguments[0].value='" + description + "';", editDescriptionField);
		executer.executeScript("arguments[0].click();", editSubmit);

		
//		this.notesTabField.click();

//		wait.until(ExpectedConditions.elementToBeClickable(editButton));
//		this.editButton.click();

//		wait.until(ExpectedConditions.elementToBeClickable(editTitleField));
//		this.editTitleField.clear();
//		this.editTitleField.sendKeys(title);
//
//		wait.until(ExpectedConditions.elementToBeClickable(editDescriptionField));
//		this.editDescriptionField.clear();
//		this.editDescriptionField.sendKeys(description);
//
//		executer.executeScript(clickStr, editSubmit);
	}

	public void deleteNote() {
		
		executer = (JavascriptExecutor) driver; 
		executer.executeScript("arguments[0].click();", deleteButton);
		executer.executeScript("arguments[0].click();", deleteSubmit);

		
		//		this.driver.findElement(By.id("nav-notes-tab")).click();
//		this.driver.findElement(By.id("delete-note-button")).click();
//		this.driver.findElement(By.id("deleteNoteSubmit")).click();

//		this.notesTabField.click();


//		wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
//		this.deleteButton.click();
	
//		@FindBy(css = "#delete-note-button")
//		private WebElement deleteButton;
//
//		@FindBy(css = "#deleteNoteSubmit")
//		private WebElement deleteSubmit;
//		executer.executeScript(clickStr, deleteSubmit);

	}

//	public WebElement getNotesTabField() {
////		return notesTabField;
//		return this.driver.findElement(By.id("nav-notes-tab"));
//	}

	public int getNotesTableSize() {
		this.driver.findElement(By.id("nav-notes-tab")).click();
		notesList = notesTable.findElements(By.id("title-col"));
		return notesList.size();
	}

	public String getNewNoteTitle() {
		return notesTableFirstTitleCol.getAttribute("innerHTML");

	}
	
	public void navToNotesView() {
		executer = (JavascriptExecutor) driver;
		executer.executeScript("arguments[0].click();", notesTabField);
//		return this.driver.findElement(By.id("nav-notes-tab"));
	}
}
