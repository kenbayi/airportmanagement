package kz.aitu.airportmanagementsystem.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
@Data
@Entity
@Table(name = "flightdetails")
@Accessors(chain = true)
//Flight details from table in database
public class FlightDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int flightId;

    @Column(name = "flight_number", nullable = false)
    private String flightNumber;

    @Column(name = "departure_location", nullable = false)
    private String departureLocation;

    @Column(name = "destination_location", nullable = false)
    private String destinationLocation;

    @Column(name = "departure_time", nullable = false)
    private Timestamp departureTime;

    @Column(name = "arrival_time", nullable = false)
    private Timestamp arrivalTime;

    @Column(name = "available_seats", nullable = false)
    private int availableSeats;
}
