package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.model.entity.Client;
import com.epam.finalproject.service.UserService;
import com.epam.finalproject.service.impl.UserServiceImpl;
import com.epam.finalproject.util.RequestParameterName;
import com.epam.finalproject.util.SessionAttributeName;
import com.epam.finalproject.validator.ValidationError;
import com.epam.finalproject.validator.ValidationErrorSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class BuyTrainingsCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(BuyTrainingsCommand.class);
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String trainingsNumber = request.getParameter(RequestParameterName.TRAININGS_NUMBER);
        Client client = (Client) request.getSession().getAttribute(SessionAttributeName.USER);
        String page;
        try {
            if (userService.buyTrainings(client, trainingsNumber)) {
                page = PagePath.SCHEDULE.getServletPath();
            } else {
                ValidationErrorSet errorSet = ValidationErrorSet.getInstance();
                if (errorSet.contains(ValidationError.LOW_BALANCE)) {
                    request.getSession().setAttribute(SessionAttributeName.ERROR_SET, errorSet.getAllAndClear());
                    page = PagePath.SCHEDULE.getServletPath();
                } else {
                    page = PagePath.ERROR_404.getDirectUrl();
                }
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            page = PagePath.ERROR_500.getDirectUrl();
        }
        return CommandResult.setForwardPage(page);
    }
}
