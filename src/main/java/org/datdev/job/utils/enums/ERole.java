package org.datdev.job.utils.enums;

public enum ERole {
    ADMIN("ADMIN"),
    ENDUSER("ENDUSER"),
    MANAGER("MANAGER");

    private final String name;

    ERole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
