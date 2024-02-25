package kz.aitu.airportmanagementsystem.repositories;

import kz.aitu.airportmanagementsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepositoryInterface extends JpaRepository<User, Integer> {
    List<User> findByLastNameIgnoreCase(String lastName);
    User findByPhoneNumber(String phoneNumber);
}
