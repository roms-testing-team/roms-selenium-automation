package com.swt301.roms.base;

import com.swt301.roms.utils.ConfigReader;
import com.swt301.roms.utils.ScreenshotOnFailure;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {
    protected WebDriver driver;
    protected static final String BASE_URL = ConfigReader.get("base.url", "http://localhost:5173");

    @RegisterExtension
    ScreenshotOnFailure screenshotExtension = new ScreenshotOnFailure(() -> driver);

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        boolean isHeadless = Boolean.parseBoolean(ConfigReader.get("headless", "false"));
        if (isHeadless) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--window-size=1366,768");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
