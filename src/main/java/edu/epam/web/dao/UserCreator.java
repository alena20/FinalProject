package edu.epam.web.dao;

import edu.epam.web.entity.User;
import edu.epam.web.entity.UserRole;

public class UserCreator {
    public static UserCreator instance = new UserCreator();

    private UserCreator() {
    }

    public static UserCreator getInstance() {
        return instance;
    }

    public User createUser(String userLogin, String userPassword, String userEmail, String userName,
                           String userSurname) {
        User createdUser = new User(userLogin, userPassword, userEmail, userName, userSurname, UserRole.CLIENT);
        return createdUser;
    }
}
