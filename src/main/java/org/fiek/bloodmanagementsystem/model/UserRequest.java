package org.fiek.bloodmanagementsystem.model;

import lombok.*;
import org.fiek.bloodmanagementsystem.type.RequestStatus;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Date requiredDate;

    private String bloodGroup;

    private String status;

}
