package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.util.RequestParameterName;
import com.epam.finalproject.util.SessionAttributeName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

public class ChangeLanguageCommand  implements ActionCommand {
    private static final String RU = "ru";
    private static final String EN = "en";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String language = request.getParameter(RequestParameterName.LOCALE);
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(SessionAttributeName.CURRENT_PAGE);
        switch (language) {
            case RU :
                session.setAttribute(RequestParameterName.LOCALE, RU);
                break;
            case EN :
                session.setAttribute(RequestParameterName.LOCALE, EN);
                break;
            default:
                session.setAttribute(RequestParameterName.LOCALE, RU);
        }
        return CommandResult.setRedirectPage(page);
    }
}
