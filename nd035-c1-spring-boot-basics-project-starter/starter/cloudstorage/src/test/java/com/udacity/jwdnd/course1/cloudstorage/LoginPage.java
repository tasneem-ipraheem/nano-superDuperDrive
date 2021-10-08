package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

class LoginPage {

	 @FindBy(css="#inputUsername")
	    private WebElement usernameField;

	    @FindBy(css="#inputPassword")
	    private WebElement passwordField;

	    @FindBy(css="#submit-button")
	    private WebElement submitButton;
   
	    @FindBy(css="#logout-btn-id")
	    private WebElement logoutButton;
	    
	    private WebDriver driver;
		JavascriptExecutor executer;
	    
	    public LoginPage(WebDriver webDriver) {
	        PageFactory.initElements(webDriver, this);
	        
	    	this.driver = webDriver;
			executer = (JavascriptExecutor) driver;
	    }

	    public void login(String username, String password) {
	        this.usernameField.sendKeys(username);
	        this.passwordField.sendKeys(password);
	        this.submitButton.click();
	        
	    }
	    
	    
	    public void logout() {
//	        this.logoutButton.click();
//			wait.until(ExpectedConditions.elementToBeClickable(By.id("logout-btn-id")));
			executer = (JavascriptExecutor) driver;
			executer.executeScript("arguments[0].click();", logoutButton);
	        
	    }
	    
}
