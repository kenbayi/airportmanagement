package kz.aitu.airportmanagementsystem.services.interfaces;

import kz.aitu.airportmanagementsystem.models.User;

import java.util.List;
    //main methods of user service
public interface UserServiceInterface {
    List<User> getAll();
    User getById(int id);
    User createUser(User user);
    List<User> findByLastNameIgnoreCase(String lastName);
}
