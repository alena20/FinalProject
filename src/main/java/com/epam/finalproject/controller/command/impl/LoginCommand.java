package com.epam.finalproject.controller.command.impl;

import com.epam.finalproject.controller.command.ActionCommand;
import com.epam.finalproject.controller.command.PagePath;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.model.entity.User;
import com.epam.finalproject.model.entity.UserRole;
import com.epam.finalproject.service.ActiveUser;
import com.epam.finalproject.service.UserService;
import com.epam.finalproject.service.impl.UserServiceImpl;
import com.epam.finalproject.util.RequestParameterName;
import com.epam.finalproject.util.SessionAttributeName;
import com.epam.finalproject.validator.ValidationErrorSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginCommand  implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private final UserService service = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(RequestParameterName.LOGIN);
        String password = request.getParameter(RequestParameterName.LOGIN_PASSWORD);
        Map<String, String> parameters = new HashMap<>();
        parameters.put(RequestParameterName.LOGIN, login);
        parameters.put(RequestParameterName.LOGIN_PASSWORD, password);
        try {
            HttpSession session = request.getSession();
            Optional<User> optional = service.findUser(parameters);
            if (optional.isPresent()) {
                User user = optional.get();
                session.setAttribute(SessionAttributeName.USER, user);
                if (user.getAccount().getRole() == UserRole.ADMIN) {
                    page = PagePath.ADMIN_MAIN.getServletPath();
                } else {
                    ActiveUser map = ActiveUser.getInstance();
                    map.put(user.getAccount().getId(), user.getAccount().getIsActive());
                    page = PagePath.INDEX.getDirectUrl();
                }
            } else {
                ValidationErrorSet errorSet = ValidationErrorSet.getInstance();
                session.setAttribute(SessionAttributeName.LOGIN_MAP, parameters);
                session.setAttribute(SessionAttributeName.ERROR_SET, errorSet.getAllAndClear());
                page = PagePath.INDEX.getDirectUrl();
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            page = PagePath.ERROR_500.getDirectUrl();
        }
        return page;
    }
}
