package org.fiek.bloodmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiek.bloodmanagementsystem.entity.Camp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampRegister {

    private String campTitle;

    private String state;

    private String city;

    private String details;

    private String img;

    public Camp getCamp(){
        return new Camp(campTitle, state, city, details, img);
    }
}
