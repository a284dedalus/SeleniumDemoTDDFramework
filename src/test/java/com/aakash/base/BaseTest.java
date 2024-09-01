package com.aakash.base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aakash.util.ConfigReader;
import com.aakash.util.ExtentManager;
import com.aakash.util.Log;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class BaseTest {
    protected WebDriver driver;
    protected Properties config;
    protected ExtentReports extent;
    protected ExtentTest test;
    protected Logger log;

    @BeforeTest
    public void setUp() {
        config = ConfigReader.loadProperties();
        extent = ExtentManager.getInstance();
        log = Log.getLogger(BaseTest.class);
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        String implicitWait = config.getProperty("implicitWait");
        int impwait = Integer.parseInt(implicitWait);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(impwait));
        driver.manage().window().maximize();
        driver.get(config.getProperty("url"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed");
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            String screenshotPath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + "screenshot_" + result.getName() + ".png";
            File dest = new File(screenshotPath);
            try {
                // Copy the screenshot to the destination file
                Files.copy(src.toPath(), dest.toPath());
                test.addScreenCaptureFromPath(screenshotPath);

                String logFilePath = System.getProperty("user.dir") + File.separator + "logs" + File.separator + "test-execution.log";
                System.out.println(logFilePath);
                
                // Attach the log file as plain text content
                if (Files.exists(Paths.get(logFilePath))) {
                    String logContent = new String(Files.readAllBytes(Paths.get(logFilePath)));
                    test.log(Status.INFO, "Log file contents:\n" + logContent);
                } else {
                    test.log(Status.INFO, "Log file not found.");
                }
                System.out.println("test");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        extent.flush();
    }

    @AfterTest
    public void cleanUp() {
        driver.quit();
    }
}
