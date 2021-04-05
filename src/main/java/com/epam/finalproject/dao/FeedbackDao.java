package com.epam.finalproject.dao;

import com.epam.finalproject.exception.DaoException;
import com.epam.finalproject.model.entity.Feedback;

import java.util.List;

public interface FeedbackDao {

    boolean add(String name, String email, String subject, String message) throws DaoException;

    List<Feedback> findAll() throws DaoException;

    boolean addReplyMessage(int feedbackId, String message) throws DaoException;
}
