package com.SkTravelingFlightService.service;

import com.SkTravelingFlightService.entity.FlightDetails;

import java.util.List;

public interface FlightService {
    FlightDetails addFlight(FlightDetails flightDetails);
    List<FlightDetails> getAllFlights();

    FlightDetails updateFlight(FlightDetails flight);
}
