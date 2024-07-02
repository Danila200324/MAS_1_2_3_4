package com.danven.web_library.domain.user;

import java.io.Serializable;

public enum Role{

    USER("USER"), ADMINISTRATOR("ADMINISTRATOR");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
