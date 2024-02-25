package kz.aitu.airportmanagementsystem.services;

import jakarta.transaction.Transactional;
import kz.aitu.airportmanagementsystem.models.FlightDetails;
import kz.aitu.airportmanagementsystem.repositories.FlightDetailsRepositoryInterface;
import kz.aitu.airportmanagementsystem.services.interfaces.FlightDetailsServiceInterface;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FlightDetailsService implements FlightDetailsServiceInterface {
    private final FlightDetailsRepositoryInterface repositoryInterface;

    // Constructor injection for FlightDetailsRepositoryInterface
    public FlightDetailsService(FlightDetailsRepositoryInterface repositoryInterface) {
        this.repositoryInterface = repositoryInterface;
    }

    // Method to get all flights sorted by departure time
    @Override
    public List<FlightDetails> getAllFlights() {
        return repositoryInterface.findAll(Sort.by(Sort.Direction.ASC, "departureTime"));
    }

    // Method to find flight details by flight number
    @Override
    public List<FlightDetails> findFlightDetailsByFlightNumberIgnoreCase(String flightNumber) {
        return repositoryInterface.findFlightDetailsByFlightNumberIgnoreCase(flightNumber);
    }

    // Method to create a new flight details record
    @Override
    public FlightDetails CreateFlightDetails(FlightDetails flightDetails) {
        return repositoryInterface.save(flightDetails);
    }

    // Method to delete flight details by flight number
    @Override
    @Transactional
    public Long deleteFlightDetailsByFlightNumberIgnoreCase(String flightNumber) {
        return repositoryInterface.deleteFlightDetailsByFlightNumberIgnoreCase(flightNumber);
    }

    // Method to change the number of available seats for a flight
    @Override
    public FlightDetails changeSeats(Long flightId, int seats) {
        FlightDetails flightDetails = repositoryInterface.findById(flightId).orElse(null);
        if(flightDetails != null){
            flightDetails.setAvailableSeats(seats);
            return repositoryInterface.save(flightDetails);
        }
        else{
            return null;
        }
    }
}
