package org.fiek.bloodmanagementsystem.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private Date timestamp;

    private Integer status;

    private String error;

    private String message;

    private String path;
}
