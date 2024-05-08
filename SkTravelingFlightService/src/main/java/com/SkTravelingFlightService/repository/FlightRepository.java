package com.SkTravelingFlightService.repository;

import com.SkTravelingFlightService.entity.FlightDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<FlightDetails, String> {
}
