package com.udacity.jwdnd.course1.cloudstorage.selenium;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//TODO check the another codes
// - check the difference between the codes with the GitHub -> sakshee-19


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
    private WebElement credentialUrlText;

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

    public void createNote(String title, String desc) throws InterruptedException {
        openNotesTab();
        Thread.sleep(2000);
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


    public void openCredentialsTab() throws InterruptedException {
        webDriver.manage().window().maximize();
        credentialsTab.click();
    }

    public void createCredential(String url, String username, String pass) throws InterruptedException {
        openNotesTab();
        Thread.sleep(2000);
        addCredentialButton.click();
        Thread.sleep(2000);
        credentialUrlText.sendKeys(url);
        credentialUsernameText.sendKeys(username);
        credentialPasswordText.sendKeys(pass);
        credentialSaveButton.click();
        Thread.sleep(2000);
    }

    public void editCredential(String url, String user, String pass) throws InterruptedException {
        openCredentialsTab();
        Thread.sleep(2000);
        credentialUrlText.sendKeys(url);
        credentialUsernameText.clear();
        credentialUrlText.clear();
        credentialUsernameText.sendKeys(user);
        credentialPasswordText.sendKeys(pass);
        credentialSaveButton.click();
        Thread.sleep(1000);


    }




}
