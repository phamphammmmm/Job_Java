package org.datdev.job.shareds.enums;

import lombok.Data;

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
