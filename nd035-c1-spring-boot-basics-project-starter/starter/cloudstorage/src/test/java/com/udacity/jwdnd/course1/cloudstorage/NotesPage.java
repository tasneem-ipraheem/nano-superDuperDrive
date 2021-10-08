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
	
	@FindBy(css = "#title-desc")
	private WebElement notesTableFirstDescriptionCol;

	/******************************************/

	List<WebElement> notesList;
	private  WebDriver driver;
	JavascriptExecutor executer;

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
		executer = (JavascriptExecutor) driver;
		executer.executeScript("arguments[0].click();", notesTabField);
	}

	public void addNote(String title, String description) {
		
		executer = (JavascriptExecutor) driver; 
		executer.executeScript("arguments[0].click();", addNoteButton);
		
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOf(noteTitleField)).sendKeys(title);
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOf(noteDescriptionField)).sendKeys(description);
		
		executer.executeScript("arguments[0].click();", addSubmit);
		executer.executeScript("arguments[0].click();", notesTabField);
		
	}

	public void editNote(String title, String description) {
		
		executer = (JavascriptExecutor) driver; 
		executer.executeScript("arguments[0].click();", editButton);
		
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOf(editTitleField)).clear();
		editTitleField.sendKeys(title);
		
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOf(editDescriptionField)).clear();
		editDescriptionField.sendKeys(description);
		
		executer.executeScript("arguments[0].click();", editSubmit);
		executer.executeScript("arguments[0].click();", notesTabField);
		
	}

	public void deleteNote() {
		
		executer = (JavascriptExecutor) driver; 
		executer.executeScript("arguments[0].click();", deleteButton);
		executer.executeScript("arguments[0].click();", deleteSubmit);
		executer.executeScript("arguments[0].click();", notesTabField);
		
	}

	public int getNotesTableSize() {
		this.driver.findElement(By.id("nav-notes-tab")).click();
		notesList = notesTable.findElements(By.id("title-col"));
		return notesList.size();
	}
	
	
	public List<WebElement> getNotesTitlesList() {
		this.driver.findElement(By.id("nav-notes-tab")).click();
		notesList = notesTable.findElements(By.id("title-col"));
		return notesList;
	}

	public String getNewNoteTitle() {
		return notesTableFirstTitleCol.getAttribute("innerHTML");

	}

	public String getNewNoteDescription() {
		return notesTableFirstDescriptionCol.getAttribute("innerHTML");

	}
}
