package kz.aitu.airportmanagementsystem.services.interfaces;

import kz.aitu.airportmanagementsystem.models.FlightDetails;

import java.util.List;
//Main methods of flight service
public interface FlightDetailsServiceInterface {
    List<FlightDetails> getAllFlights();
    List<FlightDetails> findFlightDetailsByFlightNumberIgnoreCase(String flightNumber);
    FlightDetails CreateFlightDetails(FlightDetails flightDetails);
    Long deleteFlightDetailsByFlightNumberIgnoreCase(String flightNumber);
    FlightDetails changeSeats(Long flightId, int seats);
}
