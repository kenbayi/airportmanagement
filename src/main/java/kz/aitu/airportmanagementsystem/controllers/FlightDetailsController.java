package kz.aitu.airportmanagementsystem.controllers;

import jakarta.validation.Valid;
import kz.aitu.airportmanagementsystem.datatransferobject.FlightDetailsDto;
import kz.aitu.airportmanagementsystem.models.FlightDetails;
import kz.aitu.airportmanagementsystem.services.interfaces.FlightDetailsServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightDetailsController {
    private final FlightDetailsServiceInterface service;

    public FlightDetailsController(FlightDetailsServiceInterface service) {
        this.service = service;
    }

    @GetMapping("/admin/")
    public ResponseEntity<?> getAllFlights(){
        List<FlightDetails> flightDetailsList = service.getAllFlights();
        if(flightDetailsList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Flights there.");
        }
        return ResponseEntity.ok(flightDetailsList);
    }
    
    @GetMapping("/admin/find/{flight_number}")
    public ResponseEntity<?> findFlightDetailsByFlightNumber(@PathVariable("flight_number") String flightNumber) {
        List<FlightDetails> flightDetails = service.findFlightDetailsByFlightNumberIgnoreCase(flightNumber);

        if (flightDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight details not found for flight number: " + flightNumber);
        } else {
            return ResponseEntity.ok(flightDetails);
        }
    }

    @DeleteMapping("/admin/delete/{flight_number}")
    public ResponseEntity<?> deleteFlightDetailsByFlightNumber(@PathVariable("flight_number") String flightNumber) {
        Long deletedRecord = service.deleteFlightDetailsByFlightNumberIgnoreCase(flightNumber);
        if (deletedRecord == null || deletedRecord == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No flight details found for deletion with flight number: " + flightNumber);
        } else {
            return ResponseEntity.ok("Deleted " + deletedRecord + " flight details with flight number: " + flightNumber);
        }
    }

    @PostMapping("/admin/changeSeat/{flight_id}/{seats}")
    public ResponseEntity<?> ChangeSeats(@PathVariable("flight_id") Long flightId, @PathVariable("seats") int seats){
        FlightDetails updatedSeat = service.changeSeats(flightId, seats);
        if(updatedSeat == null)
            return new ResponseEntity<>("There is no flights with id " + flightId ,HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(updatedSeat, HttpStatus.OK);
    }
    //inserting flight details into database through validation of data using post method
    @PostMapping("/admin/create")
    public ResponseEntity<FlightDetails> CreateFlightDetails(@Valid @RequestBody FlightDetailsDto flightDetailsDto){
        FlightDetails createdflightDetails = service.CreateFlightDetails(flightDetailsDto.flightDetailsTo());
        if(createdflightDetails == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(createdflightDetails, HttpStatus.CREATED);
    }
}
