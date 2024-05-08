package com.SkTravelingFlightService.service.impl;

import com.SkTravelingFlightService.entity.FlightDetails;
import com.SkTravelingFlightService.repository.FlightRepository;
import com.SkTravelingFlightService.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public FlightDetails addFlight(FlightDetails flightDetails){
        LocalDateTime departureDateTime = flightDetails.getDepartureDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        flightDetails.setFlightId(flightDetails.getFlightNumber()+ "-" +departureDateTime.format(formatter));
        System.out.println("*********** flightDetails : " + flightDetails);
        return flightRepository.save(flightDetails);
    }

    @Override
    public List<FlightDetails> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public FlightDetails updateFlight(FlightDetails flightDetails) {
        return flightRepository.save(flightDetails);
    }

}
