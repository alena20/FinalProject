package com.epam.finalproject.service;

import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.model.entity.Feedback;

import java.util.List;

public interface FeedbackService {

    boolean addFeedback(String name, String email, String subject, String message) throws ServiceException;

    List<Feedback> findAllFeedbacks() throws ServiceException;

    boolean addReplyMessage(String feedbackId, String email, String subject, String replyMessage) throws ServiceException;
}
