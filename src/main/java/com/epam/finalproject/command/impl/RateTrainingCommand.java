package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.TrainingService;
import com.epam.finalproject.service.impl.TrainingServiceImpl;
import com.epam.finalproject.util.RequestParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RateTrainingCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(RateTrainingCommand.class);
    private final TrainingService trainingService = TrainingServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String trainingId = request.getParameter(RequestParameterName.TRAINING_ID);
        String trainingRating = request.getParameter(RequestParameterName.TRAINING_RATING);
        String trainerId = request.getParameter(RequestParameterName.TRAINER_ID);
        String page;
        try {
            if (trainingService.rateTraining(trainingId, trainingRating, trainerId)) {
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

