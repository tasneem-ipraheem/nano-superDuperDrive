package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
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
	
	NotesPage notesPage ;
	SignupPage signupPage;
	LoginPage loginPage ;
	CredentialsPage credentialsPage ;
	
	
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
		
		driver.get(this.baseURL + "/signup");
		signupPage = new SignupPage(driver);
		signupPage.signup("rootName", "rootPswrd", this.username, this.password);

		driver.get(this.baseURL + "/login");
		loginPage = new LoginPage(driver);
		loginPage.login(this.username, this.password);
		
		driver.get(this.baseURL + "/home");
		
	}

//	@AfterEach
//	public void afterEach() {
//		if (driver != null) {
//			driver.quit();
//		}
//	}

//	public void signupThenLogin() {
//
//		driver.get(this.baseURL + "/signup");
//		signupPage = new SignupPage(driver);
//		signupPage.signup("rootName", "rootPswrd", this.username, this.password);
//
//		driver.get(this.baseURL + "/login");
//		loginPage = new LoginPage(driver);
//		loginPage.login(this.username, this.password);
//		
//		driver.get(this.baseURL + "/home");
//
//
//	}

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
		
		notesPage = new NotesPage(driver);
		notesPage.addNote("Note title", "Note description");
		
		driver.get(this.baseURL + "/home");
//		notesPage.navToNotesView();
		Assertions.assertEquals("Note title",  notesPage.getNewNoteTitle());

		driver.get("http://localhost:" + this.port + "/home");
//		notesPage.navToNotesView();
		notesPage.deleteNote();

	}	
	
	

	
	@Test
	public void editNoteTest() {
		
		notesPage = new NotesPage(driver);
		notesPage.addNote("Note title", "Note description");
		
		driver.get("http://localhost:" + this.port + "/home");
//		notesPage.navToNotesView();
		notesPage.editNote("note Title edited", "Edited note description");
		
//		notesPage.navToNotesView();
		Assertions.assertEquals("note Title edited", notesPage.getNewNoteTitle());
		
		driver.get("http://localhost:" + this.port + "/home");
//		notesPage.navToNotesView();

		notesPage.deleteNote();


	}

	@Test
	public void deleteNoteTest() {
		
		notesPage = new NotesPage(driver);
		notesPage.addNote( "Note title", "Note description");
		
//		notesPage.navToNotesView();		notesPage.deleteNote();
		
		driver.get("http://localhost:" + this.port + "/home");
		notesPage.deleteNote();
//		notesPage.navToNotesView();
		
		Assertions.assertEquals(0,notesPage.getNotesTableSize());

	}
	
	


	
	@Test
	public void createCredentialTest() {
		
		credentialsPage = new CredentialsPage(driver);
		credentialsPage.addCredential( "facebook", "rootName", "rootPswrd");
		
		driver.get(this.baseURL + "/home");
//		credentialsPage.navToNotesView();
		Assertions.assertEquals("facebook",  credentialsPage.getNewCredentialTitle());
		
		driver.get("http://localhost:" + this.port + "/home");
//		credentialsPage.navToNotesView();
		credentialsPage.deleteCredential();
	}

	@Test
	public void editcredentialTest() {

		credentialsPage = new CredentialsPage(driver);
		credentialsPage.addCredential( "facebook", "rootName", "rootPswrd");
	
		driver.get("http://localhost:" + this.port + "/home");
//		credentialsPage.navToNotesView();
		credentialsPage.editCredential("Edited facebook", "Edited username", "Edited password");
		
		driver.get("http://localhost:" + this.port + "/home");
//		credentialsPage.navToNotesView();
		Assertions.assertEquals("Edited facebook",  credentialsPage.getNewCredentialTitle());
		
		driver.get("http://localhost:" + this.port + "/home");
//		credentialsPage.navToNotesView();
		credentialsPage.deleteCredential();
	}
	
	
	@Test
	public void deleteCredentialTest() {

		credentialsPage = new CredentialsPage(driver);
		credentialsPage.addCredential( "facebook", "rootName", "rootPswrd");
		
		driver.get("http://localhost:" + this.port + "/home");
//		credentialsPage.navToNotesView();
		credentialsPage.deleteCredential();
		
		driver.get("http://localhost:" + this.port + "/home");
//		credentialsPage.navToNotesView();
		Assertions.assertEquals(0, credentialsPage.getCredentialTableSize());
	}
	


}





