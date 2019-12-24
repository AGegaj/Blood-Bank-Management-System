package org.fiek.bloodmanagementsystem.type;

import lombok.Getter;

public enum ResponseStatus {

    OK(200, "OK"),

    SUCCESS(201, "Created"),

    BAD_REQUEST(400, "Bad Request"),

    CREATED(201, "Created"),

    NOT_FOUND(404, "Not Found"),

    NO_DATA(204, "No Data"),

    CONFLICT(409, "Conflict"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    @Getter
    private final int statusCode;

    @Getter
    private final String msg;

    ResponseStatus(Integer statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
}
