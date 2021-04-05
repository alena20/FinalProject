package com.epam.finalproject.dao.impl;

import com.epam.finalproject.connection.impl.ConnectionPool;
import com.epam.finalproject.dao.TrainingDao;
import com.epam.finalproject.exception.DaoException;
import com.epam.finalproject.model.builder.AccountBuilder;
import com.epam.finalproject.model.builder.ClientBuilder;
import com.epam.finalproject.model.builder.TrainingBuilder;
import com.epam.finalproject.model.entity.Account;
import com.epam.finalproject.model.entity.Client;
import com.epam.finalproject.model.entity.Training;
import com.epam.finalproject.model.entity.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static com.epam.finalproject.dao.impl.TrainingStatement.*;

public class TrainingDaoImpl implements TrainingDao {
    private static final TrainingDaoImpl INSTANCE = new TrainingDaoImpl();
    private final ConnectionPool pool = ConnectionPool.getInstance();

    private TrainingDaoImpl() {
    }

    public static TrainingDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public int add(int trainerId, int clientId, Date trainingDate, Time trainingTime) throws DaoException {
        Connection connection = pool.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement statementAdd = statementInsertTraining(connection, trainerId, clientId, trainingDate, trainingTime);
             PreparedStatement statementDecrement = statementDecrementTrainings(connection, clientId)) {
            connection.setAutoCommit(false);
            statementAdd.execute();
            statementDecrement.executeUpdate();
            resultSet = statementAdd.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            rollback(connection);
            throw new DaoException(e);
        } finally {
            setAutoCommitTrue(connection);
            close(resultSet);
            pool.releaseConnection(connection);
        }
    }

    @Override
    public List<Training> findClientTrainings(int clientId) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementSelectClientTrainings(connection, clientId);
             ResultSet resultSet = statement.executeQuery()) {
            List<Training> trainings = new ArrayList<>();
            while (resultSet.next()) {
                Training training = createTraining(resultSet);
                trainings.add(training);
            }
            return trainings;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Training> findTrainerTrainings(int trainerId) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementSelectTrainerTrainings(connection, trainerId);
             ResultSet resultSet = statement.executeQuery()) {
            List<Training> trainings = new ArrayList<>();
            while (resultSet.next()) {
                Training training = createTraining(resultSet);
                trainings.add(training);
            }
            return trainings;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateDescription(int trainingId, String description) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementUpdateDescription(connection, trainingId, description)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean deleteTraining(int trainingId, int userId) throws DaoException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statementAdd = statementDeleteTraining(connection, trainingId);
             PreparedStatement statementDecrement = statementIncrementTrainings(connection, userId)) {
            connection.setAutoCommit(false);
            statementAdd.execute();
            boolean result = statementDecrement.executeUpdate() > 0;
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
    public boolean updateTraining(int trainingId, Date trainingDate, Time trainingTime, String description)
            throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement =
                     statementUpdateTraining(connection, trainingDate, trainingTime, description, trainingId)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean setTrainingDone(int trainingId) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementSetTrainingDone(connection, trainingId)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateTrainingRating(int trainingId, int rating, int trainerId) throws DaoException {
        Connection connection = pool.getConnection();
        try {
            connection.setAutoCommit(false);
            setTrainingRating(connection, trainingId, rating);
            double trainerRating = countAverageTrainerRating(trainerId);
            boolean result = updateTrainerRating(connection, trainerId, trainerRating);
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

    private void setTrainingRating(Connection connection, int trainingId, int rating) throws DaoException {
        try (PreparedStatement updTraining = statementUpdateTrainingRating(connection, trainingId, rating)) {
            updTraining.executeUpdate();
        } catch (SQLException e) {
            rollback(connection);
            throw new DaoException(e);
        }
    }

    private boolean updateTrainerRating(Connection connection, int trainerId, double rating) throws DaoException {
        try (PreparedStatement updTraining = statementUpdateTrainerRating(connection, trainerId, rating)) {
            return updTraining.executeUpdate() > 0;
        } catch (SQLException e) {
            rollback(connection);
            throw new DaoException(e);
        }
    }

    @Override
    public double countAverageTrainerRating(int trainerId) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementAverageRating(connection, trainerId);
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            return resultSet.getDouble(TableColumnName.AVERAGE_TRAINER_RATING);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Training> findTrainingById(int trainingId) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementSelectTrainingById(connection, trainingId);
             ResultSet resultSet = statement.executeQuery()) {
            Optional<Training> optional;
            if (resultSet.next()) {
                optional = Optional.of(createTraining(resultSet));
            } else {
                optional = Optional.empty();
            }
            return optional;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Client> findTrainerClients(int trainerId) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementSelectTrainerClients(connection, trainerId);
             ResultSet resultSet = statement.executeQuery()) {
             List<Client> clients = new ArrayList<>();
             while (resultSet.next()) {
                Account account = AccountBuilder.anAccount()
                        .withId(resultSet.getInt(TableColumnName.ACCOUNT_ID))
                        .withEmail(resultSet.getString(TableColumnName.ACCOUNT_EMAIL))
                        .withRole(UserRole.CLIENT)
                        .build();
                Client client = ClientBuilder.aClient()
                        .withAccount(account)
                        .withFirstName(resultSet.getString(TableColumnName.USER_FIRST_NAME))
                        .withLastName(resultSet.getString(TableColumnName.USER_LAST_NAME))
                        .withPhoneNumber(resultSet.getString(TableColumnName.USER_PHONE))
                        .withImageName(resultSet.getString(TableColumnName.USER_IMAGE))
                        .build();
                clients.add(client);
            }
            return clients;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    private Training createTraining(ResultSet resultSet) throws DaoException {
        try {
            return TrainingBuilder.aTraining()
                    .withTrainingId(resultSet.getInt(TableColumnName.TRAINING_ID))
                    .withTrainerId(resultSet.getInt(TableColumnName.TRAINER_ID))
                    .withTrainerFirstName(resultSet.getString(TableColumnName.TRAINER_FIRST_NAME))
                    .withTrainerLastName(resultSet.getString(TableColumnName.TRAINER_LAST_NAME))
                    .withClientId(resultSet.getInt(TableColumnName.CLIENT_ID))
                    .withClientFirstName(resultSet.getString(TableColumnName.CLIENT_FIRST_NAME))
                    .withClientLastName(resultSet.getString(TableColumnName.CLIENT_LAST_NAME))
                    .withDate(resultSet.getDate(TableColumnName.TRAINING_DATE))
                    .withTime(resultSet.getTime(TableColumnName.TRAINING_TIME))
                    .withDescription(resultSet.getString(TableColumnName.TRAINING_DESCRIPTION))
                    .withIsDone(resultSet.getBoolean(TableColumnName.TRAINING_IS_DONE))
                    .withRating(resultSet.getInt(TableColumnName.TRAINING_RATING))
                    .build();
        } catch (SQLException e) {
            throw new DaoException("Training creation error!", e);
        }
    }
}

