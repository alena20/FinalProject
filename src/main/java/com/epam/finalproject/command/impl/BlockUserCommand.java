package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.UserService;
import com.epam.finalproject.service.impl.UserServiceImpl;
import com.epam.finalproject.util.ActiveUsersMap;
import com.epam.finalproject.util.RequestParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class BlockUserCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(BlockUserCommand.class);
    private final UserService service = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameterName.USER_ID);
        String page;
        try {
            if (service.blockUser(id)) {
                int userId = Integer.parseInt(id);
                ActiveUsersMap map = ActiveUsersMap.getInstance();
                map.put(userId, false);
                page = PagePath.ADMIN_REGISTRATIONS.getServletPath();
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

