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
public class UserDonations {

    private Long donationId;

    private Date donationDate;

    private Double quantity;

    private String details;

    private Long groupId;

    private Long campId;

    private String campTitle;

    private String state;

    private String city;

    private String firstName;

    private String lastName;

    private String group;

    private String personalNumber;
}
