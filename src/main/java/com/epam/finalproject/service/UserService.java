package com.epam.finalproject.service;

import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.model.entity.Client;
import com.epam.finalproject.model.entity.Trainer;
import com.epam.finalproject.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> findUser(Map<String, String> parameters) throws ServiceException;

    Optional<User> registerUser(Map<String, String> parameters) throws ServiceException;

    boolean confirmAccount(String accountId) throws ServiceException;

    boolean updateAccountData(User user, String email, String locale, String newPassword, String repeatPassword)
            throws ServiceException;

    boolean updatePersonalData(int userId, String firstName, String lastName, String phone, String instagram)
            throws ServiceException;

    List<User> findRecentUsers(String daysNumber) throws ServiceException;

    boolean updateUserImage(int userId, String imageName) throws ServiceException;

    boolean buyTrainings(Client client, String trainingsNumber) throws ServiceException;

    List<Trainer> findAllTrainers() throws ServiceException;

    boolean addToBalance(Client client, String amount) throws ServiceException;

    Optional<String> findEmailById(int userId) throws ServiceException;

    boolean blockUser(String userId) throws ServiceException;

    boolean unblockUser(String userId) throws ServiceException;

    boolean updateDiscount(String clientId, String discount) throws ServiceException;

    boolean updateDescription(int trainerId, String description) throws ServiceException;
}
