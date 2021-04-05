package com.epam.finalproject.model.builder;

import com.epam.finalproject.model.entity.User;

public class AdminBuilder extends UserBuilder<AdminBuilder> {
    private AdminBuilder() {
        user = new User();
    }

    public static AdminBuilder anAdmin() {
        return new AdminBuilder();
    }

    @Override
    public User build() {
        return user;
    }
}
