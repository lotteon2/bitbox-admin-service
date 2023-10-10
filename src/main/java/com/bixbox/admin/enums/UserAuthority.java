package com.bixbox.admin.enums;

public enum UserAuthority {
    ADMIN(0),
    MANAGER(1),
    TEACHER(2),
    GRADUATE(3),
    TRAINEE(4),
    GENERAL(5);

    private final int value;

    private UserAuthority(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
