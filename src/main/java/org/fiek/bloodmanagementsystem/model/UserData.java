package org.fiek.bloodmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserData {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private Date createdDate;

    private String image;

    private String country;

    private String city;

    private String personalNumber;

    private String role;

}
