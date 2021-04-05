package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.model.entity.Client;
import com.epam.finalproject.model.entity.User;
import com.epam.finalproject.service.TrainingService;
import com.epam.finalproject.service.impl.TrainingServiceImpl;
import com.epam.finalproject.util.RequestParameterName;
import com.epam.finalproject.util.SessionAttributeName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CancelTrainingCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(CancelTrainingCommand.class);
    private final TrainingService service = TrainingServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(SessionAttributeName.USER);
        String clientId = request.getParameter(RequestParameterName.CLIENT_ID);
        String trainingId = request.getParameter(RequestParameterName.TRAINING_ID);
        String page;
        try {
            if (service.deleteTraining(trainingId, clientId)) {
                if (user instanceof Client) {
                    Client client = (Client) user;
                    ((Client) user).setBoughtTrainings(client.getBoughtTrainings() + 1);
                }
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
