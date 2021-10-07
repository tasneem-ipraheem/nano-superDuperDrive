package com.udacity.jwdnd.course1.cloudstorage;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.github.bonigarcia.wdm.WebDriverManager;

//TODO [Junit - Read resources]
/**
 * 1. Setup the driver :
 * https://classroom.udacity.com/nanodegrees/nd035/parts/f318240c-0513-47b3-bf26-09db96482caf/modules/1370646e-400f-4685-b916-5bc8e03554ce/lessons/67b1a5b0-14a8-419b-b9d1-c5604f9cbcbe/concepts/a792032b-e19f-4b49-aa90-be0d796cc6f0
 * 2. Chat example :
 * https://github.com/udacity/nd035-c1-spring-boot-basics-examples/tree/master/udacity-jwdnd-c1-l5-final-review-solution-master
 * 3. DOCs :
 * https://www.selenium.dev/documentation/guidelines/page_object_models/
 * https://junit.org/junit5/docs/current/user-guide/#writing-tests-assertions
 * https://junit.org/junit5/docs/current/user-guide/#writing-tests-annotations
 * https://github.com/bonigarcia/webdrivermanager
 * https://www.selenium.dev/documentation/webdriver/waits/
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	public int port;

	public static WebDriver driver;
	public String baseURL;

	@BeforeAll
	public static void beforeAll() {
		WebDriverManager.chromedriver().setup();
//		new WebDriverWait(driver, 1000);
		driver = new ChromeDriver();
	}

	@AfterAll
	public static void afterAll() {
		driver.quit();
		driver = null;
	}

	@BeforeEach
	public void beforeEach() {
		baseURL = "http://localhost:" + port;
//		driver = new ChromeDriver();
		
	}

//	@AfterEach
//	public void afterEach() {
//		if (driver != null) {
//			driver.quit();
//		}
//	}

	String username = "rootName";
	String password = "rootPswrd";
	
	NotesPage notesPage ;
	SignupPage signupPage;
	LoginPage loginPage ;

	public void signupThenLogin() {

		driver.get(this.baseURL + "/signup");
		signupPage = new SignupPage(driver);
		signupPage.signup("rootName", "rootPswrd", this.username, this.password);

		driver.get(this.baseURL + "/login");
		loginPage = new LoginPage(driver);
		loginPage.login(this.username, this.password);
		
		driver.get(this.baseURL + "/home");


	}

	/**************** Login/signup ********************/

//	@Test
//	public void getSignupPage() {
//		driver.get(this.baseURL + "/signup");
//		Assertions.assertEquals("Sign Up", driver.getTitle());
//	}
//
//	@Test
//	public void getLoginPage() {
//		driver.get(this.baseURL + "/login");
//		Assertions.assertEquals("Login", driver.getTitle());
//	}
//	@Test
//	public void testRandomPage() {
//		driver.get(this.baseURL + "/dummy");
//		Assertions.assertEquals("Login", driver.getTitle());
//	}
	
	/*
	@Test
	public void testUserSignupLoginHome() {
		signup();
		login();
		driver.get(this.baseURL + "/home");
		Assertions.assertEquals("Home", driver.getTitle());

	}
	
	@Test
	public void getHomePageNOLogin() {
		driver.get(this.baseURL + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}


	@Test
	public void testUserSignupLoginLogOut() {
		signup();
		login();
		driver.get(this.baseURL + "/login?logout");
		Assertions.assertEquals("You have been logged out", driver.findElement(By.id("logout-msg")).getText());
		driver.get(this.baseURL + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

	}

	

*/
	
//	/*************		Notes  	*******************/

	
	@Test
	public void createNoteTest() {
		
		signupThenLogin();
		
		notesPage = new NotesPage(driver);
		notesPage.addNote("Note title", "Note description");
		
		driver.get(this.baseURL + "/home");
		notesPage.getNotesTabField().click();
		Assertions.assertEquals("Note title",  notesPage.getNewNoteTitle());

		driver.get("http://localhost:" + this.port + "/home");
		notesPage.getNotesTabField().click();
		notesPage.deleteNote();

	}	
	
	

	
	@Test
	public void editNoteTest() {
		signupThenLogin();
		
		notesPage = new NotesPage(driver);
		notesPage.addNote("Note title", "Note description");
		
		driver.get("http://localhost:" + this.port + "/home");
		notesPage.getNotesTabField().click();
		notesPage.editNote("note Title edited", "Edited note description");
		
		notesPage.getNotesTabField().click();
		Assertions.assertEquals("note Title edited", notesPage.getNewNoteTitle());
		
		driver.get("http://localhost:" + this.port + "/home");
		notesPage.getNotesTabField().click();
		notesPage.deleteNote();


	}

	@Test
	public void deleteNoteTest() {
		signupThenLogin();
		
		notesPage = new NotesPage(driver);
		notesPage.addNote( "Note title", "Note description");
		
		notesPage.getNotesTabField().click();
		notesPage.deleteNote();
		
		driver.get("http://localhost:" + this.port + "/home");
		notesPage.getNotesTabField().click();
		
		Assertions.assertEquals(0,notesPage.getNotesTableSize());

	}
	
/*
	@Test
	public void createCredentialTest() {
		signup();
		login();
		
		driver.get(this.baseURL + "/home");
		CredentialsPage credentialsPage = new CredentialsPage(driver);
		credentialsPage.addCredential( "facebook", "rootName", "rootPswrd");
		Assertions.assertEquals("You successfully added a new credential", driver.findElement(By.id("success-msg")).getText());
		
		driver.get(this.baseURL + "/home");
		credentialsPage.openCredentialTab();
		Assertions.assertEquals("facebook",  driver.findElement(By.id("url-col")).getAttribute("innerHTML"));
		
		driver.get("http://localhost:" + this.port + "/home");
		credentialsPage.openCredentialTab();
		credentialsPage.deleteCredential();
	}

	@Test
	public void editcredentialTest() {
		signup();
		login();
		CredentialsPage credentialsPage = new CredentialsPage(driver);
		credentialsPage.addCredential( "facebook", "rootName", "rootPswrd");
		Assertions.assertEquals("You successfully added a new credential", driver.findElement(By.id("success-msg")).getText());
		driver.get("http://localhost:" + this.port + "/home");
		credentialsPage.openCredentialTab();
		credentialsPage.editCredential("edited facebook", "Edited username", "Edited password");
		driver.get("http://localhost:" + this.port + "/home");
		credentialsPage.openCredentialTab();
		Assertions.assertEquals("edited facebook",  driver.findElement(By.id("url-col")).getAttribute("innerHTML"));
		
		driver.get("http://localhost:" + this.port + "/home");
		credentialsPage.openCredentialTab();
		credentialsPage.deleteCredential();
	}

	@Test
	public void deleteCredentialTest() {
		signup();
		login();
		CredentialsPage credentialsPage = new CredentialsPage(driver);
		credentialsPage.addCredential( "facebook", "rootName", "rootPswrd");
		Assertions.assertEquals("You successfully added a new credential", driver.findElement(By.id("success-msg")).getText());
		driver.get("http://localhost:" + this.port + "/home");
		credentialsPage.openCredentialTab();
		credentialsPage.deleteCredential();
		driver.get("http://localhost:" + this.port + "/home");
		credentialsPage.openCredentialTab();
		Assertions.assertEquals(false, credentialsPage.hasCredentials());
	}
	
*/

}





