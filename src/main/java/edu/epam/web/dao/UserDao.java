package edu.epam.web.dao;

import edu.epam.web.dao.impl.BaseDao;
import edu.epam.web.entity.User;
import edu.epam.web.exceptoin.DaoException;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    boolean register(User user);

    User login(String login, String pass);

    boolean updatePasswordByLogin(String login, String password) throws DaoException;

    String findPasswordByLogin(String login) throws DaoException;

    Optional<User> findByLoginAndPassword(String login, String password) throws DaoException;

    boolean delete(long id);

    Optional<User> findById(long id);

    Optional<User> findByLogin(String login) throws DaoException;
}

