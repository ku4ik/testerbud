package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;

public abstract class BaseTest {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected final ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return driver.get();
    }

    protected WebDriverWait getWait() {
        return wait.get();
    }

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {
        try {
            if (driver.get() == null) {
                driver.set(WebDriverFactory.createDriver(browser));
                driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                wait.set(new WebDriverWait(driver.get(), Duration.ofSeconds(10)));
            }
        } catch (Exception e) {
            org.testng.Assert.fail("Driver initialization failed for browser: " + browser + ". Reason: " + e.getMessage(), e);
        }
    }

    @AfterMethod
    public void tearDown() {
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            currentDriver.quit();
        }
        driver.remove();
        wait.remove();
    }
}
