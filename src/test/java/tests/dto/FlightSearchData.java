package tests.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record FlightSearchData(
        String fromCity,
        String toCity,
        String departureDate,
        String returnDate,
        int passengers,
        String travelClass,
        boolean oneWay
) {
    public static FlightSearchData flightSearchRecordData() {
        LocalDate today = LocalDate.now();
        LocalDate dep = today.plusDays(1);
        LocalDate ret = today.plusDays(4);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return new FlightSearchData(
                "Paris",
                "New York",
                dep.format(fmt),
                ret.format(fmt),
                1,
                "Economy",
                true
        );
    }
}
