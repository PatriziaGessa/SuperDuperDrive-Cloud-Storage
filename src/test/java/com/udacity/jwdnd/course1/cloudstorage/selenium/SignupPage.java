package com.udacity.jwdnd.course1.cloudstorage.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement submitBtn;



    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void signUp(String firstname, String lastname, String username, String password)  {
        inputFirstName.clear();
        inputLastName.clear();
        inputUsername.clear();
        inputPassword.clear();

        inputFirstName.sendKeys(firstname);
        inputLastName.sendKeys(lastname);
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);

        submitBtn.click();



    }





}
