package kz.aitu.airportmanagementsystem.controllers;

import jakarta.validation.Valid;
import kz.aitu.airportmanagementsystem.datatransferobject.UserDto;
import kz.aitu.airportmanagementsystem.models.User;
import kz.aitu.airportmanagementsystem.services.interfaces.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserServiceInterface service;

    public UserController(UserServiceInterface service) {
        this.service = service;
    }

    @GetMapping("/admin/")
    public List<User> getAll(){
        return service.getAll();
    }

    @GetMapping("/admin/{user_id}")
    public ResponseEntity<?> getById(@PathVariable("user_id") int id) {
        User user = service.getById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Passenger not found with id " + id);
        }
    }

    @PostMapping("/admin/")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDto userDto){
        User createdUser = service.createUser(userDto.toUser());
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/admin/lastname/{user_surname}")
    public ResponseEntity<?> findByLastName(@PathVariable("user_surname") String lastName){
        List<User> userList = service.findByLastNameIgnoreCase(lastName);
        if(userList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user with last name `" + lastName + "` was found");
        }
        return ResponseEntity.ok(userList);
    }
}
