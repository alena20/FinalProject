package edu.epam.web.command.impl;

import edu.epam.web.command.ICommand;
import edu.epam.web.entity.User;
import edu.epam.web.service.UserService;
import edu.epam.web.service.impl.UserServiceImpl;
import edu.epam.web.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginImpl implements ICommand {
    public final static String WRONG_LOGIN_DATA = "Неправильный логин или пароль!";
    public final static String WRONG_REGISTRATION_DATA = "Пользователь с таким логином уже существует.";

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        Optional<User> user= userService.login(login, pass);

        if (user.isPresent()) {
            req.getSession().setAttribute("user", user.get());
        } else {
            req.setAttribute("error", WRONG_LOGIN_DATA);
            req.setAttribute("pageJsp", WRONG_REGISTRATION_DATA);
        }

        WebUtil.forwardJSP("index", req, resp);
    }
}
