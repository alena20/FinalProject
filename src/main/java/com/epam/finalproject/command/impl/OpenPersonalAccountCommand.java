package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class OpenPersonalAccountCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String page = PagePath.PERSONAL_ACCOUNT.getDirectUrl();
        return CommandResult.setRedirectPage(page);
    }
}
