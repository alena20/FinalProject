package com.epam.finalproject.model.creator;

import com.epam.finalproject.model.entity.User;

public class AdminCreator extends UserCreator<AdminCreator> {
    private AdminCreator() {
        user = new User();
    }

    public static AdminCreator anAdmin() {
        return new AdminCreator();
    }

    @Override
    public User build() {
        return user;
    }
}
