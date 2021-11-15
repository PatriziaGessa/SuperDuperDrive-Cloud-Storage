package com.udacity.jwdnd.course1.cloudstorage;


import com.udacity.jwdnd.course1.cloudstorage.selenium.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.NotePage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.SignupPage;
import com.udacity.jwdnd.course1.cloudstorage.utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteTest {
    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private SignupPage signupPage;
    private LoginPage loginPage;
    private NotePage notePage;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void endSelenium() {
        driver.close();
        try {
            driver.quit();
        } catch (Exception e) {
            System.out.println("Browser closed already");
            e.printStackTrace();
        }

    }

    @BeforeEach
    public void beforeEach() {
        String argument = Constants.LOCAL_HOST + port + Constants.SIGNUP_SLASH;
        driver.get(argument);
        signupPage = new SignupPage(driver);
        loginPage = new LoginPage(driver);
        notePage = new NotePage(driver);
    }

    @Test
    public void testNoteCreated() throws InterruptedException {
        createNote("createNote", "12345");
    }

    private void createNote(String name, String password) throws InterruptedException {
        signupAndLogin(name, password);

        //Get home
        String argumentLocalHostHome = Constants.LOCAL_HOST + port + Constants.HOME_SLASH;
        driver.get(argumentLocalHostHome);
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement homeMarker = wait.until
                (webDriver -> webDriver.findElement(By.id("nav-files-tab")));
        assertNotNull(homeMarker);

        // Enter note
        notePage.addNote("Note Title", "Description Note Text");
        Thread.sleep(3000);

        // Check note added
        String expectedTitle = "Note Title";
        String resultTitle = notePage.getFirstNoteTitle();
        assertEquals(expectedTitle, resultTitle);

        String expectedDescriptionNote = "Description Note Text";
        String resultDescriptionNote = notePage.getFirstNoteText();
        assertEquals(expectedDescriptionNote, resultDescriptionNote);
    }


    @Test
    public void testNoteEdited() throws InterruptedException {
        createNote("note test", "12345");
        notePage.editNote("New Note Title Test", "New Note Text Test");
        Thread.sleep(3000);

        // Check
        String expectedTitleNote = "New Note Title Test";
        String resultTitleNote = notePage.getFirstNoteTitle();
        assertEquals(expectedTitleNote,resultTitleNote);

        String expectedDescriptionNote = "New Note Text Test";
        String resultDescriptionNote = notePage.getFirstNoteText();
        assertEquals(expectedDescriptionNote, resultDescriptionNote);
    }

    @Test
    public void testNoteDeleted() throws InterruptedException {
        createNote("deleteNote", "12345");
        notePage.deleteNote();
        Thread.sleep(3000);

        // Check
        try {
            notePage.getFirstNoteTitle();
            fail("Note not deleted");
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }


    private void signupAndLogin(String name, String password) {
        //Signup
        signupPage.signUp("Patrizia", "Bellissima", name, password);

        //Login
        String argumentLocalHostLogin = Constants.LOCAL_HOST + port + Constants.LOGIN_SLASH;
        driver.get(argumentLocalHostLogin);
        loginPage.getLogin(name, password);
    }


}
