package com.udacity.jwdnd.course1.cloudstorage.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement usernameInput;

    @FindBy(id = "inputPassword")
    private WebElement passwordInput;

    @FindBy(id = "submit-button")
    private WebElement submit;

    @FindBy(id = "signup-link")
    private WebElement signup;

    @FindBy(id = "logout")
    private WebElement logout;

    @FindBy(id = "errorMsg")
    private WebElement errorMsg;


    private WebDriverWait wait;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void getLogin(String username, String password) {
        // this.username.sendKeys(username);
        // this.password.sendKeys(password);
        // this.submit.click();
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput)).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(submit)).click();

    }

    public void goToSignup() {
        wait.until(ExpectedConditions.elementToBeClickable(signup)).click();
        // this.signup.click();

    }

    public void goToSignUpPage() {
        wait.until(ExpectedConditions.elementToBeClickable(signup)).click();
        // this.signup.click();
    }

    public String getErrorMsg() {
        return errorMsg.getText();
    }

    public String getLogoutMsg() {
        return logout.getText();
    }
}
