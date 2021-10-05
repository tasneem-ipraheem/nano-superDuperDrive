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
	    
//	    @FindBy(css="#logout-btn-id")
//	    private WebElement logoutButton;
	    
	    

	    public LoginPage(WebDriver webDriver) {
	        PageFactory.initElements(webDriver, this);
	    }

	    public void login(String username, String password) {
	        this.usernameField.sendKeys(username);
	        this.passwordField.sendKeys(password);
	        this.submitButton.click();
	        
	    }
	    
	    
//	    public void logout(String username, String password) {
////	        this.logoutButton.click();
//			((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutButton);
//
//	        
//	    }
}
