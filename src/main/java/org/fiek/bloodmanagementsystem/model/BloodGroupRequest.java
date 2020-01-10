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
public class BloodGroupRequest {

    private Long requestId;

    private Date requiredDate;

    private Long userId;

    private String userFirstName;

    private String userLastName;

    private String userEmail;

    private String personalNumber;
}
