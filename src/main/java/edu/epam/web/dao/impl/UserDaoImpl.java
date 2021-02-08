package edu.epam.web.dao.impl;

import edu.epam.web.dao.SqlQuery;
import edu.epam.web.dao.UserDao;
import edu.epam.web.entity.User;
import edu.epam.web.entity.UserRole;
import edu.epam.web.exceptoin.DaoException;
import edu.epam.web.repository.UserRepository;
import edu.epam.web.repository.connection.ConnectionPool;
import edu.epam.web.repository.impl.UserRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl  implements UserDao {
    private final List<User> userList = new ArrayList<>();

    {
        userList.add(new User("user1", "user1", UserRole.ADMIN));
    }

    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance(){
        return instance;
    }

    @Override
    public boolean register(User user) {
        boolean result = false;
        if(!userList.contains(user)) {
            result = userList.add(user);
        }
        return result;
    }

    @Override
    public User login(String login, String pass) {
        return null;
    }

    @Override
    public boolean updatePasswordByLogin(String login, String password) throws DaoException {
        return false;
    }

    @Override
    public String findPasswordByLogin(String login) throws DaoException {
        return null;
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean delete(long id) {
        boolean result = false;
        Optional<User> user = findById(id);
        if(user.isPresent()){
            result = userList.remove(user.get());
        }
        return result;
    }

    @Override
    public Optional<User> findById(long id) {
        Optional<User> result = userList.stream().filter(x -> x.getId() == id).findFirst();
        return result;
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
      //  Optional<User> result = userList.stream().filter(x -> x.getLogin().equals(login)).findFirst();
      //  return result;
        Optional<User> foundUser = Optional.empty();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                foundUser = Optional.of(createUserFromResultSet(resultSet));
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return foundUser;
    }

    @Override
    public User createUserFromResultSet(ResultSet resultSet) throws DaoException {
        return null;
    }

    @Override
    public boolean add(User user) throws DaoException {
        return false;
    }
}

