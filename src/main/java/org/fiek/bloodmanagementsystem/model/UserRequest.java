package org.fiek.bloodmanagementsystem.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Long requestId;

    private Date requiredDate;

    private String bloodGroup;

    private String status;
}
