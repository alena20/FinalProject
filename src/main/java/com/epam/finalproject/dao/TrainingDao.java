package com.epam.finalproject.dao;

import com.epam.finalproject.exception.DaoException;
import com.epam.finalproject.model.entity.Client;
import com.epam.finalproject.model.entity.Training;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface TrainingDao extends AbstractDao {

    int add(int trainerId, int clientId, Date trainingDate, Time trainingTime) throws DaoException;

    List<Training> findClientTrainings(int clientId) throws DaoException;

    List<Training> findTrainerTrainings(int trainerId) throws DaoException;

    boolean updateDescription(int trainingId, String description) throws DaoException;

    boolean deleteTraining(int trainingId, int userId) throws DaoException;

    boolean updateTraining(int trainingId, Date trainingDate, Time trainingTime, String description) throws DaoException;

    boolean setTrainingDone(int trainingId) throws DaoException;

    boolean updateTrainingRating(int trainingId, int rating, int trainerId) throws DaoException;

    double countAverageTrainerRating(int trainerId) throws DaoException;

    Optional<Training> findTrainingById(int trainingId) throws DaoException;

    List<Client> findTrainerClients(int trainerId) throws DaoException;
}
