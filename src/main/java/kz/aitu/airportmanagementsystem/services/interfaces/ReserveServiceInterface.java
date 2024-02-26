package kz.aitu.airportmanagementsystem.services.interfaces;

import kz.aitu.airportmanagementsystem.models.Reserve;

import java.util.List;

// Interface defining methods for managing flight reservations
public interface ReserveServiceInterface {
    // Method to retrieve all reservations
    List<Reserve> getAllReserve();

    // Method to make a reservation based on flight ID and user phone number
    Reserve makeReservation(Long flightId, String phoneNumber);

    // Method to view reservation history by reservation ID
    Reserve viewReservationHistory(int reserveId);
}

