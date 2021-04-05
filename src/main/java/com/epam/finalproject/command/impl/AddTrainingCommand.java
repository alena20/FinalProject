package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.model.entity.Client;
import com.epam.finalproject.service.TrainingService;
import com.epam.finalproject.service.impl.TrainingServiceImpl;
import com.epam.finalproject.util.RequestParameterName;
import com.epam.finalproject.util.SessionAttributeName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddTrainingCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(AddTrainingCommand.class);
    private static final int INVALID_ID = -1;
    private final TrainingService trainingService = TrainingServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        Client client = (Client) request.getSession().getAttribute(SessionAttributeName.USER);
        int clientId = client.getAccount().getId();
        String trainerId = request.getParameter(RequestParameterName.SELECTED_TRAINER_ID);
        String date = request.getParameter(RequestParameterName.TRAINING_DATE);
        String time = request.getParameter(RequestParameterName.TRAINING_TIME);
        String page;
        try {
            int trainingId = trainingService.addTraining(trainerId, clientId, date, time);
            if (trainingId != INVALID_ID) {
                client.setBoughtTrainings(client.getBoughtTrainings() - 1);
                page = PagePath.SCHEDULE.getServletPath();
            } else {
                page = PagePath.ERROR_404.getDirectUrl();
            }

        } catch (ServiceException e) {
            LOGGER.error(e);
            page = PagePath.ERROR_500.getDirectUrl();
        }
        return CommandResult.setForwardPage(page);
    }
}