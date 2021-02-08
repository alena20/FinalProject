package com.epam.finalproject.service.impl;

import com.epam.finalproject.dao.UserDao;
import com.epam.finalproject.dao.impl.UserDaoImpl;
import com.epam.finalproject.exception.DaoException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.model.entity.AccountLocale;
import com.epam.finalproject.model.entity.Client;
import com.epam.finalproject.model.entity.Trainer;
import com.epam.finalproject.model.entity.User;
import com.epam.finalproject.service.UserService;
import com.epam.finalproject.util.CryptoUtility;
import com.epam.finalproject.util.RequestParameterName;
import com.epam.finalproject.validator.AbstractValidator;
import com.epam.finalproject.validator.UserValidator;
import com.epam.finalproject.validator.ValidationError;
import com.epam.finalproject.validator.ValidationErrorSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final String BLANK = "";
    private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private final UserDao dao = UserDaoImpl.getInstance();

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<User> findUser(Map<String, String> parameters)
            throws ServiceException {
        if (!UserValidator.correctLoginParameters(parameters)) {
            return Optional.empty();
        }
        String login = parameters.get(RequestParameterName.LOGIN);
        String password = parameters.get(RequestParameterName.LOGIN_PASSWORD);
        String encryptedPassword = CryptoUtility.encryptMessage(password);
        Optional<User> optional;
        try {
            optional = dao.findUserByLoginPassword(login, encryptedPassword);
            if (optional.isEmpty()) {
                ValidationErrorSet errorSet = ValidationErrorSet.getInstance();
                errorSet.add(ValidationError.WRONG_LOGIN_PASSWORD);
            }
            LOGGER.info("Login result: {}", optional.isPresent());
        } catch (DaoException e) {
            throw new ServiceException("Error, accessing database!", e);
        }
        return optional;
    }

    @Override
    public Optional<User> registerUser(Map<String, String> parameters)
            throws ServiceException {
        if (!UserValidator.correctRegisterParameters(parameters)) {
            return Optional.empty();
        }
        String login = parameters.get(RequestParameterName.REGISTRATION_LOGIN);
        String password = parameters.get(RequestParameterName.REGISTRATION_PASSWORD);
        String email = parameters.get(RequestParameterName.REGISTRATION_EMAIL);
        String encryptedPassword = CryptoUtility.encryptMessage(password);
        try {
            ValidationErrorSet errorSet = ValidationErrorSet.getInstance();
            boolean exists = false;
            if (dao.loginExists(login)) {
                errorSet.add(ValidationError.LOGIN_EXISTS);
                exists = true;
            }
            if (dao.findIdByEmail(email) != 0) {
                errorSet.add(ValidationError.EMAIL_EXISTS);
                exists = true;
            }
            if (exists) {
                parameters.put(RequestParameterName.REGISTRATION_PASSWORD, BLANK);
                parameters.put(RequestParameterName.REPEAT_PASSWORD, BLANK);
                return Optional.empty();
            }
            int id = dao.add(login, encryptedPassword, email);
            return dao.findUserById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean confirmAccount(String accountId) throws ServiceException {
        if (!AbstractValidator.correctId(accountId)) {
            return false;
        }
        int id = Integer.parseInt(accountId);
        try {
            boolean result = dao.confirmAccount(id);
            if (result) {
                LOGGER.info("Account id={} was confirmed!", id);
            } else {
                LOGGER.error("Account id={} was not found", id);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<String> findEmailById(int userId) throws ServiceException {
        if (!AbstractValidator.correctId(userId)) {
            return Optional.empty();
        }
        try {
            return dao.findEmailById(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateAccountData(User user, String email, String locale, String newPassword, String repeatPassword) throws ServiceException {
        return false;
    }

    @Override
    public boolean updatePersonalData(int userId, String firstName, String lastName, String phone, String instagram) throws ServiceException {
        return false;
    }

    @Override
    public List<User> findRecentUsers(String daysNumber) throws ServiceException {
        return null;
    }

    @Override
    public boolean updateUserImage(int userId, String imageName) throws ServiceException {
        return false;
    }

    @Override
    public boolean buyTrainings(Client client, String trainingsNumber) throws ServiceException {
        return false;
    }

    @Override
    public List<Trainer> findAllTrainers() throws ServiceException {
        return null;
    }

    @Override
    public boolean addToBalance(Client client, String amount) throws ServiceException {
        return false;
    }

    @Override
    public boolean blockUser(String userId) throws ServiceException {
        return false;
    }

    @Override
    public boolean unblockUser(String userId) throws ServiceException {
        return false;
    }

    @Override
    public boolean updateDiscount(String clientId, String discount) throws ServiceException {
        return false;
    }

    @Override
    public boolean updateDescription(int trainerId, String description) throws ServiceException {
        return false;
    }

}
