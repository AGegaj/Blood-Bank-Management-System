package org.fiek.bloodmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiek.bloodmanagementsystem.type.RequestStatus;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestData {

    private Long requestId;

    private Date requiredDate;

    private String bloodGroup;

    private Long userId;

    private String userFirstName;

    private String userLastName;

    private String userEmail;

    private String personalNumber;

    private RequestStatus status;

}
