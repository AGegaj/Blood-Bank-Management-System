package org.fiek.bloodmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiek.bloodmanagementsystem.entity.Donation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonationRegister {

    @NotNull(message = "Must not be empty")
    @Positive(message = "Only numbers")
    private Double quantity;

    private String details;

    @NotNull(message = "Select an User")
    private Long userId;

    @NotNull(message = "Select a Camp")
    private Long campId;

    public Donation getDonation(){
        return new Donation(quantity, details);
    }

}
