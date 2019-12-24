package org.fiek.bloodmanagementsystem.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiek.bloodmanagementsystem.type.ResponseStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataResult<T> {

    private Integer status;

    private ResponseStatus responseStatus;

    private T data;
}
