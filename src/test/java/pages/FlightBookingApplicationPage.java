package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static org.openqa.selenium.By.id;
import static org.testng.AssertJUnit.assertEquals;

public class FlightBookingApplicationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Find Your Perfect Flight form
    private final By from = id("from");
    private final By to = id("to");
    private final By departureDate = id("departureDate");
    private final By returnDate = id("returnDate");
    private final By passengers = id("passengers");
    private final By travelClass = id("travelClass");
    private final By oneWay = id("oneWay");
    private final By searchButton = By.cssSelector("button.w-100.btn.btn-primary");

    // Available Flights
    private final By availableFlightsRecord1 = By.xpath("//h3/following::button[1]");

    // Payment Details
    private final By paymentDetailsCardNumberInput = By.id("cardNumber");
    private final By paymentDetailsExpiryDateInput = By.id("expiryDate");
    private final By paymentDetailsCvvInput = By.id("cvv");
    private final By bookFlightButton = By.xpath("//button[text()='Book Flight']");

    // Booking Status from
    private final By bookingStatusLabel = By.xpath("//h4");


    public FlightBookingApplicationPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Find Your Perfect Flight form
    public void selectFrom(String city) {
        wait.until(ExpectedConditions.elementToBeClickable(from));
        new Select(driver.findElement(from)).selectByVisibleText(city);
    }

    public void selectTo(String city) {
        wait.until(ExpectedConditions.elementToBeClickable(to));
        new Select(driver.findElement(to)).selectByVisibleText(city);
    }

    public void setDepartureDate(String date) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(departureDate));
        el.clear();
        el.sendKeys(date);
    }

    public void setReturnDate(String date) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(returnDate));
        el.clear();
        el.sendKeys(date);
    }

    public void setPassengers(int count) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(passengers));
        el.clear();
        el.sendKeys(String.valueOf(count));
    }

    public void selectTravelClass(String clazz) {
        wait.until(ExpectedConditions.elementToBeClickable(travelClass));
        new Select(driver.findElement(travelClass)).selectByVisibleText(clazz);
    }

    public void setOneWay(boolean enabled) {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(oneWay));
        if (checkbox.isSelected() != enabled) {
            checkbox.click();
        }
    }

    public void clickSearchFlights() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        scrollToElement(searchButton);

        driver.findElement(searchButton).click();
    }

    public void clickAvailableFlightsRecord1() {
        wait.until(ExpectedConditions.elementToBeClickable(availableFlightsRecord1));
        scrollToElement(availableFlightsRecord1);

        driver.findElement(availableFlightsRecord1).click();
    }

    //Payment Details
    public void setPaymentDetailsCardNumberInput(String cardNumber) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(paymentDetailsCardNumberInput));
        el.clear();
        el.sendKeys(cardNumber);
    }

    public void setPaymentDetailsExpiryDateInput(String expiryDate) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(paymentDetailsExpiryDateInput));
        el.clear();
        el.sendKeys(expiryDate);
    }

    public void setPaymentDetailsCvvInput(String cvv) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(paymentDetailsCvvInput));
        el.clear();
        el.sendKeys(cvv);
    }

    public void clickBookFlightButton() {
        wait.until(ExpectedConditions.elementToBeClickable(bookFlightButton)).click();
    }

    // Booking Status from
    public void assertBookingStatusValue(String expected) {
        String actual = driver.findElement(bookingStatusLabel).getText();
        assertEquals("Booking Status mismatch", expected, actual);
    }

    public void scrollToElement(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement( driver.findElement(locator)).pause(Duration.ofMillis(100)).perform();
    }
}

