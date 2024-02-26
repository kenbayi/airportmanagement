package kz.aitu.airportmanagementsystem.repositories;

import kz.aitu.airportmanagementsystem.models.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveRepositoryInterface extends JpaRepository<Reserve, Integer> {
    Reserve findByReserveId(int reserveId);
}