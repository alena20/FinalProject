package edu.epam.web.servlet;

import edu.epam.web.command.Factory;
import edu.epam.web.command.ICommand;
import edu.epam.web.entity.ECommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "AuthorizeServlet", urlPatterns = {"/user"})
public class AuthorizeServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AuthorizeServlet.class);
    private final Factory factory = Factory.getInstance();

    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        ECommand userAction = ECommand.valueOf(req.getParameter("action"));
        ICommand command = factory.getCommand(userAction);
        command.execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        ECommand userAction = ECommand.valueOf(req.getParameter("action"));
        ICommand command = factory.getCommand(userAction);
        command.execute(req, resp);
    }

    public void destroy() {
    }
}