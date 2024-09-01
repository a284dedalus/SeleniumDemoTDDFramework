package com.aakash.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aakash.base.BaseTest;
import com.aakash.pages.LoginPage;
import com.aventstack.extentreports.Status;

public class LoginTest extends BaseTest {
    @Test
    public void testValidLogin() {
        test = extent.createTest("testValidLogin");
        log.info("Starting valid login test1");
        log.info("Starting valid login test2");
        log.info("Starting valid login test3");
        //new
        log.info("Starting valid login test4");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(config.getProperty("username"));
        loginPage.enterPassword(config.getProperty("password"));
        loginPage.clickLoginButton();
        log.info(loginPage.verifyHomeTab());
        // Add assertions to verify successful login
        Assert.assertEquals(loginPage.verifyHomeTab(), "HOME");
        log.info("Completed valid login test");
        test.log(Status.PASS, "Valid login test passed");
    }
    
    @Test
    public void HomeVerify() {
        test = extent.createTest("HomeVerify");  
        log.info("Starting verify Home");  
    }
    
    @Test
    public void SearchVerify() {
        test = extent.createTest("SearchVerify");  
        log.info("Starting verify Search");  
    }
    
    @Test
    public void CartVerify() {
        test = extent.createTest("CartVerify");  
        log.info("Starting verify Cart");  
    }
    
    @Test
    public void LogOutVerify() {
        test = extent.createTest("LogOutVerify");  
        log.info("Starting verify LogOut");  
    }
}
