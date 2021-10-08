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

	public CredentialsPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(webDriver, this);

		executer = (JavascriptExecutor) driver;
		executer.executeScript("arguments[0].click();", credentialsTabField);

	}


	public void addCredential(String url, String username, String password) {

		executer = (JavascriptExecutor) driver;
		executer.executeScript("arguments[0].click();", addCredentialButton);
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOf(credentialUrlField)).sendKeys(url);
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOf(credentialUsernameField)).sendKeys(username);
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOf(credentialPasswordField)).sendKeys(password);
		executer.executeScript("arguments[0].click();", credentialAddSubmit);
		executer.executeScript("arguments[0].click();", credentialsTabField);

	}


	public void deleteCredential(){
		
		executer = (JavascriptExecutor) driver;
		executer.executeScript("arguments[0].click();", deleteButton);
		executer.executeScript("arguments[0].click();", deleteSubmit);
		executer.executeScript("arguments[0].click();", credentialsTabField);



	}
	
	
	public void editCredential(String url, String username, String password){
		
		executer = (JavascriptExecutor) driver;
		executer.executeScript("arguments[0].click();", editButton);
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOf(editUrlField)).clear();
		editUrlField.sendKeys(url);
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOf(editUsernameField)).clear();
		editUsernameField.sendKeys(username);
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOf(editPasswordField)).clear();
		editPasswordField.sendKeys(password);
		executer.executeScript("arguments[0].click();", editSubmit);
		executer.executeScript("arguments[0].click();", credentialsTabField);

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
}
