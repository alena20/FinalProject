package com.epam.finalproject.dao;

import com.epam.finalproject.exception.DaoException;
import com.epam.finalproject.model.entity.TrainerApplication;

import java.util.List;

public interface TrainerApplicationDao extends AbstractDao{
    boolean add(int userId, String institution, int graduationYear) throws DaoException;

    boolean exists(int userId) throws DaoException;

    boolean delete(int userId) throws DaoException;

    List<TrainerApplication> findAll() throws DaoException;

    boolean approve(int userId, String institution, int graduationYear) throws DaoException;
}
