package org.fiek.bloodmanagementsystem.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiek.bloodmanagementsystem.type.ResponseStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldErrorResultObject {

    private Integer status;

    private ResponseStatus responseStatus;

    private List<FieldError> fieldsError;
}
