package org.fiek.bloodmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiek.bloodmanagementsystem.type.Gender;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonatorData {
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String createdDate;

    private String image;

    private String country;

    private String city;

    private String personalNumber;

    private String role;

    private Double weight;

    private Integer age;

    private String group;

    private String gender;
}
