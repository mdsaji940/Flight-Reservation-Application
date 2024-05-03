package com.SkTravelingFlightService.controller;

import com.SkTravelingFlightService.entity.Flight;
import com.SkTravelingFlightService.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;
    @PostMapping("/admin/add-flight")
    public Flight addFlights(Flight flight){
        return flightService.addFlight(flight);
    }

    @GetMapping("/admin/get-flights")
    public List<Flight> getAllFlights(){
        return flightService.getAllFlights();
    }
}
