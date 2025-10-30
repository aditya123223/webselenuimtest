package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import static org.junit.Assert.*;

public class SumWebTest {
    private WebDriver driver;
    
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-software-rasterizer");
        
        String userDataDir = System.getProperty("java.io.tmpdir") + "chrome-test-" + System.currentTimeMillis();
        options.addArguments("--user-data-dir=" + userDataDir);
        
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    
    @Test
    public void testSum() {
        // Test with Google homepage
        driver.get("https://www.google.com");
        
        String title = driver.getTitle();
        System.out.println("Page title: " + title);
        
        assertNotNull("Title should not be null", title);
        assertTrue("Title should contain Google", title.contains("Google"));
        
        System.out.println("Test passed successfully!");
    }
    
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
