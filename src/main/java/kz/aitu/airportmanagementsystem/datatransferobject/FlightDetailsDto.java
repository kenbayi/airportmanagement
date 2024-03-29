package kz.aitu.airportmanagementsystem.datatransferobject;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import kz.aitu.airportmanagementsystem.datatransferobject.validation.ArrivalAfterDeparture;
import kz.aitu.airportmanagementsystem.models.FlightDetails;
import lombok.Data;

import java.sql.Timestamp;
@ArrivalAfterDeparture
@Data
public class FlightDetailsDto {
    // flight details model's data transfer object for validation.
    @NotEmpty(message = "flight number is required")
    private String flightNumber;

    @NotEmpty(message = "departure location is required")
    private String departureLocation;

    @NotEmpty(message = "destination location is required")
    private String destinationLocation;

    @Future
    private Timestamp departureTime;

    private Timestamp arrivalTime;

    @Positive(message = "positive integer")
    private int availableSeats;

    //to send data when saving
    public FlightDetails flightDetailsTo() {
        return new FlightDetails()
                .setFlightNumber(flightNumber)
                .setDepartureLocation(departureLocation)
                .setDestinationLocation(destinationLocation)
                .setDepartureTime(departureTime)
                .setArrivalTime(arrivalTime)
                .setAvailableSeats(availableSeats);
    }
}
