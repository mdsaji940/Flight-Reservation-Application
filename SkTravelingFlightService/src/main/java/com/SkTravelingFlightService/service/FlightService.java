package com.SkTravelingFlightService.service;

import com.SkTravelingFlightService.entity.Flight;

import java.util.List;

public interface FlightService {
    Flight addFlight(Flight flight);
    List<Flight> getAllFlights();
}
