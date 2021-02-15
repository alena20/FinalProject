package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.model.entity.User;
import com.epam.finalproject.service.UserService;
import com.epam.finalproject.service.impl.UserServiceImpl;
import com.epam.finalproject.util.ActiveUsersMap;
import com.epam.finalproject.util.RequestAttributeName;
import com.epam.finalproject.util.RequestParameterName;
import com.epam.finalproject.util.SessionAttributeName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ConfirmAccountCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(ConfirmAccountCommand.class);
    private final UserService service = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String accountId = request.getParameter(RequestParameterName.ID);
        String page;
        try {
            if (service.confirmAccount(accountId)) {
                int id = Integer.parseInt(accountId);
                Optional<String> optional = service.findEmailById(id);
                if (optional.isPresent()) {
                    User user = (User) request.getSession().getAttribute(SessionAttributeName.USER);
                    String email = optional.get();
                    request.setAttribute(RequestAttributeName.CONFIRMED_ACCOUNT, email);
                    ActiveUsersMap map = ActiveUsersMap.getInstance();
                    if (user != null && map.containsKey(id)) {
                        map.put(user.getAccount().getId(), true);
                    }
                }
                page = PagePath.INDEX.getDirectUrl();
            } else {
                page = PagePath.ERROR_404.getDirectUrl();
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            page = PagePath.ERROR_500.getDirectUrl();
        }
       // return page;
        return CommandResult.setForwardPage(page);
    }
}
