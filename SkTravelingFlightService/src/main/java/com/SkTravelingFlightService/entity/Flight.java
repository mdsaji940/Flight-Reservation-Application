package com.SkTravelingFlightService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SK_FLIGHT")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;
    private String flightNumber;
    private String airline;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private Duration flightDuration;
    private double price;
    private int seatsAvailable;
    private int totalSeats;
    private String baggage;
    private String checkIn;
    private String cabin;
    private String aircraftType;
    private String status;
    private boolean isActive;
    private Integer createdBy;
    private LocalDateTime createdDate;
    private Integer modifiedBy;
    private LocalDateTime modifiedDate;

}
