package kz.aitu.airportmanagementsystem.services.interfaces;

import kz.aitu.airportmanagementsystem.models.Reserve;

import java.util.List;

public interface ReserveServiceInterface {
    List<Reserve> getAllReserve();
    Reserve makeReservation(Long flightId, String phoneNumber);
    Reserve viewReservationHistory(int reserveId);
}
