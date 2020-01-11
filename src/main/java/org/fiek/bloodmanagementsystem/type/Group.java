package org.fiek.bloodmanagementsystem.type;

public enum Group {
    OMINUS(1),
    OPLUS(2),
    AMINUS(3),
    APLUS(4),
    BMINUS(5),
    BPLUS(6),
    ABMINUS(7),
    ABPLUS(8),;

    private final int type;

    Group(final int type) {
        this.type = type;
    }

    public int fromCanonicalForm() { return type; }
}
