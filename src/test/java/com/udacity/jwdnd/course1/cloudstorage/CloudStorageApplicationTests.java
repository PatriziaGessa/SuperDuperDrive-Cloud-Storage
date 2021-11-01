package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.selenium.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.SignupPage;
import com.udacity.jwdnd.course1.cloudstorage.utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.lang.invoke.ConstantBootstraps;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

    @LocalServerPort
    private Integer port;
    private static WebDriver driver;
    private static WebDriverWait wait;
    private SignupPage signupPage;
    private LoginPage loginPage;
    private HomePage homePage;
    private String baseURL;

    //  Global Variable
    private String firstname = "my name";
    private String lastname = "my lastname";
    private String username = "admir";
    private String password = "this is a password";


    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }


    @AfterAll
    public static void afterAll() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeEach
    public void beforeEach() {
        baseURL = Constants.LOCAL_HOST + port;
        signupPage = new SignupPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

// Not really gut
    @Test
    @Order(1)
    public void testUserSignupAndLogin() {
        // Signup
        driver.get(baseURL + "/signup");
        signupPage.signUp(
                firstname,
                lastname,
                username,
                password);

        // Login
        driver.get(baseURL + "/login");
        loginPage.getLogin(username, password);

        String expected = "Home";
        String result = driver.getTitle();
        assertEquals(expected, result);

    }

    @Test
    @Order(2)
    public void testLoginPageAccessed() {
        driver.get(baseURL + port + "/login");
        wait = new WebDriverWait(driver, 3);
        WebElement homeMarker = null;
        homeMarker = wait.until(webDriver -> webDriver.findElement(By.id("inputUsername")));
        assertNotNull(homeMarker);
    }

    @Test
    @Order(3)
    public void testHomePageNotAccessed() {
        driver.get(baseURL + port + "/home");
        wait = new WebDriverWait(driver, 3);
        WebElement homeMarker = null;
        try {
            homeMarker = wait.until(webDriver -> webDriver.findElement(By.id("nav-files-tab")));
        } catch (TimeoutException e) {
            // Expected
        }
        assertNull(homeMarker);

        WebDriverWait waitSecond = new WebDriverWait(driver, 3);
        WebElement markerSecond = waitSecond.until(webDriver -> webDriver.findElement(By.id("inputUsername")));
        assertNotNull(markerSecond);
    }


    @Test
    @Order(4)
    public void testSignupPageAccessed() {
        driver.get(baseURL + port + "/signup");
        wait = new WebDriverWait(driver, 3);
        WebElement homeMarker = null;
        homeMarker = wait.until(webDriver -> webDriver.findElement(By.id("inputUsername")));
        assertNotNull(homeMarker);

    }

    @Test
    @Order(5)
    public void testHomePageAfterSignupAccessible() {
        // Signup
        signupPage.signUp(firstname, lastname, username, password);
        // Login
        driver.get(baseURL + port + "/login");
        String testUsername = "username";
        String testPass = "pass";
        loginPage.getLogin(testUsername, testPass);
        // Get to HomePage
        driver.get(baseURL + port + "/home");
        wait = new WebDriverWait(driver, 3);
        WebElement homeMarker = wait.until(webDriver -> webDriver.findElement(By.id("nav-files-tab")));
        assertNotNull(homeMarker);

        // Logout
        homePage.logout();

        // Test home page not accessible
        driver.get(username + port + "/home");
        WebDriverWait waitSecond = new WebDriverWait(driver, 3);
        WebElement homeMarkerSecond = null;
        try {
            homeMarkerSecond = waitSecond.until(webDriver -> webDriver.findElement(By.id("nav-files-tab")));
        } catch (TimeoutException e) {
            // Expected
        }
        assertNull(homeMarkerSecond);
    }


    @Test
    @Order(6)
    public void testAddingNewNote() throws InterruptedException {
        String argumentTitle = "Title";
        String argumentDescription = "Description";
        testLoginPageAccessed();


        homePage = new HomePage(driver);
        homePage.createNote(argumentTitle, argumentDescription);

        String expectedTitle = "Title";
        String resultTitle = homePage.getNoteTitle();
        assertEquals(expectedTitle, resultTitle);

        String expectedDescription = "Description";
        String resultDescription = homePage.getNoteDescription();
        assertEquals(expectedDescription, resultDescription);


    }

    @Test
    @Order(7)
    public void testEditNote() throws InterruptedException {
        String title = "Title";
        String description = "Description";
        testAddingNewNote();


       homePage.editNote(title, description);
        Thread.sleep(3000);

        // Check
        String expectedTitle = "Title";
        String expectedDescription = "Description";
        String resultTitle = homePage.getNoteTitle();
        String resultDescription = homePage.getNoteDescription();
        assertEquals(expectedTitle, resultTitle);
        assertEquals(expectedDescription, resultDescription);




    }


    @Test
    @Order(6)
    public void deleteANote() throws InterruptedException {

        testAddingNewNote();
        NotesPage notesPage = new NotesPage(driver);
        notesPage.deleteNote(driver);
        Assertions.assertEquals("Result", driver.getTitle());


    }

    @Test
    @Order(7)
    public void modifyNote() throws InterruptedException {

        String editedTitle = "EditedTest";
        String editedDescription = "EditedDescription";
        testAddingNewNote();
        NotesPage notesPage = new NotesPage(driver);
        notesPage.editNote(driver, editedTitle, editedDescription);
        Note note = notesPage.getFirstNote(driver);
        Assertions.assertEquals(editedTitle, note.getNoteTitle());
        Assertions.assertEquals(editedDescription, note.getNoteDescription());

    }

    @Test
    @Order(8)
    public void createCredentialAndVerifyPasswordEncrypted() throws Exception {

        String url = "www.facebook.com";
        String username = "rizwan";
        String password = "testpassword";
        EncryptionService encryptionService = new EncryptionService();
        credentialsPage = new CredentialsPage(driver, credentialService, encryptionService);


        login();
        credentialsPage.addCredential(driver, url, username, password);
        Credential credential = credentialsPage.getFirstCredential(driver);
        String encryptedPassword = credential.getPassword();

        String passwordInDB = credentialsPage.getEncryptedPassword(username, credentialService);


        Assertions.assertEquals(encryptedPassword, passwordInDB);

    }

    @Test
    @Order(9)
    public void editACredentialAndVerifiesChangesDisplayed() throws Exception {


        String editedurl = "EditedTest";
        String editedusername = "EditedDescription";
        String editedpassword = "EditedPassword";
        login();

        CredentialsPage credentialsPage = new CredentialsPage(driver, credentialService, encryptionService);
        credentialsPage.editCredential(driver, editedurl, editedusername, editedpassword);
        Credential credential = credentialsPage.getFirstCredential(driver);
        String decryptedPassword = credentialsPage.getDecryptedPassword(editedusername, credentialService, encryptionService);
        Assertions.assertEquals(editedurl, credential.getUrl());
        Assertions.assertEquals(editedusername, credential.getUsername());
        Assertions.assertEquals(editedpassword, decryptedPassword);


    }

    @Test
    @Order(10)
    public void deleteACredential() throws Exception {
        login();
        CredentialsPage credentialsPage = new CredentialsPage(driver, credentialService, encryptionService);
        credentialsPage.deleteCredential(driver);
        Assertions.assertEquals("Result", driver.getTitle());


    }


    private void signupAndLogin(String username, String password) {
        //Signup
        String firstname = "name";
        String lastname = "lastname";
        signupPage.signUp(firstname, lastname, username, password);

        //Login
        driver.get(baseURL + port + "/login");
        loginPage.getLogin(username, password);
    }

}
