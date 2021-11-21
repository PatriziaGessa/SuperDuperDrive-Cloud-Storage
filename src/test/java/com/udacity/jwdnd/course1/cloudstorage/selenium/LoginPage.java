package com.udacity.jwdnd.course1.cloudstorage.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "signup-link")
    private WebElement signup;


    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void clickSignup() {
        signup.click();
    }


    public void login(String username, String password)  {
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        submitButton.click();



    }

    public void getAuthenticateUser(String username, String pass)  {
        inputUsername.clear();
        inputPassword.clear();

        inputUsername.sendKeys(username);
        inputPassword.sendKeys(pass);

        submitButton.click();


    }
}