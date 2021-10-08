package com.udacity.jwdnd.course1.cloudstorage;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
 * https://stackoverflow.com/questions/49864965/org-openqa-selenium-elementnotinteractableexception-element-is-not-reachable-by
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	public int port;

	public static WebDriver driver;
	public String baseURL;

	String username = "rootName";
	String password = "rootPswrd";

	NotesPage notesPage;
	SignupPage signupPage;
	LoginPage loginPage;
	CredentialsPage credentialsPage;
	
	boolean found ;
	List<WebElement> notsTitles;
	List<WebElement> credentialUrlList;
	
	
	@BeforeAll
	public static void beforeAll() {
		WebDriverManager.chromedriver().setup();
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

		// signUp
		driver.get(this.baseURL + "/signup");
		signupPage = new SignupPage(driver);
		signupPage.signup("rootName", "rootPswrd", this.username, this.password);

		// signin
		driver.get(this.baseURL + "/login");
		loginPage = new LoginPage(driver);
		loginPage.login(this.username, this.password);

		driver.get(this.baseURL + "/home");
		
		
		found = false;

	}

	/**************** Login/signup ********************/

	/**
	 * 
	 * Write a Selenium test that signs up a new user, logs that user in, verifies
	 * that they can access the home page, then logs out and verifies that the home
	 * page is no longer accessible.
	 * 
	 */

	@Test
	public void accessHomeAfterLogout() {

		// user signup and login and moved to home page --> at beforeEach()

		// logout
		loginPage = new LoginPage(driver);
		loginPage.logout();
		Assertions.assertEquals("Login", driver.getTitle());

		// signs up a new user
		driver.get(this.baseURL + "/signup");
		signupPage = new SignupPage(driver);
		signupPage.signup("tasneem", "passwrd", "tasneem", "passwrd");

		// logs that user in
		driver.get(this.baseURL + "/login");
		loginPage = new LoginPage(driver);
		loginPage.login(this.username, this.password);

		// accessing home
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Home", driver.getTitle());

		// logout
		loginPage = new LoginPage(driver);
		loginPage.logout();

		Assertions.assertEquals("Login", driver.getTitle());

	}

	/**
	 * Write a Selenium test that verifies that the home page is not accessible
	 * without logging in.
	 */

	@Test
	public void accessHomeWithoutLogging() {

		// user signup and login and moved to home page --> at beforeEach()

		// logout
		loginPage = new LoginPage(driver);
		loginPage.logout();
		Assertions.assertEquals("Login", driver.getTitle());

		// Try accessing home
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());

	}

	/************* Notes *******************/

	/*
	 * Write a Selenium test that logs in an existing user, creates a note and
	 * verifies that the note details are visible in the note list.
	 */
	@Test
	public void createNoteTest() {

		// user signup and login and moved to home page --> at beforeEach()

		notesPage = new NotesPage(driver);
		notesPage.addNote("Note title", "Note description");
		
		notsTitles = notesPage.getNotesTitlesList();
		
		for (int i = 0; i < notsTitles.size(); i++) {
			String title = notsTitles.get(i).getAttribute("innerHTML");
			if ("Note title".equals(title)) {
				found =true;
				break;
			}
		}
		
		Assertions.assertTrue(found);


	}

	/**
	 * Write a Selenium test that logs in an existing user with existing notes,
	 * clicks the edit note button on an existing note, changes the note data, saves
	 * the changes, and verifies that the changes appear in the note list.
	 */

	@Test
	public void editNote() {

		// user signup and login and moved to home page --> at beforeEach()

		notesPage = new NotesPage(driver);
		notesPage.addNote("Note title", "Note description");

		driver.get("http://localhost:" + this.port + "/home");
		notesPage.editNote("note Title edited", "Edited note description");

		notsTitles = notesPage.getNotesTitlesList();
		
		for (int i = 0; i < notsTitles.size(); i++) {
			String title = notsTitles.get(i).getAttribute("innerHTML");
			if ("note Title edited".equals(title)) {
				found =true;
				break;
			}
		}
		
		Assertions.assertTrue(found);

	}

	/**
	 * Write a Selenium test that logs in an existing user with existing notes,
	 * clicks the delete note button on an existing note, and verifies that the note
	 * no longer appears in the note list.
	 */

	@Test
	public void deleteNote() {

		// user signup and login and moved to home page --> at beforeEach()

		notesPage = new NotesPage(driver);
		notesPage.addNote("Note title", "Note description");

		driver.get("http://localhost:" + this.port + "/home");
		notesPage.deleteNote();
		
		notsTitles = notesPage.getNotesTitlesList();
		
		for (int i = 0; i < notsTitles.size(); i++) {
			String title = notsTitles.get(i).getAttribute("innerHTML");
			if ("Note Title".equals(title)) {
				found =true;
				break;
			}
		}
		
		Assertions.assertFalse(found);
		
	}

	/*********** Credential *****************/

	/**
	 * Write a Selenium test that logs in an existing user, creates a credential and
	 * verifies that the credential details are visible in the credential list.
	 */

	@Test
	public void createCredential() {

		// user signup and login and moved to home page --> at beforeEach()

		credentialsPage = new CredentialsPage(driver);
		credentialsPage.addCredential("facebook", "rootName", "rootPswrd");
		
		credentialUrlList = credentialsPage.getCredentialUrlList();
		
		for (int i = 0; i < credentialUrlList.size(); i++) {
			String url = credentialUrlList.get(i).getAttribute("innerHTML");
			if ("facebook".equals(url)) {
				found =true;
				break;
			}
		}
		
		Assertions.assertTrue(found);

//		driver.get(this.baseURL + "/home");
//		Assertions.assertEquals("facebook", credentialsPage.getNewCredentialTitle());
//
		driver.get("http://localhost:" + this.port + "/home");
		credentialsPage.deleteCredential();
	}

	/**
	 * Write a Selenium test that logs in an existing user with existing
	 * credentials, clicks the edit credential button on an existing credential,
	 * changes the credential data, saves the changes, and verifies that the changes
	 * appear in the credential list.
	 */

	@Test
	public void editcredential() {

		// user signup and login and moved to home page --> at beforeEach()

		credentialsPage = new CredentialsPage(driver);
		credentialsPage.addCredential("facebook", "rootName", "rootPswrd");

		driver.get("http://localhost:" + this.port + "/home");
		credentialsPage.editCredential("Edited facebook", "Edited username", "Edited password");
		
		credentialUrlList = credentialsPage.getCredentialUrlList();
		
		for (int i = 0; i < credentialUrlList.size(); i++) {
			String url = credentialUrlList.get(i).getAttribute("innerHTML");
			if ("Edited facebook".equals(url)) {
				found =true;
				break;
			}
		}
		
		Assertions.assertTrue(found);

//		driver.get("http://localhost:" + this.port + "/home");
//		Assertions.assertEquals("Edited facebook", credentialsPage.getNewCredentialTitle());
//
		driver.get("http://localhost:" + this.port + "/home");
		credentialsPage.deleteCredential();
	}

	/**
	 * Write a Selenium test that logs in an existing user with existing
	 * credentials, clicks the delete credential button on an existing credential,
	 * and verifies that the credential no longer appears in the credential list.
	 */

	@Test
	public void deleteCredential() {

		// user signup and login and moved to home page --> at beforeEach()

		credentialsPage = new CredentialsPage(driver);
		credentialsPage.addCredential("facebook", "rootName", "rootPswrd");

		driver.get("http://localhost:" + this.port + "/home");
		credentialsPage.deleteCredential();
		
		credentialUrlList = credentialsPage.getCredentialUrlList();

//		System.out.println("***********		"+credentialUrlList.size());
		for (int i = 0; i < credentialUrlList.size(); i++) {
			String url = credentialUrlList.get(i).getAttribute("innerHTML");
//			System.out.println("***********		"+url);
			if ("facebook".equals(url)) {
				found = true;
				break;
			}
		}
		
		Assertions.assertFalse(found);

	}

}
