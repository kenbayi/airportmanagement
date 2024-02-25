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

    public FlightDetailsService(FlightDetailsRepositoryInterface repositoryInterface) {
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public List<FlightDetails> getAllFlights() {
        return repositoryInterface.findAll(Sort.by(Sort.Direction.ASC, "departureTime"));
    }

    @Override
    public List<FlightDetails> findFlightDetailsByFlightNumberIgnoreCase(String flightNumber) {
        return repositoryInterface.findFlightDetailsByFlightNumberIgnoreCase(flightNumber);
    }

    @Override
    public FlightDetails CreateFlightDetails(FlightDetails flightDetails) {
        return repositoryInterface.save(flightDetails);
    }

    @Override
    @Transactional
    public Long deleteFlightDetailsByFlightNumberIgnoreCase(String flightNumber) {
        return repositoryInterface.deleteFlightDetailsByFlightNumberIgnoreCase(flightNumber);
    }

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
