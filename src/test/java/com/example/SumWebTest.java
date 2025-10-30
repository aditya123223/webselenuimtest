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
        // ✅ Specify Edge WebDriver path
        System.setProperty("webdriver.edge.driver", "C:\\WebDrivers\\msedgedriver.exe");

        // ✅ Use Edge in headless mode for Jenkins
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--allow-file-access-from-files");

        driver = new EdgeDriver(options);
    }

    @Test
    public void testSum() throws InterruptedException {
        String url = "file:///
