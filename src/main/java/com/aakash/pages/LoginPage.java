package com.aakash.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private By usernameField = By.id("userEmail");
    private By passwordField = By.id("userPassword");
    private By loginButton = By.id("login");
    private By homeTab = By.xpath("//button[normalize-space()='HOME']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    
    public String verifyHomeTab() {
    	return driver.findElement(homeTab).getText();
    }
}
