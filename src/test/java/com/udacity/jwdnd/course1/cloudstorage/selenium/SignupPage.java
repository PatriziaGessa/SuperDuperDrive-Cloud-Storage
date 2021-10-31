package com.udacity.jwdnd.course1.cloudstorage.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// TODO cercare su -> sakshee-19 il Success -msg + error -msg
public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstNameInput;

    @FindBy(id = "inputLastName")
    private WebElement lastNameInput;

    @FindBy(id = "inputUsername")
    private WebElement usernameInput;

    @FindBy(id = "inputPassword")
    private WebElement passwordInput;

    @FindBy(id = "success-msg")
    private WebElement success;

    @FindBy(id = "error-msg")
    private WebElement error;

    @FindBy(id = "submit-button")
    private WebElement submitBt;

    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void signUp(String firstname, String lastname, String username, String password) {
        firstNameInput.sendKeys(firstname);
        lastNameInput.sendKeys(lastname);
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitBt.submit();
    }


    public String getErrorMsg() {
        return error.getText();
    }

    public String getSuccessMsg() {
        return success.getText();
    }

    public void setErrorMsg(String msg) {
        error.sendKeys(msg);
    }

    public void setSuccessMsg(String msg) {
        success.sendKeys(msg);
    }

}
