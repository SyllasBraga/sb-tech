package com.sb.tech.models.enums;

public enum AccountStatusEnum {
    ACTIVE("ACTIVE"), INACTIVE("INACTIVE");

    private final String id;
    AccountStatusEnum(String id){
        this.id = id;
    }
}
