package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class OpenContactsCommand  implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        return CommandResult.setRedirectPage(PagePath.CONTACTS.getDirectUrl());
    }
}