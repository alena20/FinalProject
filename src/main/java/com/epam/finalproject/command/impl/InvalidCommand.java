package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;
import com.epam.finalproject.util.RequestParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class InvalidCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(InvalidCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String command = request.getParameter(RequestParameterName.COMMAND);
        LOGGER.error("Command '{}' is not defined!", command);
        //return PagePath.ERROR_404.getDirectUrl();
        return CommandResult.setRedirectPage(PagePath.ERROR_404.getDirectUrl());
    }
}
