package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.selenium.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.SignupPage;
import com.udacity.jwdnd.course1.cloudstorage.utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice;
import org.jsoup.parser.CharacterReader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTest {

    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private static WebDriverWait wait;
    private SignupPage signupPage;
    private LoginPage loginPage;
    private HomePage homePage;


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
        driver.get(Constants.LOCAL_HOST + port + "/signup");
        signupPage = new SignupPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }


    /**
     * Attempt to access the homepage without being logged in.
     */

}
}
