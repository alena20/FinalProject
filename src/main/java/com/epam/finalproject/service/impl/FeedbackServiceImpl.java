package com.epam.finalproject.service.impl;

import com.epam.finalproject.dao.FeedbackDao;
import com.epam.finalproject.dao.impl.FeedbackDaoImpl;
import com.epam.finalproject.exception.DaoException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.model.entity.Feedback;
import com.epam.finalproject.service.FeedbackService;
import com.epam.finalproject.validator.FeedbackValidator;

import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {
    private static final FeedbackServiceImpl INSTANCE = new FeedbackServiceImpl();
    private final FeedbackDao dao = FeedbackDaoImpl.getInstance();

    private FeedbackServiceImpl() {
    }

    public static FeedbackServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean addFeedback(String name, String email, String subject, String message) throws ServiceException {
        if (!FeedbackValidator.correctAddParameters(name, email, subject, message)) {
            return false;
        }
        try {
            return dao.add(name, email, subject, message);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Feedback> findAllFeedbacks() throws ServiceException {
        try {
            return dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addReplyMessage(String feedbackId, String email, String subject, String replyMessage) throws ServiceException {
        if (!FeedbackValidator.correctReplyParameters(feedbackId, email, subject, replyMessage)) {
            return false;
        }
        int id = Integer.parseInt(feedbackId);
        try {
            return dao.addReplyMessage(id, replyMessage);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}