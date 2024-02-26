package kz.aitu.airportmanagementsystem.repositories;

import kz.aitu.airportmanagementsystem.models.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for managing Reserve entities, extending JpaRepository for basic CRUD operations
public interface ReserveRepositoryInterface extends JpaRepository<Reserve, Integer> {
    // Method to find a reservation by its ID
    Reserve findByReserveId(int reserveId);
}
