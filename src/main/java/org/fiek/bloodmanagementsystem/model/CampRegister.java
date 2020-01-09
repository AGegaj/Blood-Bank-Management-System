package org.fiek.bloodmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiek.bloodmanagementsystem.entity.Camp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampRegister {

    @NotBlank(message = "Must not be empty")
    private String campTitle;

    @NotBlank(message = "Must not be empty")
    @Pattern(regexp = "^[a-zA-Z]{2,45}$", message = "State must contain only letters!")
    private String state;

    @NotBlank(message = "Must not be empty")
    @Pattern(regexp = "^[a-zA-Z]{2,45}$", message = "City must contain only letters!")
    private String city;

    private String details;

    private String img;

    public Camp getCamp(){
        return new Camp(campTitle, state, city, details, img);
    }
}
