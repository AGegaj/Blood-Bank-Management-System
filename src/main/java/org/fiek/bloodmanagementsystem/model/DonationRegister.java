package org.fiek.bloodmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiek.bloodmanagementsystem.entity.Donation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonationRegister {

    @NotBlank(message = "Must not be empty")
    @Pattern(regexp = "^[0-9]$", message = "Only numbers!")
    private Double quantity;

    private String details;

    private Long userId;

    private Long campId;

    public Donation getDonation(){
        return new Donation(quantity, details);
    }

}
