package kz.aitu.airportmanagementsystem.datatransferobject;

import jakarta.validation.constraints.Size;
import kz.aitu.airportmanagementsystem.models.User;
import lombok.Data;

import jakarta.validation.constraints.*;
@Data
public class UserDto {
    @NotEmpty(message = "First name is required")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    private String lastName;

    @NotEmpty(message = "Phone number is required")
    @Size(min = 11, max = 11, message = "Phone number should be 11 characters")
    private String phoneNumber;

    @Min(value = 1, message = "Age must be greater than 0")
    private int age;

    public User toUser() {
        return new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhoneNumber(phoneNumber)
                .setAge(age);
    }

}
