package com.epam.finalproject.service.impl;

import com.epam.finalproject.dao.TrainerApplicationDao;
import com.epam.finalproject.dao.impl.TrainerApplicationDaoImpl;
import com.epam.finalproject.exception.DaoException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.model.entity.TrainerApplication;
import com.epam.finalproject.service.TrainerApplicationService;
import com.epam.finalproject.validator.CommonValidator;
import com.epam.finalproject.validator.TrainerApplicationValidator;
import com.epam.finalproject.validator.ValidationError;
import com.epam.finalproject.validator.ValidationErrorSet;

import java.util.List;

public class TrainerApplicationServiceImpl implements TrainerApplicationService {
    private static final TrainerApplicationServiceImpl INSTANCE = new TrainerApplicationServiceImpl();
    private final TrainerApplicationDao dao = TrainerApplicationDaoImpl.getInstance();

    private TrainerApplicationServiceImpl() {
    }

    public static TrainerApplicationServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean addApplication(int id, String institution, String graduationYear) throws ServiceException {
        if (!TrainerApplicationValidator.correctSendParameters(id, institution, graduationYear)) {
            return false;
        }
        int year = Integer.parseInt(graduationYear);
        try {
            boolean result;
            if (dao.exists(id)) {
                ValidationErrorSet errorSet = ValidationErrorSet.getInstance();
                errorSet.add(ValidationError.APPLICATION_EXISTS);
                result = false;
            } else {
                result = dao.add(id, institution, year);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteApplication(String userId) throws ServiceException {
        if (!CommonValidator.correctId(userId)) {
            return false;
        }
        int id = Integer.parseInt(userId);
        try {
            return dao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean approveApplication(String userId, String institution, String graduationYear)
            throws ServiceException {
        if (!TrainerApplicationValidator.correctApplicationParameters(userId, institution,
                graduationYear)) {
            return false;
        }
        int id = Integer.parseInt(userId);
        int year = Integer.parseInt(graduationYear);
        try {
            return dao.approve(id, institution, year);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<TrainerApplication> getAllApplications() throws ServiceException {
        try {
            return dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
