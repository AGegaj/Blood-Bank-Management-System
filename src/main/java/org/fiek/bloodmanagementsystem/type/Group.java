package org.fiek.bloodmanagementsystem.type;

public enum Group {
    OPLUS(1),
    OMINUS(2),
    APLUS(3),
    AMINUS(4),
    BPLUS(5),
    BMINUS(6),
    ABPLUS(7),
    ABMINUS(8);


    private final int type;

    Group(final int type) {
        this.type = type;
    }

    public int fromCanonicalForm() { return type; }
}
