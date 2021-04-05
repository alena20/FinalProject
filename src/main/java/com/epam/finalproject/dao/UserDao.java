package com.epam.finalproject.dao;

import com.epam.finalproject.exception.DaoException;
import com.epam.finalproject.model.entity.Trainer;
import com.epam.finalproject.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao  extends AbstractDao {

    int add(String login, String encryptedPassword, String email) throws DaoException;

    Optional<User> findUserByLoginPassword(String name, String encryptedPassword) throws DaoException;

    Optional<User> findUserById(int userId) throws DaoException;

    boolean loginExists(String login) throws DaoException;

    int findIdByEmail(String email) throws DaoException;

    boolean confirmAccount(int id) throws DaoException;

    boolean updateAccountData(int userId, String email, String locale, String password) throws DaoException;

    boolean updatePersonalData(int userId, String firstName, String lastName, String phone) throws DaoException;

    List<User> findRecentUsers(int days) throws DaoException;

    boolean updateUserImage(int userId, String imageName) throws DaoException;

    boolean updateBalanceAndBoughtTrainings(int userId, double decreaseBalance, int increaseTrainings) throws DaoException;

    List<Trainer> findAllTrainers() throws DaoException;

    boolean addToBalance(int userId, int amount) throws DaoException;

    Optional<String> findEmailById(int userId) throws DaoException;

    boolean blockUser(int userId) throws DaoException;

    boolean unblockUser(int userId) throws DaoException;

    boolean updateDiscount(int clientId, double discount) throws DaoException;

    boolean updateDescription(int trainerId, String description) throws DaoException;

    String findPassword(int userId) throws DaoException;
}
