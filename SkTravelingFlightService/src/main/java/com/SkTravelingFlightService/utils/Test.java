package com.SkTravelingFlightService.utils;

import com.SkTravelingFlightService.entity.FlightDetails;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        FlightDetails flightDetails = new FlightDetails();
        flightDetails.setFlightNumber("ABC123");

        flightDetails.setDepartureDateTime(LocalDateTime.of(2024, 5, 6, 12, 0)); // Assuming departure date is 6th May 2024 at 12:00 PM

        System.out.println("Flight ID: " + flightDetails.getFlightId());
    }
}
