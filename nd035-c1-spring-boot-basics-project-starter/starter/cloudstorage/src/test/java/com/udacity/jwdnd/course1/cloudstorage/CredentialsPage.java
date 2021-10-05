package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CredentialsPage {
	@FindBy(css = "#nav-credentials-tab")
	private WebElement credentialsTabField;

	@FindBy(css = "#credentialTable")
	private WebElement credentialsTable;

	@FindBy (css="#add-credential-button")
	private WebElement addButton;

	@FindBy(css = "#credential-url")
	private WebElement credentialUrl;

	@FindBy(css = "#credential-username")
	private WebElement credentialUsername;

	@FindBy(css="#credential-password")
	private WebElement credentialPassword;

	@FindBy(css="#credentialSubmit")
	private WebElement credentialSubmit;

	@FindBy (css="#edit-credential-button")
	private WebElement editButton;

	@FindBy (css="#delete-credential-button")
	private WebElement deleteButton;

	@FindBy(css = "#edit-credential-username")
	private WebElement editUsername;

	@FindBy(css="#edit-credential-password")
	private WebElement editPassword;

	@FindBy(css="#edit-credential-url")
        private WebElement editUrl;

	@FindBy(css="#editCredentialSubmit")
	private WebElement editSubmit;

	@FindBy(css="#deleteCredentialSubmit")
	private WebElement deleteSubmit;

	private final WebDriver driver;

	private final WebDriverWait wait;

	public CredentialsPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(webDriver, this);
		wait = new WebDriverWait(driver, 1000);
	}

	public void openCredentialTabJS() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialsTabField);
	}

	public void  addCredential(String url, String username ,String password){
		this.openCredentialTabJS();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + url + "';", credentialUrl);
		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + username + "';", credentialUsername);
		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + password + "';", credentialPassword);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialSubmit);
	}

	public void editCredential(String url, String username, String password){
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + url + "';", editUrl);
		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + username + "';", editUsername);
		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + password + "';", editPassword);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", editSubmit);
	}

	public void deleteCredential(){
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteSubmit);

	}

	public boolean hasCredentials() {
		List<WebElement> credentialsList = credentialsTable.findElements(By.id("table-credentialTitle"));
		return credentialsList.size() != 0;
	}
}

