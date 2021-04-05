package com.epam.finalproject.dao.impl;

import com.epam.finalproject.connection.impl.ConnectionPool;
import com.epam.finalproject.dao.TrainerApplicationDao;
import com.epam.finalproject.exception.DaoException;
import com.epam.finalproject.model.builder.TrainerApplicationBuilder;
import com.epam.finalproject.model.entity.TrainerApplication;
import com.epam.finalproject.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.finalproject.dao.impl.TrainerApplicationStatement.*;
import static com.epam.finalproject.dao.impl.UserStatement.statementUpdateTrainer;
import static com.epam.finalproject.dao.impl.UserStatement.statementUpdateTrainerRole;

public class TrainerApplicationDaoImpl implements TrainerApplicationDao {
    private static final TrainerApplicationDaoImpl INSTANCE = new TrainerApplicationDaoImpl();
    private final ConnectionPool pool = ConnectionPool.getInstance();

    private TrainerApplicationDaoImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static TrainerApplicationDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(int userId, String institution, int graduationYear) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementInsertApplication(connection, userId,
                     institution, graduationYear)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean exists(int userId) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementFindById(connection, userId);
             ResultSet resultSet = statement.executeQuery()) {
            return resultSet.next();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(int userId) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementDeleteApplication(connection, userId)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<TrainerApplication> findAll() throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementSelectAllApplications(connection);
             ResultSet resultSet = statement.executeQuery()) {
            List<TrainerApplication> applications = new ArrayList<>();
            while (resultSet.next()) {
                TrainerApplication application = createTrainerApplication(resultSet);
                applications.add(application);
            }
            return applications;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean approve(int userId, String institution, int graduationYear) throws DaoException {
        Connection connection = pool.getConnection();
        try {
            connection.setAutoCommit(false);
            setTrainerRole(connection, userId);
            updateUserData(connection, userId, institution, graduationYear);
            boolean result = deleteApplication(connection, userId);
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

    private void setTrainerRole(Connection connection, int userId) throws DaoException {
        try (PreparedStatement statement = statementUpdateTrainerRole(connection, userId)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            rollback(connection);
            throw new DaoException(e);
        }
    }

    private void updateUserData(Connection connection, int userId, String institution, int graduationYear) throws DaoException {
        try (PreparedStatement statement = statementUpdateTrainer(connection, institution, graduationYear, userId)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            rollback(connection);
            throw new DaoException(e);
        }
    }

    private boolean deleteApplication(Connection connection, int userId) throws DaoException {
        try (PreparedStatement statement = statementDeleteApplication(connection, userId)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            rollback(connection);
            throw new DaoException(e);
        }
    }

    private TrainerApplication createTrainerApplication(ResultSet resultSet) throws DaoException {
        try {
            String institution = resultSet.getString(TableColumnName.TRAINER_APPLICATION_INSTITUTION);
            int graduationYear = resultSet.getInt(TableColumnName.TRAINER_APPLICATION_GRADUATION_YEAR);
            Date applicationDate = resultSet.getDate(TableColumnName.TRAINER_APPLICATION_APPLICATION_DATE);
            User user = UserDaoImpl.create(resultSet);
            return TrainerApplicationBuilder.aTrainerApplication()
                    .withUser(user)
                    .withInstitution(institution)
                    .withGraduationYear(graduationYear)
                    .withApplicationDate(applicationDate)
                    .build();
        } catch (SQLException e) {
            throw new DaoException("Trainer application creation error!", e);
        }
    }

}

