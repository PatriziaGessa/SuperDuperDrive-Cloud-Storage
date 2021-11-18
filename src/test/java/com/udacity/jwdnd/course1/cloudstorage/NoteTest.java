package com.udacity.jwdnd.course1.cloudstorage;


import com.udacity.jwdnd.course1.cloudstorage.selenium.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.NotePage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.SignupPage;
import com.udacity.jwdnd.course1.cloudstorage.utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteTest {
    @LocalServerPort
    private int port;
    private String baseURL;

    private static WebDriver driver;
    private SignupPage signupPage;
    private LoginPage loginPage;
    private NotePage notePage;
    private WebDriverWait wait;


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
        baseURL = Constants.LOCAL_HOST + port;
        driver.get(baseURL +  Constants.SIGNUP_SLASH);
        signupPage = new SignupPage(driver);
        loginPage = new LoginPage(driver);
        notePage = new NotePage(driver);
    }

    
    @Test
    public void testCreateNote() throws InterruptedException {
        String firstName = "a", lastName = "b", username = "c", password = "d";
        String noteTitle = "created!", noteDescription = "created!";
        wait = new WebDriverWait(driver, 10);


        // Signup
        driver.get(Constants.LOCAL_HOST + port + Constants.SIGNUP_SLASH);
        signupPage.signUp(firstName, lastName, username, password);

        //Login
        driver.get(Constants.LOCAL_HOST + port + Constants.LOGIN_SLASH);
        WebElement marker = wait.until(ExpectedConditions.elementToBeClickable(By.name("username")));
        loginPage.getLogin(username, password);

        //Get Home
        driver.get(Constants.LOCAL_HOST + port + Constants.HOME_SLASH);
        marker = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-notes-tab")));
        driver.findElement(By.id("nav-notes-tab")).click();

        // Create Note
        marker = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-note-button")));
        driver.findElement(By.id("add-note-button")).click();
        marker = wait.until(ExpectedConditions.elementToBeClickable(By.id("note-title")));
        notePage.addNote(noteTitle, noteDescription);
        driver.get(Constants.LOCAL_HOST + port + Constants.HOME_SLASH);
        marker = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-notes-tab")));
        driver.findElement(By.id("nav-notes-tab")).click();
        marker = wait.until(ExpectedConditions.elementToBeClickable(By.id("show-note-title")));
        String result = driver.findElement(By.id("show-note-title")).getText();
        assertEquals(result, noteTitle);

    }


}
