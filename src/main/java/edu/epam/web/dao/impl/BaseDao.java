package edu.epam.web.dao.impl;

import edu.epam.web.dao.UserCreator;
import edu.epam.web.entity.User;
import edu.epam.web.exceptoin.DaoException;
import edu.epam.web.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.epam.web.entity.Entity;
import java.sql.ResultSet;
import java.sql.SQLException;

import static edu.epam.web.util.RequestParameterName.*;

public interface BaseDao <T extends Entity> {
    Logger logger = LogManager.getLogger(BaseDao.class);

    default User createUserFromResultSet(ResultSet resultSet) throws DaoException {
        UserCreator creator = UserCreator.getInstance();

        try {
            String userLogin = resultSet.getString(USER_LOGIN);
            String userPassword = resultSet.getString(USER_PASSWORD);
            String userName = resultSet.getString(USER_NAME);
            String userSurname = resultSet.getString(USER_SURNAME);
            String userEmail = resultSet.getString(USER_EMAIL);

            User createdUser = creator.createUser(userLogin, userPassword, userEmail, userName, userSurname);
            return createdUser;
        } catch (SQLException exp) {
            throw new DaoException("Error while creating user from resultSet", exp);
        }
    }

    boolean add(T t) throws DaoException;
}
