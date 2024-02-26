package kz.aitu.airportmanagementsystem.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "reserve")
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reserveId; // Unique identifier for the reservation

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private FlightDetails flightDetails; // Associated flight details for this reservation

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private User user; // Associated user (passenger) for this reservation

    // Timestamp for when the reservation was made, automatically set to the current timestamp by the database
    @Column(name = "reserve_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Timestamp reserveDate; // Date and time when the reservation was made
}

