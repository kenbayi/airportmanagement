package kz.aitu.airportmanagementsystem.repositories;

import kz.aitu.airportmanagementsystem.models.FlightDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//Main methods
public interface FlightDetailsRepositoryInterface extends JpaRepository<FlightDetails, Long> {
    List<FlightDetails> findFlightDetailsByFlightNumberIgnoreCase(String flightNumber);
    Long deleteFlightDetailsByFlightNumberIgnoreCase(String flightNumber);
}
