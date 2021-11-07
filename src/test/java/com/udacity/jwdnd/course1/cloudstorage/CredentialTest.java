package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.selenium.CredentialPage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.selenium.SignupPage;

import com.udacity.jwdnd.course1.cloudstorage.utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Credential Creation, Viewing, Editing, and Deletion.
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTest {


    @LocalServerPort
    private int port;

    private static WebDriver driver;
    private LoginPage loginPage;
    private SignupPage signupPage;
    private CredentialPage credentialPage;
    private HomePage homePage;
    WebDriverWait wait;


    /**
     * Included the TestInstance annotation above so that this could be an instance method.
     * Reason: needed to perform the signupUser() just once before the tests begin.
     */
    @BeforeAll
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }


    @AfterAll
    public static void afterAll() {
        // if (driver != null) {
        driver.quit();
        // }
    }

    @BeforeEach
    public void beforeEach() {
        driver.get(Constants.LOCAL_HOST + port + Constants.SIGNUP);
        signupPage = new SignupPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        credentialPage = new CredentialPage(driver);

    }


    /**
     * Tests if a credentials are successfully created, and verifies if it is displayed on the credential's list.
     * Additionally it checks if the shown password is encrypted.
     *
     * @throws InterruptedException
     */
    @Test
    @Order(1)
    public void testCreateCredentials() throws InterruptedException {
        // Signup
        getSignup("CreateCredentials");


        List<CredentialPage.CredentialFormTest> credentialFormTestList = new ArrayList<>();
        credentialFormTestList.add(new CredentialPage.CredentialFormTest("", "", "", ""));
        credentialFormTestList.add(new CredentialPage.CredentialFormTest("", "", "", ""));
        credentialFormTestList.add(new CredentialPage.CredentialFormTest("", "", "", ""));

        credentialPage.addListCredentials(credentialFormTestList);

        List<CredentialPage.CredentialFormTest> showCredentials = credentialPage.displayedCredentials();
        for (int i = 0; i < credentialFormTestList.size(); i++) {
            //String id = showCredentials.get(i).getId();

            CredentialPage.CredentialFormTest submittedCredential = credentialFormTestList.get(i);
            CredentialPage.CredentialFormTest itShowCredential = showCredentials.get(i);

            String expectedCredentialUrl = submittedCredential.getUrl();
            String resultCredentialUrl = itShowCredential.getUrl();
            assertEquals(expectedCredentialUrl, resultCredentialUrl);

            String expectedCredentialUsername = submittedCredential.getUsername();
            String resultCredentialUsername = itShowCredential.getUsername();
            assertEquals(expectedCredentialUsername, resultCredentialUsername);

            String expectedCredentialPassword = submittedCredential.getPassword();
            String resultCredentialPassword = itShowCredential.getPassword();
            assertEquals(expectedCredentialPassword, resultCredentialPassword);
        }


    }


    /**
     * Tests if a set of credentials is successfully edited and verifies if the changes are displayed on the credentials's list.
     * Additionally it checks if the shown password is encrypted.
     *
     * @throws InterruptedException
     */
    @Test
    @Order(2)
    public void testEditCredentials() throws InterruptedException {
        // Signup
        getSignup("EditCredential");
        TimeUnit.SECONDS.sleep(1);

        List<CredentialPage.CredentialFormTest> credentialFormTestList = new ArrayList<>();
        credentialFormTestList.add(new CredentialPage.CredentialFormTest("", "", "", ""));
        credentialFormTestList.add(new CredentialPage.CredentialFormTest("", "", "", ""));
        credentialFormTestList.add(new CredentialPage.CredentialFormTest("", "", "", ""));

        credentialPage.addListCredentials(credentialFormTestList);


        List<CredentialPage.CredentialFormTest> showListCredentials = credentialPage.displayedCredentials();
        List<CredentialPage.CredentialFormTest> editListCredentials = new ArrayList<>();

        for (int i = 0; i < showListCredentials.size(); i++) {
            String displayPassword = credentialPage.getShowedPasswordForCredentialId(showListCredentials.get(i).getId());

            String expectedPassword = credentialFormTestList.get(i).getPassword();
            String resultPassword = displayPassword;
            assertEquals(expectedPassword, resultPassword);
        }

        for (int i = 0; i < showListCredentials.size(); i++) {
            CredentialPage.CredentialFormTest showCredential = showListCredentials.get(i);
            editListCredentials.add(new CredentialPage.CredentialFormTest(
                    showCredential.getId(),
                    "" + i,
                    "" + i,
                    "" + i));


        }
        credentialPage.editListCredentials(editListCredentials);
        List<CredentialPage.CredentialFormTest> displayedCredentialsAfterEdit = credentialPage.displayedCredentials();
        for (int i = 0; i < displayedCredentialsAfterEdit.size(); i++) {
            CredentialPage.CredentialFormTest displayedCredential = displayedCredentialsAfterEdit.get(i);
            String showedPassword = credentialPage.getShowedPasswordForCredentialId(displayedCredential.getId());

            String expectedPassword = "";
            String resultPassword = showedPassword;
            assertEquals(expectedPassword + i, resultPassword);

            String expectedUsername = "";
            String resultUsername = displayedCredential.getUsername();
            assertEquals(expectedUsername + i, resultUsername);

            String expectedUrl = "";
            String resultUrl = displayedCredential.getUrl();
            assertEquals(expectedUrl + i, resultUrl);
        }


    }

    @Test
    @Order(3)
    public void testRemoveCredentials() throws InterruptedException {
        // Signup
        getSignup("DeleteCredential");
        TimeUnit.SECONDS.sleep(1);


        List<CredentialPage.CredentialFormTest> credentialFormTestList = new ArrayList<>();
        credentialFormTestList.add(new CredentialPage.CredentialFormTest("", "", "", ""));
        credentialFormTestList.add(new CredentialPage.CredentialFormTest("", "", "", ""));
        credentialFormTestList.add(new CredentialPage.CredentialFormTest("", "", "", ""));

        credentialPage.addListCredentials(credentialFormTestList);

        List<CredentialPage.CredentialFormTest> showCredentials = credentialPage.displayedCredentials();
        int expectedSize = credentialFormTestList.size();
        int resultSize = showCredentials.size();
        assertEquals(expectedSize, resultSize);

        credentialPage.deleteListOfCredentials(showCredentials);
        List<CredentialPage.CredentialFormTest> showCredentialsAfterRemove = credentialPage.displayedCredentials();
        int expectedSizeAfterRemove = 0;
        int resultSizeAfterRemove = showCredentialsAfterRemove.size();
        assertEquals(expectedSizeAfterRemove, resultSizeAfterRemove);


    }


    private void getSignup(String username) throws InterruptedException {
        signupPage.signUp("", "", "", "");


        //Login
        driver.get(Constants.LOCAL_HOST + port + Constants.LOGIN_SLASH);
        loginPage.getLogin("CreateCredentials", "123456");

        // To the Home
        driver.get(Constants.LOCAL_HOST + port + Constants.HOME_SLASH);
        wait = new WebDriverWait(driver, 3);
        WebElement homeMarker = wait.until(
                webDriver -> webDriver.findElement(By.id("nav-files-tab")));
        assertNotNull(homeMarker);
    }

}
