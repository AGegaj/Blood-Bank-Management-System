package org.fiek.bloodmanagementsystem.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordUpdate {

    @NotNull(message = "Must not be empty")
    private Long id;

    @NotBlank(message = "Must not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]{6,45}$", message = "Min 6 characters and Max 45")
    private String password;
}
