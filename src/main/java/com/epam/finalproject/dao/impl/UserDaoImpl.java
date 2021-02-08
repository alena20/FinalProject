package com.epam.finalproject.dao.impl;

import com.epam.finalproject.connection.impl.ConnectionPool;
import com.epam.finalproject.dao.UserDao;
import com.epam.finalproject.exception.DaoException;
import com.epam.finalproject.model.entity.Trainer;
import com.epam.finalproject.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.epam.finalproject.dao.impl.UserStatement.*;

public class UserDaoImpl implements UserDao {
    private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    private static final String BLANK = "";
    private final ConnectionPool pool = ConnectionPool.getInstance();

    private UserDaoImpl() { }

    public static UserDaoImpl getInstance() {
        return INSTANCE;
    }
    @Override
    public int add(String login, String password, String email) throws DaoException {
        Connection connection = pool.getConnection();
        try {
            connection.setAutoCommit(false);
            int id = addAccount(connection, login, password, email);
            addUser(connection, id);
            connection.commit();
            return id;
        } catch (SQLException e) {
            rollback(connection);
            throw new DaoException(e);
        } finally {
            setAutoCommitTrue(connection);
            pool.releaseConnection(connection);
        }
    }

    private int addAccount(Connection connection, String login,
                           String password, String email) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement insertAccount = statementInsertAccount(connection, login, password, email)) {
            insertAccount.executeUpdate();
            resultSet = insertAccount.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            rollback(connection);
            throw new DaoException(e);
        } finally {
            close(resultSet);
        }
    }

    private void addUser(Connection connection, int id) throws DaoException {
        try (PreparedStatement insertUser = statementInsertUser(connection, id)) {
            insertUser.execute();
        } catch (SQLException e) {
            rollback(connection);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> findUserByLoginPassword(String name, String encryptedPassword) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserById(int userId) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean loginExists(String login) throws DaoException {
        return false;
    }

    @Override
    public int findIdByEmail(String email) throws DaoException {
        return 0;
    }

    @Override
    public boolean confirmAccount(int id) throws DaoException {
        return false;
    }

    @Override
    public boolean updateAccountData(int userId, String email, String locale, String password) throws DaoException {
        return false;
    }

    @Override
    public boolean updatePersonalData(int userId, String firstName, String lastName, String phone, String instagram) throws DaoException {
        return false;
    }

    @Override
    public List<User> findRecentUsers(int days) throws DaoException {
        return null;
    }

    @Override
    public boolean updateUserImage(int userId, String imageName) throws DaoException {
        return false;
    }

    @Override
    public boolean updateBalanceAndBoughtTrainings(int userId, double decreaseBalance, int increaseTrainings) throws DaoException {
        return false;
    }

    @Override
    public List<Trainer> findAllTrainers() throws DaoException {
        return null;
    }

    @Override
    public boolean addToBalance(int userId, int amount) throws DaoException {
        return false;
    }

    @Override
    public Optional<String> findEmailById(int userId) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean blockUser(int userId) throws DaoException {
        return false;
    }

    @Override
    public boolean unblockUser(int userId) throws DaoException {
        return false;
    }

    @Override
    public boolean updateDiscount(int clientId, double discount) throws DaoException {
        return false;
    }

    @Override
    public boolean updateDescription(int trainerId, String description) throws DaoException {
        return false;
    }

    @Override
    public String findPassword(int userId) throws DaoException {
        return null;
    }
}
