package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;
import com.epam.finalproject.util.RequestAttributeName;
import com.epam.finalproject.util.SidebarTabName;

import javax.servlet.http.HttpServletRequest;

public class OpenPersonalDataCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        request.setAttribute(RequestAttributeName.ACTIVE_TAB, SidebarTabName.PERSONAL_DATA_TAB);
        String page = PagePath.PERSONAL_DATA.getDirectUrl();
        return CommandResult.setRedirectPage(page);
    }
}
