package com.udacity.jwdnd.course1.cloudstorage.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotePage {

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "logout-button")
    private WebElement logoutButton;


    @FindBy(id = "nav-notes")
    private WebElement btnNoteModal;

    @FindBy(id = "show-note-title")
    private WebElement noteTitle;

    @FindBy(id = "show-note-description")
    private WebElement noteDescription;

    @FindBy(id = "save-note-button")
    private WebElement saveNoteButton;

    @FindBy(id = "add-note-button")
    private WebElement addNoteButton;

    @FindBy(id = "edit-note-button")
    private WebElement editNoteButton;

    @FindBy(className = "delete-note-button")
    private WebElement deleteNoteButton;

    private WebDriver webDriver;


    public NotePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void clickNoteTab() {
        webDriver.manage().window().maximize();
        navNotesTab.click();
    }

    public void logout() {
        logoutButton.click();
    }


    public void addNote(String titleNote, String descriptionNote) throws InterruptedException {
        clickNoteTab();
        Thread.sleep(2000);

        addNoteButton.click();
        Thread.sleep(2000);

        noteTitle.clear();
        noteDescription.clear();

        noteTitle.sendKeys(titleNote);
        noteDescription.sendKeys(descriptionNote);
        saveNoteButton.click();
        Thread.sleep(2000);

    }

    public String getFirstNoteTitle() {
        return webDriver.findElement(By.id("show-note-title")).getText();
    }

    public String getFirstNoteText() {
        return webDriver.findElement(By.id("show-note-description")).getText();
    }


    public void editNote(String title, String text) throws InterruptedException {
        clickNoteTab();
        Thread.sleep(2000);

        editNoteButton.click();
        Thread.sleep(3000);

        noteTitle.clear();
        noteDescription.clear();

        noteTitle.sendKeys(title);
        noteDescription.sendKeys(text);

        saveNoteButton.click();
        Thread.sleep(2000);
    }

    public void deleteNote() throws InterruptedException {
        clickNoteTab();
        Thread.sleep(2000);

        deleteNoteButton.click();
        Thread.sleep(2000);

    }


    //public WebElement getNoteElement(WebDriver driver, String cssSelector) {
    //  return driver.findElement(By.cssSelector(".list-note-item" + cssSelector));
    //}

}
