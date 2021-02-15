package com.epam.finalproject.controller;

import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;
import com.epam.finalproject.connection.impl.ConnectionPool;
import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.util.RequestParameterName;
import com.epam.finalproject.util.SessionAttributeName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;
import java.util.Iterator;

@WebServlet(urlPatterns = "/mainController")
public class MainServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(MainServlet.class);
    private static final int BUFFER_SIZE = 4096;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter(RequestParameterName.COMMAND);
        ActionCommand command = CommandProvider.defineCommand(commandName);
        CommandResult commandResult = command.execute(request);
        String page = commandResult.getPage();
        if (page == null)
            response.sendRedirect(request.getContextPath()+PagePath.ERROR_404.getDirectUrl());
        if(commandResult.isRedirect()){
            response.sendRedirect(request.getContextPath()+page);
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request,response);
        }

    }

    private void saveRequestAttributes(HttpServletRequest request) {
        Enumeration<String> attrNames = request.getAttributeNames();
        Iterator<String> iterator = attrNames.asIterator();
        RequestAttributesWrapper wrapper = new RequestAttributesWrapper();
        while (iterator.hasNext()) {
            String name = iterator.next();
            Object value = request.getAttribute(name);
            wrapper.put(name, value);
        }
        request.getSession().setAttribute(SessionAttributeName.SAVED_ATTRIBUTES, wrapper);
    }

    @Override
    public void init() throws ServletException {
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}
