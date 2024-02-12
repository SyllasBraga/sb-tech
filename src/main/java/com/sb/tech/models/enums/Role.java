package com.sb.tech.models.enums;

public enum Role {
    TECHNICIAN("Technician");

    private final String roleType;

    Role(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleType(){
        return roleType;
    }
}
