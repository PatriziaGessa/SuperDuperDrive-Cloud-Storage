package com.udacity.jwdnd.course1.cloudstorage.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialPage {

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;

    @FindBy(id = "add-credential-button")
    // btnCredentialModal
    private WebElement addCredentialBtn;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "credential-save-button")
    private WebElement saveCredentialBtn;

    @FindBy(className = "edit-credential-button")
    private WebElement editCredentialBtn;

    @FindBy(className = "delete-credential-button")
    private WebElement deleteCredentialBtn;




    public CredentialPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public void clickCredentialTab() {
        navCredentialsTab.click();

    }


    public void clickAddCredential() {
        addCredentialBtn.click();

    }


    public void addCredential(WebDriver driver, String url, String username, String password) {
        credentialUrl.clear();
        credentialUsername.clear();
        credentialPassword.clear();


        credentialUrl.sendKeys(url);
        credentialUsername.sendKeys(username);
        credentialPassword.sendKeys(password);

       addCredentialBtn.click();

    }




    public void clickEditCredentialButton() {
       editCredentialBtn.click();

    }


    public void clickDeleteCredentialButton() {
      deleteCredentialBtn.click();

    }


    public WebElement getCredentialElement(WebDriver driver, String cssSelector) {
        return driver.findElement(By.cssSelector(".list-credential-item " + cssSelector));
    }


}
