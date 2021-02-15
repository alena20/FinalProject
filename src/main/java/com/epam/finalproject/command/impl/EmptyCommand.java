package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String page = PagePath.INDEX.getDirectUrl();
        return CommandResult.setRedirectPage(page);
    }
}
