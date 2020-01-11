package org.fiek.bloodmanagementsystem.type;

public enum RequestStatus {
    UNCONFIRMED,

    CONFIRMED;

    public static RequestStatus getStatus(String status) {
        if (status.equalsIgnoreCase("UNCONFIRMED")) {
            return RequestStatus.UNCONFIRMED;
        } else if (status.equalsIgnoreCase("CONFIRMED")) {
            return RequestStatus.CONFIRMED;
        }
        return null;
    }
}
