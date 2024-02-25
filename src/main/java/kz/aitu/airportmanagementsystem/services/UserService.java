package kz.aitu.airportmanagementsystem.services;

import kz.aitu.airportmanagementsystem.models.User;
import kz.aitu.airportmanagementsystem.repositories.UserRepositoryInterface;
import kz.aitu.airportmanagementsystem.services.interfaces.UserServiceInterface;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements UserServiceInterface {
    private final UserRepositoryInterface repositoryInterface;

    public UserService(UserRepositoryInterface repositoryInterface) {
        this.repositoryInterface = repositoryInterface;
    }
    // CRUD methods that overrides user interface
    @Override
    public List<User> getAll() {
        return repositoryInterface.findAll(Sort.by(Sort.Direction.ASC, "passengerId"));
    }

    @Override
    public User getById(int id) {
        return repositoryInterface.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) {
        return repositoryInterface.save(user);
    }

    @Override
    public List<User> findByLastNameIgnoreCase(String lastName) {
        return repositoryInterface.findByLastNameIgnoreCase(lastName);
    }


}
