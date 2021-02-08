package edu.epam.web.dao;

public class SqlQuery {
    public static final String ADD_USER = "INSERT INTO users (login, password, first_name, last_name, telephone_number, email" +
            ") VALUES (?, ?, ?, ?, ?, ?)";
    public static final String FIND_PASSWORD_BY_LOGIN = "SELECT password FROM users WHERE login = ?";
    public static final String FIND_USER_BY_LOGIN = "SELECT login, password, first_name, last_name, " +
            "email FROM users WHERE login = ?";
    private SqlQuery() {
    }
}
