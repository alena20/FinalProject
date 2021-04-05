package com.epam.finalproject.service;

import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.model.entity.TrainerApplication;

import java.util.List;

public interface TrainerApplicationService {

    boolean addApplication(int id, String institution, String graduationYear) throws ServiceException;


    boolean deleteApplication(String id) throws ServiceException;

    boolean approveApplication(String userId, String institution, String graduationYear) throws ServiceException;

    List<TrainerApplication> getAllApplications() throws ServiceException;
}
