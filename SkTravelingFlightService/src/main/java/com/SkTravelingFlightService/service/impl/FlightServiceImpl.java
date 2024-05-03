package com.SkTravelingFlightService.service.impl;

import com.SkTravelingFlightService.entity.Flight;
import com.SkTravelingFlightService.repository.FlightRepository;
import com.SkTravelingFlightService.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public Flight addFlight(Flight flight){
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

}
