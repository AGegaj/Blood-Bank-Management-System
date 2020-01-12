package org.fiek.bloodmanagementsystem.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtUser {

    private Long id;

    private String username;

    private String role;
}