package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.model.entity.User;
import com.epam.finalproject.service.UserService;
import com.epam.finalproject.service.impl.UserServiceImpl;
import com.epam.finalproject.util.RequestAttributeName;
import com.epam.finalproject.util.RequestParameterName;
import com.epam.finalproject.util.SidebarTabName;
import com.epam.finalproject.validator.ValidationError;
import com.epam.finalproject.validator.ValidationErrorSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OpenAdminRegistrationsCommand implements ActionCommand {
    private static final String DEFAULT_DAYS = "30";
    private static final Logger LOGGER = LogManager.getLogger(OpenAdminRegistrationsCommand.class);
    private final UserService service = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String days = request.getParameter(RequestParameterName.RECENT_DAYS);
        String page;
        try {
            List<User> users = service.findRecentUsers(days);
            ValidationErrorSet errorSet = ValidationErrorSet.getInstance();
            if (errorSet.contains(ValidationError.INVALID_NUMBER_FORMAT)) {
                errorSet.clear();
                page = PagePath.ERROR_404.getDirectUrl();
            } else {
                days = days == null ? DEFAULT_DAYS : days;
                request.setAttribute(RequestAttributeName.DAYS, days);
                request.setAttribute(RequestAttributeName.ACTIVE_TAB, SidebarTabName.REGISTRATIONS_TAB);
                request.setAttribute(RequestAttributeName.RECENT_USERS, users);
                page = PagePath.ADMIN_REGISTRATIONS.getDirectUrl();
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            page = PagePath.ERROR_500.getDirectUrl();
        }
        return CommandResult.setForwardPage(page);
    }
}
