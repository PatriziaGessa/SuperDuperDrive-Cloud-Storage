package com.udacity.jwdnd.course1.cloudstorage.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "submit-button")
    private WebElement submit;

    @FindBy(id = "signup-link")
    private WebElement signup;

    @FindBy(id = "logout")
    private WebElement logout;

    @FindBy(id = "errorMsg")
    private WebElement errorMsg;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void login(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.submit.click();
    }

    public void goToSignup() {
        this.signup.click();
    }

    public void goToSignUpPage() {
        this.signup.click();
    }

    public String getErrorMsg() {
        return errorMsg.getText();
    }

    public String getLogoutMsg() {
        return logout.getText();
    }
}
