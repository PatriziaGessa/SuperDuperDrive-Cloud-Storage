package com.udacity.jwdnd.course1.cloudstorage.selenium;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.utils.Constants;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


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
    private WebElement credentialUsernameText;

    @FindBy(id = "credential-password")
    private WebElement credentialPasswordText;

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

    public void logout() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        logoutBtn.click();
    }

    public void openNotesTab() {
        webDriver.manage().window().maximize();
        notesTab.click();
    }

    public void openCredentialsTab() throws InterruptedException {
        webDriver.manage().window().maximize();
        credentialsTab.click();
    }

    public void createNote(String title, String desc) throws InterruptedException {
        // openNotesTab();
        this.noteTitle.click();
        Thread.sleep(2000);

        this.wait.until(ExpectedConditions.elementToBeClickable(By.id("add-note")));
        addNoteButton.click();
        Thread.sleep(3000);
        noteTitle.sendKeys(title);
        noteDescription.sendKeys(desc);
        saveNoteButton.click();
        Thread.sleep(2000);
    }

    public void editNote(String title, String desc) throws InterruptedException {
        openNotesTab();
        Thread.sleep(2000);

        editNoteButton.click();
        Thread.sleep(3000);
        noteTitle.clear();
        noteDescription.clear();
        noteTitle.sendKeys(title);
        noteDescription.sendKeys(desc);
        saveNoteButton.click();
        Thread.sleep(20000);


    }

    public void deleteNote() throws InterruptedException {
        openNotesTab();
        Thread.sleep(2000);
        deleteNoteButton.click();
        Thread.sleep(2000);

    }


    public void createCredential(TestCredential credential) throws InterruptedException {
        openNotesTab();
        Thread.sleep(2000);
        addCredentialButton.click();
        Thread.sleep(2000);
        credentialUrl.sendKeys(credential.getUsername());
        credentialUsernameText.sendKeys(credential.getUsername());
        credentialPasswordText.sendKeys(credential.getPass());
        credentialSaveButton.click();
        Thread.sleep(2000);
    }

    public void editCredential(TestCredential credential) throws InterruptedException {
        openCredentialsTab();
        Thread.sleep(2000);
        WebElement buttonsTd = webDriver.findElement(By.xpath("//*[@id='credentialTable']/tbody/tr/td[1]/button"));
        WebElement editButton = buttonsTd.findElement(By.id("edit-credential-button"));
        // editButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(editButton));
        Thread.sleep(3000);

        credentialUrl.clear();
        credentialUrl.sendKeys(credential.getUrl());

        credentialUsernameText.clear();
        credentialUsernameText.sendKeys(credential.getUsername());

        credentialPasswordText.clear();
        credentialPasswordText.sendKeys(credential.getPass());

        credentialSaveButton.click();
        Thread.sleep(1000);


    }

    public void deleteCredential(TestCredential credential) throws InterruptedException {
        String id = credential.getId();

        Thread.sleep(2000);
        openCredentialsTab();
        WebElement button = webDriver.findElement(By.id(id));
        WebElement deleteButton = webDriver.findElement(By.id("delete-credential-button"));
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButton.click();
        Thread.sleep(2000);

    }

    public void deleteListCredentials(List<TestCredential> credentials) throws InterruptedException {
        for (TestCredential credential : credentials) {
            deleteCredential(credential);

        }

    }

    public static class TestCredential {
        private String id;
        private String url;
        private String username;
        private String pass;


        public TestCredential(String id, String url, String username, String pass) {
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
    }
}
