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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTest {


    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private LoginPage loginPage;
    private SignupPage signupPage;
    private CredentialPage credentialPage;
    private HomePage homePage;
    WebDriverWait wait;


    /**
     * Included the TestInstance annotation above so that this could be an instance method.
     */
    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }


    @AfterAll
    public static void endSelenium() {
        // if (driver != null) {
        // driver.quit();
        // }
        driver.close();
        try {
            driver.quit();
        } catch (Exception e) {
            System.out.println("Browser closed already, " +
                    "did not need to quit after all");
            e.printStackTrace();
        }

    }

    @BeforeEach
    public void beforeEach() {
        String argument = Constants.LOCAL_HOST + port + Constants.SIGNUP_SLASH;
        driver.get(argument);
        signupPage = new SignupPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        credentialPage = new CredentialPage(driver);

    }


    /**
     * Tests if a credentials are successfully created, and verifies if it is displayed on the credential's list.
     */
    @Test
    public void testCreateCredentials() throws InterruptedException {
        // Signup
        getSignup("CreateCredentials");

        List<CredentialPage.CredentialFormTest> credentialFormTestList = new ArrayList<>();
        credentialFormTestList.add(new CredentialPage.CredentialFormTest("fg", "fg", "fdg", "fg"));
        credentialFormTestList.add(new CredentialPage.CredentialFormTest("fg", "fg", "fg", "fg"));
        credentialFormTestList.add(new CredentialPage.CredentialFormTest("fg", "fg", "fg", "fg"));

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
     * Tests if a set of credentials is successfully edited and verifies if the changes are displayed on the credential's list.
     * Additionally, it checks if the shown password is encrypted.
     */
    @Test
    public void testEditCredentials() throws InterruptedException {
        // Signup
        getSignup("EditCredential");
        TimeUnit.SECONDS.sleep(1);

        List<CredentialPage.CredentialFormTest> credentialFormTestList = new ArrayList<>();
        credentialFormTestList.add(new CredentialPage.CredentialFormTest("", "fg", "dfg", "fdg"));
        credentialFormTestList.add(new CredentialPage.CredentialFormTest("", "dfg", "dfg", "fgfd"));
        credentialFormTestList.add(new CredentialPage.CredentialFormTest("", "fdgdf", "fg", "fgfd"));

        credentialPage.addListCredentials(credentialFormTestList);


        List<CredentialPage.CredentialFormTest> showListCredentials = credentialPage.displayedCredentials();
        List<CredentialPage.CredentialFormTest> editListCredentials = new ArrayList<>();

        for (int i = 0; i < showListCredentials.size(); i++) {
            String resultDisplayPassword = credentialPage.getShowedPasswordForCredentialId(showListCredentials.get(i).getId());

            String expectedPassword = credentialFormTestList.get(i).getPassword();
            assertEquals(expectedPassword, resultDisplayPassword);
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
            String resultShowedPassword = credentialPage.getShowedPasswordForCredentialId(displayedCredential.getId());

            String expectedPassword = "";
            assertEquals(expectedPassword + i, resultShowedPassword);

            String expectedUsername = "";
            String resultUsername = displayedCredential.getUsername();
            assertEquals(expectedUsername + i, resultUsername);

            String expectedUrl = "";
            String resultUrl = displayedCredential.getUrl();
            assertEquals(expectedUrl + i, resultUrl);
        }


    }

    @Test
    public void testRemoveCredentials() throws InterruptedException {
        // Signup
        getSignup("DeleteCredential");
        TimeUnit.SECONDS.sleep(1);


        List<CredentialPage.CredentialFormTest> credentialFormTestList = new ArrayList<>();
        credentialFormTestList.add(new CredentialPage.CredentialFormTest("dfg", "dfg", "fg", "fdg"));
        credentialFormTestList.add(new CredentialPage.CredentialFormTest("fdg", "dfg", "fg", "fg"));
        credentialFormTestList.add(new CredentialPage.CredentialFormTest("dfg", "dfg", "fdg", "fdg"));

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


    private void getSignup(String username) {
        // Signup
        signupPage.signUp("Mona", "Lisa", username, "random");

        //Login
        String argumentLocalHostLogin = Constants.LOCAL_HOST + port + Constants.LOGIN_SLASH;
        driver.get(argumentLocalHostLogin);
        loginPage.getLogin(username, "random");

        //To the Home
        String argumentLocalHostHome = Constants.LOCAL_HOST + port + Constants.HOME_SLASH;
        driver.get(argumentLocalHostHome);
        wait = new WebDriverWait(driver, 3);
        WebElement homeMarker = wait.until(
                webDriver -> webDriver.findElement(By.id("nav-files-tab")));
        assertNotNull(homeMarker);
    }

}
