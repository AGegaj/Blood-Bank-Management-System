package org.fiek.bloodmanagementsystem.type;

import java.util.ArrayList;
import java.util.List;

public enum Gender {
    MALE,

    FEMALE;

    public static Gender getGender(String status) {
        if (status.equalsIgnoreCase("MALE")) {
            return Gender.MALE;
        } else if (status.equalsIgnoreCase("FEMALE")) {
            return Gender.FEMALE;
        }
        return null;
    }

    public static List<String> getGenders() {

        List<String> res = new ArrayList<>();

        res.add(MALE.name());
        res.add(FEMALE.name());

        return res;
    }
}
