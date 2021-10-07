package com.udacity.jwdnd.course1.cloudstorage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CredentialsPage {

	/*********** Add ***********/
	@FindBy(css = "#add-credential-button")
	private WebElement addCredentialButton;

	@FindBy(css = "#credential-url")
	private WebElement credentialUrlField;

	@FindBy(css = "#credential-username")
	private WebElement credentialUsernameField;

	@FindBy(css = "#credential-password")
	private WebElement credentialPasswordField;

	@FindBy(css = "#credentialSubmit")
	private WebElement credentialAddSubmit;

	/*********** Edit ***********/

	@FindBy(css = "#edit-credential-button")
	private WebElement editButton;

	@FindBy(css = "#edit-credential-username")
	private WebElement editUsernameField;

	@FindBy(css = "#edit-credential-password")
	private WebElement editPasswordField;

	@FindBy(css = "#edit-credential-url")
	private WebElement editUrlField;

	@FindBy(css = "#editCredentialSubmit")
	private WebElement editSubmit;

	/*********** Delete ***********/

	@FindBy(css = "#delete-credential-button")
	private WebElement deleteButton;

	@FindBy(css = "#deleteCredentialSubmit")
	private WebElement deleteSubmit;

	@FindBy(css = "#nav-credentials-tab")
	private WebElement credentialsTabField;

	@FindBy(css = "#credentialTable")
	private WebElement credentialsTable;

	@FindBy(css = "#url-col")
	private WebElement credentialsTableFirstTitleCol;

	List<WebElement> credentialList;
	private WebDriver driver;
	JavascriptExecutor executer;
//	WebDriverWait wait;

	String clickStr = "arguments[0].click();";

	public CredentialsPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(webDriver, this);

		executer = (JavascriptExecutor) driver;
		executer.executeScript("arguments[0].click();", credentialsTabField);

//		wait = new WebDriverWait(driver, 30);
	}

//	public void openCredentialTab() {
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialsTabField);
//	}

	public void addCredential(String url, String username, String password) {

		executer = (JavascriptExecutor) driver;
		executer.executeScript("arguments[0].click();", addCredentialButton);
		executer.executeScript("arguments[0].value='" + url + "';", credentialUrlField);
		executer.executeScript("arguments[0].value='" + username + "';", credentialUsernameField);
		executer.executeScript("arguments[0].value='" + password + "';", credentialPasswordField);
		executer.executeScript("arguments[0].click();", credentialAddSubmit);
		executer.executeScript("arguments[0].click();", credentialsTabField);


//		wait.until(ExpectedConditions.elementToBeClickable(credentialsTabField));
//		this.credentialsTabField.click();
//		
//		wait.until(ExpectedConditions.elementToBeClickable(addCredentialButton));
//		this.addCredentialButton.click();
//
//		wait.until(ExpectedConditions.elementToBeClickable(credentialUrlField));
//		this.credentialUrlField.sendKeys(url);
//
//		wait.until(ExpectedConditions.elementToBeClickable(credentialUsernameField));
//		this.credentialUsernameField.sendKeys(username);
//		
//		wait.until(ExpectedConditions.elementToBeClickable(credentialPasswordField));
//		this.credentialPasswordField.sendKeys(password);
//
//		executer.executeScript(clickStr, credentialAddSubmit);

	}


	public void deleteCredential(){
		
		executer = (JavascriptExecutor) driver;
		executer.executeScript("arguments[0].click();", deleteButton);
		executer.executeScript("arguments[0].click();", deleteSubmit);
		executer.executeScript("arguments[0].click();", credentialsTabField);

		
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteButton);
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteSubmit);

	}
	
	
	public void editCredential(String url, String username, String password){
		
		executer = (JavascriptExecutor) driver;
		executer.executeScript("arguments[0].click();", editButton);
		executer.executeScript("arguments[0].value='" + url + "';", editUrlField);
		executer.executeScript("arguments[0].value='" + username + "';", editUsernameField);
		executer.executeScript("arguments[0].value='" + password + "';", editPasswordField);
		executer.executeScript("arguments[0].click();", editSubmit);
		executer.executeScript("arguments[0].click();", credentialsTabField);

//	((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);
//	((JavascriptExecutor) driver).executeScript("arguments[0].value='" + url + "';", editUrlField);
//	((JavascriptExecutor) driver).executeScript("arguments[0].value='" + username + "';", editUsernameField);
//	((JavascriptExecutor) driver).executeScript("arguments[0].value='" + password + "';", editPasswordField);
//	((JavascriptExecutor) driver).executeScript("arguments[0].click();", editSubmit);
}


	public WebElement getCredentialsTabField() {
		return credentialsTabField;
	}

	public int getCredentialTableSize() {
		
		this.driver.findElement(By.id("nav-credentials-tab")).click();
		credentialList = credentialsTable.findElements(By.id("url-col"));
		return credentialList.size();
	}

	public String getNewCredentialTitle() {
		return credentialsTableFirstTitleCol.getAttribute("innerHTML");

	}
//	public void navCreditinalsView() {
//		executer = (JavascriptExecutor) driver;
//		executer.executeScript("arguments[0].click();", credentialsTabField);
//	}
//
//	public boolean hasCredentials() {
//		List<WebElement> credentialsList = credentialsTable.findElements(By.id("table-credentialTitle"));
//		return credentialsList.size() != 0;
//	}

}
