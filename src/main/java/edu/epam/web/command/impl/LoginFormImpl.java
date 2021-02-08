package edu.epam.web.command.impl;

import edu.epam.web.command.ICommand;
import edu.epam.web.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFormImpl implements ICommand {
    public final static String LOGIN_PAGE = "/pages/login.jsp";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        Object user = req.getSession().getAttribute("user");
        if (user == null) {
            req.setAttribute("pageJsp", LOGIN_PAGE);
        }
        WebUtil.forwardJSP("index", req, resp);
    }
}

