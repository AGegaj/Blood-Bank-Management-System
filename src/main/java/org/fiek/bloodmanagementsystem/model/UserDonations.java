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

    private Date donatedDate;

    private Double quantity;

    private String campTitle;

    private String state;

    private String city;

    private String details;
}
