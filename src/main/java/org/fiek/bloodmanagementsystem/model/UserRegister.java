package org.fiek.bloodmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiek.bloodmanagementsystem.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegister {

    @NotBlank(message = "Must not be empty")
    @Pattern(regexp = "^[a-zA-Z]{2,45}$", message = "First Name must contain only letters!")
    private String firstName;

    @NotBlank(message = "Must not be empty")
    @Pattern(regexp = "^[a-zA-Z]{2,45}$", message = "Last Name must contain only letters!")
    private String lastName;

    @NotBlank(message = "Must not be empty")
    @Pattern(regexp = "^[A-Za-z0-9_.]{3,9}$", message = "Username must contain min 3 and max 9 characters")
    private String username;

    @NotBlank(message = "Must not be empty")
    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Must not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]{6,45}$", message = "Min 6 characters and Max 45")
    private String password;

    private String image;

    @NotBlank(message = "Must not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]{3,45}$", message = "Not Valid")
    private String country;

    @NotBlank(message = "Must not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]{3,45}$", message = "Not Valid")
    private String city;

    @NotBlank(message = "Must not be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Personal number must contain 10 numbers")
    private String personalNumber;

    @NotNull(message = "Must not be empty")
    private Long roleId;

    private DonatorDetailsRegister donatorDetailsRegister;

    public User getUser(){
        return new User(firstName, lastName, username, email, image, country, city, personalNumber);
    }

}
