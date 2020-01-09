package org.fiek.bloodmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiek.bloodmanagementsystem.entity.BloodGroup;
import org.fiek.bloodmanagementsystem.entity.Camp;
import org.fiek.bloodmanagementsystem.entity.Donation;
import org.fiek.bloodmanagementsystem.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonationRegister {

    @NotBlank(message = "Must not be empty")
    private Double quantity;

    private String details;

    private Long groupId;

    private Long userId;

    private Long campId;

    public Donation getDonation(){
        return new Donation(quantity, details);
    }

}
