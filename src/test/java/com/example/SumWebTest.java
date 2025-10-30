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
        // ✅ Specify exact EdgeDriver path for Jenkins
        System.setProperty("webdriver.edge.driver", "C:\\WebDrivers\\msedgedriver.exe");

        EdgeOptions options = new EdgeOptions();

        // ✅ Essential headless + Jenkins-safe arguments
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");

        driver = new EdgeDriver(options);
    }

    @Test
    public void testSum() throws InterruptedException {
        // ✅ Use full path to your HTML file inside Jenkins workspace
        String url = "file:///C:/ProgramData/Jenkins/.jenkins/workspace/SeleniumWebSumTest/src/test/resources/sum.html";
        driver.get(url);

        // Wait for elements to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("num1")));

        // Locate elements
        WebElement num1 = driver.findElement(By.id("num1"));
        WebElement num2 = driver.findElement(By.id("num2"));
        WebElement calcBtn = driver.findElement(By.id("calcBtn"));
        WebElement result = driver.findElement(By.id("result"));

        // Enter values and click
        num1.sendKeys("10");
        num2.sendKeys("20");
        calcBtn.click();

        // Wait for JS update
        Thread.sleep(1000);

        String output = result.getText().trim();
        System.out.println("Output: " + output);

        // Verify result
        assertEquals("Sum = 30", output);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

