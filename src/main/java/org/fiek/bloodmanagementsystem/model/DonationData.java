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
public class DonationData {

    private Long donationId;

    private Date donationDate;

    private Double quantity;

    private String details;

    private Long groupId;

    private Long userId;

    private Long campId;
}
