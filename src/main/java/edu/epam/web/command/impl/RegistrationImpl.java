package edu.epam.web.command.impl;

import edu.epam.web.command.ICommand;
import edu.epam.web.entity.User;
import edu.epam.web.entity.UserRole;
import edu.epam.web.service.UserService;
import edu.epam.web.service.impl.UserServiceImpl;
import edu.epam.web.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationImpl implements ICommand {

    private UserService userService = UserServiceImpl.getInstance();
    public final static String AUTHORIZE_PAGE = "/pages/authorize.jsp";
    public final static String WRONG_REGISTRATION_DATA = "Пользователь с таким логином уже существует. Используйте другой.";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("pass");
        User user = new User(login, password, UserRole.CLIENT);

        if (userService.create(user)) {
            req.getSession().setAttribute("user", user);
        } else {
            req.setAttribute("error", WRONG_REGISTRATION_DATA);
            req.setAttribute("pageJsp", AUTHORIZE_PAGE);
        }
        WebUtil.forwardJSP("index", req, resp);
    }
}
