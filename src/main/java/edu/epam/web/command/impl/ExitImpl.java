package edu.epam.web.command.impl;

import edu.epam.web.command.ICommand;
import edu.epam.web.service.UserService;
import edu.epam.web.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExitImpl implements ICommand {

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute("user");
        req.getSession().invalidate();
        try {
            resp.sendRedirect("/demoproject/index.jsp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
