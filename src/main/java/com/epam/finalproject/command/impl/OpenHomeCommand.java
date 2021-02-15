package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.model.entity.Trainer;
import com.epam.finalproject.service.UserService;
import com.epam.finalproject.service.impl.UserServiceImpl;
import com.epam.finalproject.util.RequestAttributeName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OpenHomeCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(OpenHomeCommand.class);
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String page;
        try {
            List<Trainer> trainers = userService.findAllTrainers();
            request.setAttribute(RequestAttributeName.TRAINERS, trainers);
            page = PagePath.HOME.getDirectUrl();
        } catch (ServiceException e) {
            LOGGER.error(e);
            page = PagePath.ERROR_500.getDirectUrl();
        }
        //return page;
        return CommandResult.setRedirectPage(page);
    }
}