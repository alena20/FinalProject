package com.epam.finalproject.service;

import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.model.entity.Client;
import com.epam.finalproject.model.entity.Training;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TrainingService {

    int addTraining(String trainerId, int clientId, String trainingDate, String trainingTime) throws ServiceException;

    List<Training> findClientTrainings(int clientId) throws ServiceException;

    List<Training> findTrainerTrainings(int trainerId) throws ServiceException;

    boolean updateDescription(String trainingId, String description) throws ServiceException;

    boolean deleteTraining(String trainingId, String userId) throws ServiceException;

    boolean updateTraining(String trainingId, String trainingDate, String trainingTime, String description) throws ServiceException;

    boolean setTrainingDone(String trainingId) throws ServiceException;

    boolean rateTraining(String trainingId, String rating, String trainerId) throws ServiceException;

    Optional<Training> findTrainingById(int trainingId) throws ServiceException;

    Map<Integer, Client> findTrainerClients(int trainerId) throws ServiceException;
}
