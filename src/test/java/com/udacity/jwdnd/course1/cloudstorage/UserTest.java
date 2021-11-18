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
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());


        driver.get("http://localhost:" + this.port + "/signup");
        Assertions.assertEquals("Sign Up", driver.getTitle());


        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertNotEquals("Home", driver.getTitle());
    }

    @Test
    public void testSignup() throws InterruptedException {
        String firstName = "a", lastName = "b", username = "c", password = "d";

        WebDriverWait wait = new WebDriverWait(driver, 10);


        driver.get("http://localhost:" + this.port + "/signup");
        signupPage.signUp(firstName, lastName, username, password);
        driver.get("http://localhost:" + this.port + "/login");
        WebElement marker = wait.until(webDriver -> webDriver.findElement(By.name("username")));
        loginPage.getLogin(username, password);
        marker = wait.until(webDriver -> webDriver.findElement(By.id("nav-files-tab")));
        homePage.logoutUser();
        marker = wait.until(webDriver -> webDriver.findElement(By.name("username")));
        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertNotEquals("Home", driver.getTitle());
    }


}
