package com.example;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class SumWebTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Tell Selenium where Edge WebDriver is located
        System.setProperty("webdriver.edge.driver", "C:\\WebDrivers\\msedgedriver.exe");

        // Configure Edge for Jenkins-friendly headless mode
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless"); // run without UI
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--allow-file-access-from-files");
        options.addArguments("--disable-software-rasterizer");

        driver = new EdgeDriver(options);
    }

    @Test
    public void testSum() throws InterruptedException {
        // Load your local HTML test file
        String url = "file:///C:/ProgramData/Jenkins/.jenkins/workspace/SeleniumWebSumTest/src/test/resources/sum.html";
        driver.get(url);

        // Wait for the page to load the input fields
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("num1")));

        // Locate HTML elements
        WebElement num1 = driver.findElement(By.id("num1"));
        WebElement num2 = driver.findElement(By.id("num2"));
        WebElement calcBtn = driver.findElement(By.id("calcBtn"));
        WebElement result = driver.findElement(By.id("result"));

        // Perform the test: enter numbers and click
        num1.sendKeys("10");
        num2.sendKeys("20");
        calcBtn.click();

        // Wait briefly for JS to update
        Thread.sleep(1000);

        // Capture and print result
        String output = result.getText().trim();
        System.out.println("Output: " + output);

        // Verify expected output
        assertEquals("Sum = 30", output);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

