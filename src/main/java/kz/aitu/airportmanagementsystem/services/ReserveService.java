package kz.aitu.airportmanagementsystem.services;

import kz.aitu.airportmanagementsystem.models.FlightDetails;
import kz.aitu.airportmanagementsystem.models.Reserve;
import kz.aitu.airportmanagementsystem.models.User;
import kz.aitu.airportmanagementsystem.repositories.FlightDetailsRepositoryInterface;
import kz.aitu.airportmanagementsystem.repositories.ReserveRepositoryInterface;
import kz.aitu.airportmanagementsystem.repositories.UserRepositoryInterface;
import kz.aitu.airportmanagementsystem.services.interfaces.ReserveServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReserveService implements ReserveServiceInterface {
    private final ReserveRepositoryInterface repositoryInterface;
    private final UserRepositoryInterface userRepositoryInterface;
    private final FlightDetailsRepositoryInterface detailsRepositoryInterface;

    // Constructor injection of repositories required by the service
    public ReserveService(ReserveRepositoryInterface repositoryInterface, UserRepositoryInterface userRepositoryInterface, FlightDetailsRepositoryInterface detailsRepositoryInterface) {
        this.repositoryInterface = repositoryInterface;
        this.userRepositoryInterface = userRepositoryInterface;
        this.detailsRepositoryInterface = detailsRepositoryInterface;
    }

    // Method to retrieve all reservations sorted by reservation date
    @Override
    public List<Reserve> getAllReserve() {
        return repositoryInterface.findAll(Sort.by(Sort.Direction.ASC, "reserveDate"));
    }

    // Method to make a reservation based on flight ID and user phone number
    @Override
    public Reserve makeReservation(Long flightId, String phoneNumber) {
        // Find the user by phone number and the flight details by flight ID
        User user = userRepositoryInterface.findByPhoneNumber(phoneNumber);
        FlightDetails flightDetails = detailsRepositoryInterface.findById(flightId).orElse(null);

        // Check if both the user and flight details exist
        if(flightDetails != null && user != null){
            // Create a new reservation
            Reserve reserve = new Reserve();
            reserve.setUser(user);
            reserve.setFlightDetails(flightDetails);
            repositoryInterface.save(reserve);

            // Decrease the available seats of the flight by 1
            flightDetails.setAvailableSeats(flightDetails.getAvailableSeats() - 1);
            detailsRepositoryInterface.save(flightDetails);

            return reserve; // Return the created reservation
        }
        else{
            return null; // Return null if either the flight or user does not exist
        }
    }

    // Method to view reservation history by reservation ID
    @Override
    public Reserve viewReservationHistory(int reserveId) {
        return repositoryInterface.findByReserveId(reserveId);
    }
}
