package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.selenium.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.SignupPage;
import com.udacity.jwdnd.course1.cloudstorage.utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for User Signup, Login, and Unauthorized Access Restrictions.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {

    @LocalServerPort
    private int port;
    private String baseURL;

    private static WebDriver driver;
    private SignupPage signupPage;
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        baseURL = Constants.LOCAL_HOST + port;
        driver = new ChromeDriver();
        signupPage = new SignupPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @AfterEach
    public void afterEach() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Signs up a user on the SDD application
     */
    public void signupUser() throws InterruptedException {
        driver.get(baseURL + Constants.SIGNUP_SLASH);
        signupPage.signUp(
                "test firstname",
                "test lastname",
                "test username",
                "test password1");
        Thread.sleep(3000);
    }

    /**
     * Authenticates a user
     */
    public void authenticateUser() throws InterruptedException {
        driver.get(baseURL + Constants.LOGIN_SLASH);
        loginPage.getLogin(
                "test username",
                "test password2");
        Thread.sleep(3000);
    }


    /**
     * Tests if a not authenticated user can access the login page.
     */
    @Test
    public void testAccessLoginPage() throws  InterruptedException{
        driver.get(baseURL + Constants.LOGIN_SLASH);
        Thread.sleep(2000);

        String expected = "Login";
        String result = driver.getTitle();
        assertEquals(expected, result);
    }

    /**
     * Tests if a not authenticated user can access the signup page.
     */
    @Test
    public void testAccessSignupPage() throws  InterruptedException{
        driver.get(baseURL + Constants.SIGNUP_SLASH);
        Thread.sleep(2000);


        String expected = "Sign Up";
        String result = driver.getTitle();
        assertEquals(expected, result);
    }


    /**
     * Tests if a not authenticated user can access the home page.
     */
    @Test
    public void testNotAccessHomePage() {
        driver.get(baseURL + Constants.HOME_SLASH);

        String expected = "Home";
        String result = driver.getTitle();
        assertNotEquals(expected, result);
    }


    /**
     * Tests if a user can signup, authenticate and access the home page.
     * Then the logout is preformed and the application tests if the user can still access the home page.
     */
    @Test
    public void testSignUpLoginLogout() throws InterruptedException {
        signupUser();
        authenticateUser();

      Thread.sleep(2000);
        String expected = "Home";
        String result = driver.getTitle();
        assertNotEquals(expected, result);


        homePage.logoutUser();
        Thread.sleep(2000);



        driver.get(baseURL + Constants.HOME_SLASH);

        String expectedHome2 = "Home";
        String resultHome2 = driver.getTitle();
        assertNotEquals(expected, result);
    }


}
