package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.model.entity.User;
import com.epam.finalproject.service.ActiveUser;
import com.epam.finalproject.service.UserService;
import com.epam.finalproject.service.impl.UserServiceImpl;
import com.epam.finalproject.util.MailUtility;
import com.epam.finalproject.util.RequestAttributeName;
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

public class RegisterCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);
    private final UserService service = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String login = request.getParameter(RequestParameterName.REGISTRATION_LOGIN);
        String password = request.getParameter(RequestParameterName.REGISTRATION_PASSWORD);
        String repeatPassword = request.getParameter(RequestParameterName.REPEAT_PASSWORD);
        String email = request.getParameter(RequestParameterName.REGISTRATION_EMAIL);
        Map<String, String> parameters = new HashMap<>();
        parameters.put(RequestParameterName.REGISTRATION_LOGIN, login);
        parameters.put(RequestParameterName.REGISTRATION_PASSWORD, password);
        parameters.put(RequestParameterName.REPEAT_PASSWORD, repeatPassword);
        parameters.put(RequestParameterName.REGISTRATION_EMAIL, email);
        String page;
        try {
            HttpSession session = request.getSession();
            Optional<User> optionalUser = service.registerUser(parameters);
            if (optionalUser.isPresent()) {
                ActiveUser map = ActiveUser.getInstance();
                User user = optionalUser.get();
                MailUtility.sendConfirmMessage(user.getAccount().getEmail(), user.getAccount().getId());
                map.put(user.getAccount().getId(), user.getAccount().getIsActive());
                session.setAttribute(SessionAttributeName.USER, user);
                session.setAttribute(RequestAttributeName.CONFIRMATION_SENT, true);
            } else {
                ValidationErrorSet errorSet = ValidationErrorSet.getInstance();
                session.setAttribute(SessionAttributeName.REGISTRATION_MAP, parameters);
                session.setAttribute(SessionAttributeName.ERROR_SET, errorSet.getAllAndClear());
            }
            page = PagePath.INDEX.getDirectUrl();
        } catch (ServiceException e) {
            LOGGER.error("Unable to register new user!", e);
            page = PagePath.ERROR_500.getDirectUrl();
        }
        //return page;
        return CommandResult.setForwardPage(page);
    }
}
