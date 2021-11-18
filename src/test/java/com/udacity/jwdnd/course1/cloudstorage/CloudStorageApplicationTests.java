package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.selenium.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.SignupPage;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
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
    private int port;
    private String baseURL;

    private static WebDriver driver;

    @BeforeAll
    static void beforeAll() {
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
        driver = new ChromeDriver();
        baseURL = Constants.LOCAL_HOST + port;

    }

    //  @AfterEach
    // public void afterEach() {
    //   if (driver != null) {
    //    driver.quit();
    // }
    // }

    @Test
    public void getLoginPage() {
        driver.get(baseURL + Constants.LOGIN_SLASH);

        String expected = "Login";
        String result = driver.getTitle();
        assertEquals(expected, result);
    }
}
