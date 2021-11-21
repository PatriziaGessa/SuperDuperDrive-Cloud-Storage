package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.selenium.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.NotePage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.SignupPage;
import com.udacity.jwdnd.course1.cloudstorage.utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for User Signup, Login, and Unauthorized Access Restrictions.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {

    @LocalServerPort
    private Integer port;


    private static WebDriver driver;
    private SignupPage signupPage;
    private LoginPage loginPage;
    private HomePage homePage;
    private WebDriverWait wait;
    private NotePage notePage;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();

    }


    @BeforeEach
    public void beforeEach() {
        driver = new ChromeDriver();

    }


    @AfterEach
    public void afterEach() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testAccessPage() {
        wait = new WebDriverWait(driver, 10);

        // Signup
        driver.get(Constants.LOCAL_HOST + port + Constants.SIGNUP_SLASH);
        String expectedSignup = "Sign Up";
        String actualSignup = driver.getTitle();
        assertEquals(expectedSignup, actualSignup);

        // Login
        driver.get(Constants.LOCAL_HOST + port + Constants.LOGIN_SLASH);
        String expectedLogin = "Login";
        String actualLogin = driver.getTitle();
        assertEquals(expectedLogin, actualLogin);

        // Get Home
        driver.get(Constants.LOCAL_HOST + port + Constants.HOME_SLASH);
        String unExpected = "Home";
        String actual = driver.getTitle();
        assertNotEquals(unExpected, actual);
    }

    @Test
    public void testSignup() {
        String firstName = "Patrizia", lastName = "Bella", username = "username", password = "password ";

        wait = new WebDriverWait(driver, 10);
        signupPage = new SignupPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        // Signup
        driver.get(Constants.LOCAL_HOST + port + Constants.SIGNUP_SLASH);
        signupPage.signUp(firstName, lastName, username, password);

        // Login
        driver.get(Constants.LOCAL_HOST + port + Constants.LOGIN_SLASH);
        WebElement marker = wait.until(webDriver -> webDriver.findElement(By.name("username")));
        loginPage.login(username, password);
        marker = wait.until(webDriver -> webDriver.findElement(By.id("nav-files-tab")));
        homePage.logoutUser();
        marker = wait.until(webDriver -> webDriver.findElement(By.name("username")));

        // Get Home
        driver.get(Constants.LOCAL_HOST + port + Constants.HOME_SLASH);

        String unExpected = "Home";
        String actual = driver.getTitle();
        assertNotEquals(unExpected, actual);
    }


}
