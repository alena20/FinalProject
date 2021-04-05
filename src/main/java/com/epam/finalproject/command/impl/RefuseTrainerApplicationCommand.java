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

public class RefuseTrainerApplicationCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(RefuseTrainerApplicationCommand.class);
    private final TrainerApplicationService service = TrainerApplicationServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameterName.APPLICATION_ID);
        String page;
        try {
            if (service.deleteApplication(id)) {
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
