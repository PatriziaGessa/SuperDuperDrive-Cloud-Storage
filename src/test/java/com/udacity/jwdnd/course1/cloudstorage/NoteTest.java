package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.selenium.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.NotePage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.SignupPage;
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
    private LoginPage loginPageObject;

    private NotePage notePage;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @BeforeEach
    public void beforeEach() {
        driver.get("http://localhost:" + port + "/signup");
        signupPage = new SignupPage(driver);
        loginPageObject = new LoginPage(driver);

        notePage = new NotePage(driver);
    }

    @Test
    public void testNoteCreated() throws InterruptedException {
        createNote("createNote", "12345");
    }

    private void createNote(String name, String password) throws InterruptedException {
        signupAndLogin(name, password);

        // Go to home
        driver.get("http://localhost:" + port + "/home");
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement homeMarker = wait.until
                (webDriver -> webDriver.findElement(By.id("nav-files-tab")));
        assertNotNull(homeMarker);

        // Enter note
        notePage.addNote("Title", "Text");
        Thread.sleep(3000);

        // Check note added
        assertEquals("Title", notePage.getFirstNoteTitle());
        assertEquals("Text", notePage.getFirstNoteText());
    }

    private void signupAndLogin(String name, String password)  {
        //Signup
        signupPage.signUp("Patrizia", "Bellissima", name, password);

        //Login
        driver.get("http://localhost:" + port + "/login");
        loginPageObject.getLogin(name, password);
    }

    @Test
    public void testNoteEdited() throws InterruptedException {
        createNote("editNote", "12345");
        notePage.editNote("New Title", "New Text");
        Thread.sleep(3000);

        // Check
        assertEquals("New Title", notePage.getFirstNoteTitle());
        assertEquals("New Text", notePage.getFirstNoteText());
    }

    @Test
    public void testNoteDeleted() throws InterruptedException {
        createNote("deleteNote", "12345");
        notePage.deleteNote();
        Thread.sleep(3000);

        // Check
        try{
            notePage.getFirstNoteTitle();
            fail("Note not deleted");
        }catch (NoSuchElementException e){
            assertTrue(true);
        }
    }


}
