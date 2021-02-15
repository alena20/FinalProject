package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class OpenAdminMainCommand  implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(OpenAdminMainCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String page;
        page = PagePath.ADMIN_MAIN.getDirectUrl();
        return CommandResult.setRedirectPage(page);
    }
}