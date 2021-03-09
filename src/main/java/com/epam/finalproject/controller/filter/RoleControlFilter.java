package com.epam.finalproject.controller.filter;

import com.epam.finalproject.command.CommandType;
import com.epam.finalproject.command.PagePath;
import com.epam.finalproject.controller.CommandProvider;
import com.epam.finalproject.model.entity.User;
import com.epam.finalproject.model.entity.UserRole;
import com.epam.finalproject.util.RequestParameterName;
import com.epam.finalproject.util.SessionAttributeName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/mainController"})
public class RoleControlFilter implements Filter {
    private static final CommandRoleMap MAP = CommandRoleMap.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(RoleControlFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String command = request.getParameter(RequestParameterName.COMMAND);
        if (command != null) {
            CommandType type = CommandProvider.defineCommandType(command);
            UserRole role = defineUserRole(request);
            if (!MAP.containsRole(type, role)) {
                LOGGER.warn("Filter interception: '{}' attempted to execute '{}' command ", role, command);
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        } else {
            LOGGER.warn("Filter interception: no command to execute!");
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ERROR_404.getDirectUrl());
            dispatcher.forward(request, response);
        }
        chain.doFilter(request, response);
    }

    private UserRole defineUserRole(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttributeName.USER);
        return (user == null || !user.getAccount().getIsActive())
                ? UserRole.GUEST
                : user.getAccount().getRole();
    }
}
