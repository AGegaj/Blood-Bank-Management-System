package org.fiek.bloodmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiek.bloodmanagementsystem.entity.DonatorDetails;
import org.fiek.bloodmanagementsystem.type.Gender;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonatorDetailsRegister {

    private Double weigh;

    private Gender gender;

    private Integer age;

    public DonatorDetails getDonatorDetails(){
        return new DonatorDetails(weigh, gender, age);
    }

}
