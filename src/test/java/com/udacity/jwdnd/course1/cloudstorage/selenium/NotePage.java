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

    @FindBy(id = "nav-notes")
    private WebElement btnNoteModal;

    @FindBy(id = "show-note-title")
    private WebElement noteTitle;

    @FindBy(id = "show-note-description")
    private WebElement noteDescription;

    @FindBy(id = "save-note-button")
    private WebElement btnAddNote;

    @FindBy(className = "edit-note")
    private WebElement btnEditNote;

    @FindBy(className = "delete-note-button")
    private WebElement btnDeleteNote;

    private WebDriverWait wait;


    public NotePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getNoteElement(WebDriver driver, String cssSelector) {
        return driver.findElement(By.cssSelector(".list-note-item" + cssSelector));
    }

    public void addNote(WebDriver driver, String titleNote, String descriptionNote) {
        noteTitle.clear();
        noteDescription.clear();

        noteTitle.sendKeys(titleNote);
        noteDescription.sendKeys(descriptionNote);

        wait.until(ExpectedConditions.elementToBeClickable(btnAddNote)).click();


    }

    public void clickNotesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(navNotesTab)).click();

    }


    public void clickAddNoteModalButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btnNoteModal)).click();

    }


    public void clickEditNoteButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btnEditNote)).click();

    }


    public void clickDeleteNoteButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btnDeleteNote)).click();

    }

}
