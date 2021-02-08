package edu.epam.web.service;

import edu.epam.web.entity.User;
import edu.epam.web.exceptoin.DaoException;

import java.util.Optional;

public interface UserService {

    boolean create(User user) throws DaoException;

    boolean delete(long id);

    Optional<User> login(String login, String pass);

    Optional<User> findById(long id);
}
