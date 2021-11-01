package com.udacity.jwdnd.course1.cloudstorage.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;


public class HomePage {

    @FindBy(id = "logout-button")
    private WebElement logoutBtn;

    @FindBy(id = "nav-files-tab")
    private WebElement filesTab;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "add-note-button")
    private WebElement addNoteButton;

    @FindBy(id = "add-credential-button")
    private WebElement addCredentialButton;

    @FindBy(id = "close-credential-edit-button")
    private WebElement closeCredentialEditButton;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "credential-save-button")
    private WebElement credentialSaveButton;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "save-note-button")
    private WebElement saveNoteButton;

    @FindBy(id = "edit-note-button")
    private WebElement editNoteButton;

    @FindBy(id = "edit-credential-button")
    private WebElement editCredentialButton;

    @FindBy(id = "delete-note-button")
    private WebElement deleteNoteButton;

    private WebDriver webDriver;
    private WebDriverWait wait;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }



    public void createNote(String title, String description) throws InterruptedException {
        getDisplayedNotesTab();
        Thread.sleep(2000);

        addNoteButton.click();
        Thread.sleep(2000);

        noteTitle.sendKeys(title);

        noteDescription.sendKeys(description);

        saveNoteButton.click();
        Thread.sleep(2000);

    }

    public String getNoteTitle() {
        return webDriver.findElement(By.id("show-note-title")).getText();
    }

    public String getNoteDescription() {
        return webDriver.findElement(By.id("show-note-description")).getText();
    }


    public void editNote(String title, String description) throws InterruptedException {
        getDisplayedNotesTab();
        Thread.sleep(2000);

        editNoteButton.click();
        Thread.sleep(3000);

        noteTitle.clear();
        noteDescription.clear();

        noteTitle.sendKeys(title);
        noteDescription.sendKeys(description);

        saveNoteButton.click();
        Thread.sleep(2000);


    }

    public void removeNote() throws InterruptedException {
        getDisplayedNotesTab();
        Thread.sleep(2000);

        deleteNoteButton.click();
        Thread.sleep(2000);
    }

    public void createCredential(CredentialForTest credential) throws InterruptedException {
        getDisplayCredentialsTab();
        Thread.sleep(2000);

        addCredentialButton.click();
        Thread.sleep(2000);

        credentialUrl.sendKeys(credential.getUrl());
        credentialUsername.sendKeys(credential.getUsername());
        credentialPassword.sendKeys(credential.getPass());

        credentialSaveButton.click();
        Thread.sleep(2000);
    }

    public void editCredential(CredentialForTest credential) throws InterruptedException {
        getDisplayCredentialsTab();
        Thread.sleep(2000);

        WebElement button = webDriver.findElement(By.id(credential.getId()));
        WebElement editBtn = button.findElement(By.id("edit-credential-button"));
        editBtn.click();
        Thread.sleep(2000);

        credentialUrl.clear();
        credentialUsername.clear();
        credentialPassword.clear();
        credentialUsername.sendKeys(credential.getUrl());
        credentialUsername.sendKeys(credential.getUsername());
        credentialPassword.sendKeys(credential.getPass());

        credentialSaveButton.click();
        Thread.sleep(2000);
    }

    public void removeCredential(CredentialForTest credential) throws InterruptedException {
        String id = credential.getId();
        getDisplayCredentialsTab();
        Thread.sleep(2000);

        WebElement button = webDriver.findElement(By.id(id));
        WebElement deleteBtn = button.findElement(By.id("delete-credential-button"));

        deleteBtn.click();
        Thread.sleep(2000);
    }


    public void getDisplayedNotesTab() {
        webDriver.manage().window().maximize();
        notesTab.click();
    }

    public void getDisplayCredentialsTab() {
        webDriver.manage().window().maximize();
        credentialsTab.click();
    }
    public void logout() {
        logoutBtn.click();
    }


    public static class CredentialForTest {
        private String id;
        private String url;
        private String username;
        private String pass;


        public CredentialForTest(String id, String url, String username, String pass) {
            this.id = id;
            this.url = url;
            this.username = username;
            this.pass = pass;
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

        public String getPass() {
            return pass;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CredentialForTest that = (CredentialForTest) o;
            return Objects.equals(id, that.id) && Objects.equals(url, that.url) && Objects.equals(username, that.username) && Objects.equals(pass, that.pass);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, url, username, pass);
        }
    }


}
