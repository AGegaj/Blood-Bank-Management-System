package org.fiek.bloodmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonationUpdate {

    private Long donationId;

    private Double quantity;

    private String details;

    private Long groupId;

    private Long userId;

    private Long campId;

}
