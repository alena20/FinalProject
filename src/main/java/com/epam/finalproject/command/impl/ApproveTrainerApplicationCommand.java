package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.TrainerApplicationService;
import com.epam.finalproject.service.impl.TrainerApplicationServiceImpl;
import com.epam.finalproject.util.RequestParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ApproveTrainerApplicationCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(ApproveTrainerApplicationCommand.class);
    private final TrainerApplicationService appService = TrainerApplicationServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String institution = request.getParameter(RequestParameterName.APPLICATION_INSTITUTION);
        String id = request.getParameter(RequestParameterName.APPLICATION_ID);
        String graduation = request.getParameter(RequestParameterName.APPLICATION_GRADUATION);
        String page;
        try {
            if (appService.approveApplication(id, institution, graduation)) {
                page = PagePath.ADMIN_MAIN.getServletPath();
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
