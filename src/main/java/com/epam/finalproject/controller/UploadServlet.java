package com.epam.finalproject.controller;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.impl.UploadImageCommand;
import com.epam.finalproject.util.RequestParameterName;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/uploadController")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ActionCommand command = new UploadImageCommand();
        CommandResult commandResult = command.execute(request);
        String page = commandResult.getPage();
        response.sendRedirect(page);
    }

    private String processRequest(HttpServletRequest request) {
        String commandName = request.getParameter(RequestParameterName.COMMAND);
        ActionCommand command = CommandProvider.defineCommand(commandName);
        CommandResult commandResult = command.execute(request);
        String page = commandResult.getPage();
        return page;
    }
}
