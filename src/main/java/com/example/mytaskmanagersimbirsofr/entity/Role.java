package com.example.mytaskmanagersimbirsofr.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    EMPLOYEE, MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}
