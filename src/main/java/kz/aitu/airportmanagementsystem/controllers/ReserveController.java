package kz.aitu.airportmanagementsystem.controllers;

import kz.aitu.airportmanagementsystem.models.Reserve;
import kz.aitu.airportmanagementsystem.services.interfaces.ReserveServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserve")
public class ReserveController {
    private final ReserveServiceInterface service;

    // Constructor to initialize the ReserveController with a ReserveServiceInterface implementation
    public ReserveController(ReserveServiceInterface service) {
        this.service = service;
    }

    // Endpoint to retrieve all reserves (accessible to admin)
    @GetMapping("/admin/")
    public List<Reserve> getAllReserve(){
        return service.getAllReserve();
    }

    // Endpoint to make a reservation (accessible to public)
    @PostMapping("/public/makeReservation")
    public ResponseEntity<String> makeReservation(@RequestParam Long flightId, @RequestParam String phoneNumber) {
        // Attempt to make a reservation with the provided flight ID and phone number
        Reserve reserve = service.makeReservation(flightId, phoneNumber);
        // Check if the reservation was successfully made
        if (reserve != null) {
            // Return a success response with the reservation ID
            return ResponseEntity.ok("Reservation made successfully. Your reservation ID is: " + reserve.getReserveId());
        } else {
            // If the reservation was not successful, return a not found status with an error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight or passenger not found.");
        }
    }

    // Endpoint to view reservation history by reservation ID (accessible to public)
    @GetMapping("/public/history/{reservationId}")
    public ResponseEntity<?> viewReservationHistory(@PathVariable int reservationId) {
        // Attempt to retrieve reservation history by reservation ID
        Reserve reserve = service.viewReservationHistory(reservationId);
        // Check if the reservation history was found
        if (reserve != null) {
            // Return the reservation history
            return ResponseEntity.ok(reserve);
        } else {
            // If the reservation history was not found, return a not found status with an error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no such reservation with ID " + reservationId);
        }
    }
}

