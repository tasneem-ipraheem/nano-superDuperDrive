package com.udacity.jwdnd.course1.cloudstorage;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialsPage {
	
	/*********** Add ***********/
	@FindBy (css="#add-credential-button")
	private WebElement addCredentialButton;

	@FindBy(css = "#credential-url")
	private WebElement credentialUrlField;

	@FindBy(css = "#credential-username")
	private WebElement credentialUsernameField;

	@FindBy(css="#credential-password")
	private WebElement credentialPasswordField;

	@FindBy(css="#credentialSubmit")
	private WebElement credentialAddSubmit;
	
	/*********** Edit ***********/

	@FindBy (css="#edit-credential-button")
	private WebElement editButton;


	@FindBy(css = "#edit-credential-username")
	private WebElement editUsernameField;

	@FindBy(css="#edit-credential-password")
	private WebElement editPasswordField;

	@FindBy(css="#edit-credential-url")
        private WebElement editUrlField;

	@FindBy(css="#editCredentialSubmit")
	private WebElement editSubmit;
	
	/*********** Delete ***********/

	@FindBy (css="#delete-credential-button")
	private WebElement deleteButton;

	@FindBy(css="#deleteCredentialSubmit")
	private WebElement deleteSubmit;
	
	
	
	@FindBy(css = "#nav-credentials-tab")
	private WebElement credentialsTabField;

	@FindBy(css = "#credentialTable")
	private WebElement credentialsTable;

	@FindBy(css = "#url-col")
	private WebElement credentialsTableFirstTitleCol;
	
	
	List<WebElement> credentialList;
	JavascriptExecutor executer;
	WebDriverWait wait;

	String clickStr = "arguments[0].click();";

	private WebDriver driver;


	public CredentialsPage(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
		this.driver = webDriver;
		executer = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 30);
	}

//	public void openCredentialTab() {
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialsTabField);
//	}

	public void  addCredential(String url, String username ,String password){

		wait.until(ExpectedConditions.elementToBeClickable(credentialsTabField));
		this.credentialsTabField.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(addCredentialButton));
		this.addCredentialButton.click();

		wait.until(ExpectedConditions.elementToBeClickable(credentialUrlField));
		this.credentialUrlField.sendKeys(url);

		wait.until(ExpectedConditions.elementToBeClickable(credentialUsernameField));
		this.credentialUsernameField.sendKeys(username);
		
		wait.until(ExpectedConditions.elementToBeClickable(credentialPasswordField));
		this.credentialPasswordField.sendKeys(password);

		executer.executeScript(clickStr, credentialAddSubmit);

	}
	

	public void editCredential(String url, String username, String password){
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + url + "';", editUrlField);
		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + username + "';", editUsernameField);
		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + password + "';", editPasswordField);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", editSubmit);
	}

	public void deleteCredential(){
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteSubmit);

	}
	
	
	public WebElement getCredentialsTabField() {
		return credentialsTabField;
	}

	public int getCredentialTableSize() {
		this.credentialsTabField.click();
//		credentialList = credentialsTable.findElements(By.id("url-col"));
//		return credentialList.size();
		
		return credentialsTable.getSize().getHeight();
	}

	public String getNewCredentialTitle() {
		return credentialsTableFirstTitleCol.getAttribute("innerHTML");

	}
	
//
//	public boolean hasCredentials() {
//		List<WebElement> credentialsList = credentialsTable.findElements(By.id("table-credentialTitle"));
//		return credentialsList.size() != 0;
//	}
	
	
}

