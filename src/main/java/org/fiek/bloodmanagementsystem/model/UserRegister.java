package org.fiek.bloodmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiek.bloodmanagementsystem.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegister {

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;

    private String image;

    private String country;

    private String city;

    private String personalNumber;

    private Long roleId;

    private DonatorDetailsRegister donatorDetailsRegister;

    public User getUser(){
        return new User(firstName, lastName, username, email, image, country, city, personalNumber);
    }

}
