package org.fiek.bloodmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiek.bloodmanagementsystem.entity.DonatorDetails;
import org.fiek.bloodmanagementsystem.type.Gender;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonatorDetailsRegister {

    @NotNull(message = "Must not be empty")
    private Double weigh;

    @NotNull(message = "Must not be empty")
    private Gender gender;

    @NotNull(message = "Must not be empty")
    private Integer age;

    @NotNull(message = "Must not be empty")
    private Long groupId;

    public DonatorDetails getDonatorDetails(){
        return new DonatorDetails(weigh, gender, age);
    }

}
