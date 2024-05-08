package com.SkTravelingFlightService.controller;

import com.SkTravelingFlightService.entity.FlightDetails;
import com.SkTravelingFlightService.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;
    @PostMapping("/admin/add-flight")
    public FlightDetails addFlights(@RequestBody FlightDetails flightDetails){
        return flightService.addFlight(flightDetails);
    }

    @GetMapping("/admin/get-flights")
    public List<FlightDetails> getAllFlights(){
        return flightService.getAllFlights();
    }

    @PutMapping("/admin/update-flight")
    public FlightDetails updateFlight(@RequestBody FlightDetails flightDetails){
        return flightService.updateFlight(flightDetails);
    }
}
