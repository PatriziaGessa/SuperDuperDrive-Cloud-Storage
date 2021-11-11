package com.udacity.jwdnd.course1.cloudstorage.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;


public class CredentialPage {

    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;

    @FindBy(id = "close-credential-edit-button")
    private WebElement closeCredentialEditButton;

    @FindBy(id = "add-credential-button")
    // btnCredentialModal
    private WebElement addCredentialButton;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "credential-save-button")
    private WebElement credentialSaveButton;

    @FindBy(className = "edit-credential-button")
    private WebElement editCredentialButton;

    @FindBy(className = "delete-credential-button")
    private WebElement deleteCredentialButton;


    private WebDriver webDriver;

    public CredentialPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    // goToCredentialsTab()
    public void clickCredentialTab() throws InterruptedException {
        webDriver.manage().window().maximize();
        navCredentialsTab.click();

    }

    public void clickEditCredentialButton() {
        editCredentialButton.click();

    }


    public void clickDeleteCredentialButton() {
        deleteCredentialButton.click();

    }

    public void logout() {
        logoutButton.click();
    }

    public void clickAddCredential() {
        addCredentialButton.click();

    }

    public List<CredentialFormTest> displayedCredentials() throws InterruptedException {
        clickCredentialTab();
        Thread.sleep(2000);

        WebElement table = webDriver.findElement(By.id("credentialTable"));
        List<String> ids = new ArrayList<>();
        List<WebElement> listUrl = table.findElements(By.id("show-credential-url"));
        for (WebElement url : listUrl) {
            System.out.println("TAG" + url.getAttribute("tag"));
        }

        listUrl.forEach(
                url -> ids.add(url.getAttribute("tag")));
        List<WebElement> usernames = table.findElements(By.id("show-credential-username"));
        List<WebElement> passwords = table.findElements(By.id("show-credential-password"));

        List<CredentialFormTest> listCredentialTests = new ArrayList<>();
        for (int i = 0; i < listUrl.size(); i++) {
            listCredentialTests.add(new CredentialFormTest(
                    ids.get(i),
                    listUrl.get(i).getText(),
                    usernames.get(i).getText(),
                    passwords.get(i).getText()));
        }
        return listCredentialTests;
    }


    public void addCredential(CredentialFormTest credentialFormTest) throws InterruptedException {
        clickCredentialTab();
        Thread.sleep(2000);

        addCredentialButton.click();
        Thread.sleep(2000);

        credentialUrl.clear();
        credentialUsername.clear();
        credentialPassword.clear();

        credentialUrl.sendKeys(credentialFormTest.getUrl());
        credentialUsername.sendKeys(credentialFormTest.getUsername());
        credentialPassword.sendKeys(credentialFormTest.getPassword());

        credentialSaveButton.click();
        Thread.sleep(2000);


    }


    public void addListCredentials(List<CredentialFormTest> credentialForms) throws InterruptedException {
        for (CredentialFormTest credential : credentialForms) {
            addCredential(credential);
        }
    }

    public void editCredential(CredentialFormTest credential) throws InterruptedException {
        clickCredentialTab();
        Thread.sleep(2000);

        WebElement buttonsTd = webDriver.findElement(By.id(credential.getId()));
        WebElement editButton = buttonsTd.findElement(By.id("edit-credential-button"));
        editButton.click();
        Thread.sleep(3000);

        credentialUrl.clear();
        credentialUrl.sendKeys(credential.getUrl());

        credentialUsername.clear();
        credentialUsername.sendKeys(credential.getUsername());

        credentialPassword.clear();
        credentialPassword.sendKeys(credential.getPassword());

        credentialSaveButton.click();
        Thread.sleep(2000);

    }


    public void editListCredentials(List<CredentialFormTest> credentials) throws InterruptedException {
        for (CredentialFormTest credential : credentials) {
            editCredential(credential);
        }
    }


    public String getShowedPasswordForCredentialId(String id) throws InterruptedException {
        // System.out.println("Get password for id: " + id);
        clickCredentialTab();
        Thread.sleep(2000);

        WebElement buttonsTd = webDriver.findElement(By.id(id));
        WebElement editButton = buttonsTd.findElement(By.id("edit-credential-button"));
        editButton.click();
        Thread.sleep(2000);

        String password = credentialPassword.getAttribute("attribute");
        closeCredentialEditButton.click();
        Thread.sleep(2000);
        return password;
    }

    public void deleteCredential(CredentialFormTest credential) throws InterruptedException {
        String id = credential.getId();
        //System.out.println("Delete credential id: " + id);
        clickCredentialTab();
        Thread.sleep(2000);

        WebElement buttonsTd = webDriver.findElement(By.id(id));
        WebElement deleteButton = buttonsTd.findElement(By.id("delete-credential-button"));

        deleteButton.click();
        Thread.sleep(2000);
    }

    public void deleteListOfCredentials(List<CredentialFormTest> credentials) throws InterruptedException {
        for (CredentialFormTest credential : credentials) {
            deleteCredential(credential);
        }
    }


    public static class CredentialFormTest {
        private final String id;
        private final String url;
        private final String username;
        private final String password;

        public CredentialFormTest(String id, String url, String username, String password) {
            this.id = id;
            this.url = url;
            this.username = username;
            this.password = password;
        }

        public String getId() {
            return id;
        }


        public String getUrl() {
            return url;
        }


        public String getUsername() {
            return username;
        }


        public String getPassword() {
            return password;
        }


    }

    //public WebElement getCredentialElement(WebDriver driver, String cssSelector) {
    //    return driver.findElement(By.cssSelector(".list-credential-item " + cssSelector));
    //}


}
