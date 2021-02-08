package com.epam.finalproject.controller.command.impl;

import com.epam.finalproject.controller.command.ActionCommand;
import com.epam.finalproject.controller.command.PagePath;
import com.epam.finalproject.model.entity.User;
import com.epam.finalproject.service.ActiveUser;
import com.epam.finalproject.util.SessionAttributeName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttributeName.USER);
        if (user != null) {
            ActiveUser map = ActiveUser.getInstance();
            map.remove(user.getAccount().getId());
        }
        session.invalidate();
        return PagePath.INDEX.getDirectUrl();
    }
}
