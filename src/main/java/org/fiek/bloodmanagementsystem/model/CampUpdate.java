package org.fiek.bloodmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampUpdate {

    private Long campId;

    private String campTitle;

    private String state;

    private String city;

    private String details;

    private String img;

}
