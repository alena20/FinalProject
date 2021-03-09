package com.epam.finalproject.dao.impl;

import com.epam.finalproject.connection.impl.ConnectionPool;
import com.epam.finalproject.dao.UserDao;
import com.epam.finalproject.exception.DaoException;
import com.epam.finalproject.model.creator.*;
import com.epam.finalproject.model.entity.*;

import java.sql.*;
import java.util.ArrayList;
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

    static User create(ResultSet resultSet) throws SQLException {
        String role = resultSet.getString(TableColumnName.ACCOUNT_ROLE);
        UserRole userRole = UserRole.valueOf(role);
        UserCreator userBuilder = null;
        if (userRole == UserRole.CLIENT) {
            double discount = resultSet.getDouble(TableColumnName.USER_DISCOUNT);
            double moneyBalance = resultSet.getDouble(TableColumnName.USER_MONEY_BALANCE);
            int boughtTrainings = resultSet.getInt(TableColumnName.USER_BOUGHT_TRAININGS);
            userBuilder = ClientCreator.aClient()
                    .withMoneyBalance(moneyBalance)
                    .withBoughtTrainings(boughtTrainings)
                    .withPersonalDiscount(discount);
        } else if (userRole == UserRole.TRAINER) {
            double rating = resultSet.getDouble(TableColumnName.USER_RATING);
            String institution = resultSet.getString(TableColumnName.USER_INSTITUTION);
            int graduationYear = resultSet.getInt(TableColumnName.USER_GRADUATION);
            String shortSummary = resultSet.getString(TableColumnName.USER_SHORT_SUMMARY);
            userBuilder = TrainerCreator.aTrainer()
                    .withGraduationYear(graduationYear)
                    .withInstitution(institution)
                    .withRating(rating)
                    .withShortSummary(shortSummary);
        } else if (userRole == UserRole.ADMIN) {
            userBuilder = AdminCreator.anAdmin();
        }
        int id = resultSet.getInt(TableColumnName.ACCOUNT_ID);
        String login = resultSet.getString(TableColumnName.ACCOUNT_LOGIN);
        String email = resultSet.getString(TableColumnName.ACCOUNT_EMAIL);
        Date date = resultSet.getDate(TableColumnName.ACCOUNT_REGISTRATION_DATE);
        String locale = resultSet.getString(TableColumnName.ACCOUNT_LOCALE);
        AccountLocale accountLocale = AccountLocale.valueOf(locale);
        boolean isActive = resultSet.getBoolean(TableColumnName.ACCOUNT_IS_ACTIVE);
        String firstName = resultSet.getString(TableColumnName.USER_FIRST_NAME);
        String lastName = resultSet.getString(TableColumnName.USER_LAST_NAME);
        String phone = resultSet.getString(TableColumnName.USER_PHONE);
        String imageName = resultSet.getString(TableColumnName.USER_IMAGE);
        Account account = AccountCreator.anAccount()
                .withId(id)
                .withName(login)
                .withEmail(email)
                .withRole(userRole)
                .withRegistrationDate(date)
                .withLocale(accountLocale)
                .withIsActive(isActive)
                .build();
        return userBuilder.withAccount(account)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withPhoneNumber(phone)
                .withImageName(imageName)
                .build();
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
        try (Connection connection = pool.getConnection();
             PreparedStatement statementSelect = statementSelectUser(connection, name, encryptedPassword);
             ResultSet resultSet = statementSelect.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(create(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> findUserById(int userId) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statementSelect = statementSelectUserById(connection, userId);
             ResultSet resultSet = statementSelect.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(create(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean loginExists(String login) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statementSelect = statementSelectByLogin(connection, login);
             ResultSet resultSet = statementSelect.executeQuery()) {
            return resultSet.next();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int findIdByEmail(String email) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statementSelect = statementSelectByEmail(connection, email);
             ResultSet resultSet = statementSelect.executeQuery()) {
            return resultSet.next() ? resultSet.getInt(TableColumnName.ACCOUNT_ID) : 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean confirmAccount(int id) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statementUpdate = statementUpdateActive(connection, id)) {
            return statementUpdate.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateAccountData(int userId, String email, String locale, String password) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statementUpdate = statementUpdateAccount(connection, email, locale, password, userId)) {
            return statementUpdate.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updatePersonalData(int userId, String firstName, String lastName, String phone) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementUpdateUser(connection, firstName, lastName, phone, userId)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> findRecentUsers(int days) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementSelectRecent(connection, days);
             ResultSet resultSet = statement.executeQuery()) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = create(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateUserImage(int userId, String imageName) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementUpdateImage(connection, userId, imageName)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateBalanceAndBoughtTrainings(int userId, double decreaseBalance, int increaseTrainings) throws DaoException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statementDecrease = statementDecreaseBalance(connection, userId, decreaseBalance);
             PreparedStatement statementIncrease = statementIncreaseTrainings(connection, userId, increaseTrainings)) {
            connection.setAutoCommit(false);
            statementDecrease.execute();
            boolean result = statementIncrease.executeUpdate() > 0;
            connection.commit();
            return result;
        } catch (SQLException e) {
            rollback(connection);
            throw new DaoException(e);
        } finally {
            setAutoCommitTrue(connection);
            pool.releaseConnection(connection);
        }
    }

    @Override
    public List<Trainer> findAllTrainers() throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementSelectAllTrainers(connection);
             ResultSet resultSet = statement.executeQuery()) {
            List<Trainer> trainers = new ArrayList<>();
            while (resultSet.next()) {
                Account account = AccountCreator.anAccount()
                        .withId(resultSet.getInt(TableColumnName.ACCOUNT_ID))
                        .withEmail(resultSet.getString(TableColumnName.ACCOUNT_EMAIL))
                        .withRole(UserRole.TRAINER)
                        .build();
                Trainer trainer = TrainerCreator.aTrainer()
                        .withAccount(account)
                        .withFirstName(resultSet.getString(TableColumnName.USER_FIRST_NAME))
                        .withLastName(resultSet.getString(TableColumnName.USER_LAST_NAME))
                        .withPhoneNumber(resultSet.getString(TableColumnName.USER_PHONE))
                        .withImageName(resultSet.getString(TableColumnName.USER_IMAGE))
                        .withInstitution(resultSet.getString(TableColumnName.USER_INSTITUTION))
                        .withGraduationYear(resultSet.getInt(TableColumnName.USER_GRADUATION))
                        .withRating(resultSet.getDouble(TableColumnName.USER_RATING))
                        .withShortSummary(resultSet.getString(TableColumnName.USER_SHORT_SUMMARY))
                        .build();
                trainers.add(trainer);
            }
            return trainers;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean addToBalance(int userId, int amount) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementUpdateBalance(connection, userId, amount)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<String> findEmailById(int userId) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementSelectEmailById(connection, userId);
             ResultSet resultSet = statement.executeQuery()) {
            Optional<String> optional;
            if (resultSet.next()) {
                String email = resultSet.getString(TableColumnName.ACCOUNT_EMAIL);
                optional = Optional.of(email);
            } else {
                optional = Optional.empty();
            }
            return optional;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean blockUser(int userId) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementBlockUser(connection, userId)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean unblockUser(int userId) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementUnblockUser(connection, userId)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateDiscount(int clientId, double discount) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementUpdateDiscount(connection, clientId, discount)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateDescription(int trainerId, String description) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementUpdateDescription(connection, trainerId, description)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public String findPassword(int userId) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementSelectPassword(connection, userId);
             ResultSet resultSet = statement.executeQuery()) {
            String result;
            if (resultSet.next()) {
                result = resultSet.getString(TableColumnName.ACCOUNT_PASSWORD);
            } else {
                result = BLANK;
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
