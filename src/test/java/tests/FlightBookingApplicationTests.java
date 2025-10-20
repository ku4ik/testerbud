package tests;


import org.testng.annotations.*;
import pages.FlightBookingApplicationPage;
import tests.dto.FlightSearchData;
import utils.BaseTest;

import static utils.TestConstants.BOOKING_SUCCESS_MESSAGE;

public class FlightBookingApplicationTests extends BaseTest {
    private FlightBookingApplicationPage flightBookingApplicationPage;

    @Override
    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional String browser) {
        if (browser == null || browser.isEmpty()) {
            browser = System.getProperty("browser", "chrome");
        }
        super.setUp(browser);

        flightBookingApplicationPage = new FlightBookingApplicationPage(getDriver(), getWait());
    }

    @Test
    public void testSearchFlightsFormInteractions() {
        getDriver().get("https://testerbud.com/flight-booking-scenarios");

        // Use DTO (record) for test data
        FlightSearchData data = FlightSearchData.flightSearchRecordData();

        //  Find Your Perfect Flight form
        // Set data
        flightBookingApplicationPage.selectFrom(data.fromCity());
        flightBookingApplicationPage.selectTo(data.toCity());
        flightBookingApplicationPage.setDepartureDate(data.departureDate());
        flightBookingApplicationPage.setReturnDate(data.returnDate());
        flightBookingApplicationPage.setPassengers(data.passengers());
        flightBookingApplicationPage.selectTravelClass(data.travelClass());
        flightBookingApplicationPage.setOneWay(data.oneWay());

        flightBookingApplicationPage.clickSearchFlights();

        // select first record in the list
        flightBookingApplicationPage.clickAvailableFlightsRecord1();

        // Set Payment details
        flightBookingApplicationPage.setPaymentDetailsCardNumberInput("1111222233334444");
        flightBookingApplicationPage.setPaymentDetailsExpiryDateInput("10/27");
        flightBookingApplicationPage.setPaymentDetailsCvvInput("111");
        flightBookingApplicationPage.clickBookFlightButton();

        // Check Booking status
        flightBookingApplicationPage.assertBookingStatusValue(BOOKING_SUCCESS_MESSAGE);
    }
}
