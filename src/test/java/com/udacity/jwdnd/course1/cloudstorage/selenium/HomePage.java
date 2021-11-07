package com.udacity.jwdnd.course1.cloudstorage.selenium;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id = "logout-button")
    private WebElement logoutBtn;


    private WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        // this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void logoutUser() {
        logoutBtn.click();

    }


}
