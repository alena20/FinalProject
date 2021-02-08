package edu.epam.web.validator;

import edu.epam.web.entity.User;

public class CheckUserInfo {
    private static CheckUserInfo instance = new CheckUserInfo();

    private static final String LOGIN_MATCHER = "^[a-zA-Zа-яА][a-zA-Z0-9-_\\.]{1,20}$";
    private static final String PASSWORD_MATCHER = "^[a-zA-Zа-яА][a-zA-Z0-9-_\\.]{1,20}$";

    private CheckUserInfo() {
    }

    public static CheckUserInfo getInstance(){
        return instance;
    }

    public boolean isValid(User user){
        String login = user.getLogin();
        String password = user.getPassword();
        return login.matches(LOGIN_MATCHER) && password.matches(PASSWORD_MATCHER);
    }
}
