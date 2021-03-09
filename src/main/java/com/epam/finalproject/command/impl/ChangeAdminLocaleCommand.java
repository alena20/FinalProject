package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;
import com.epam.finalproject.model.entity.AccountLocale;
import com.epam.finalproject.model.entity.User;
import com.epam.finalproject.util.SessionAttributeName;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class ChangeAdminLocaleCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(SessionAttributeName.USER);
        String oldLocale = user.getAccount().getLocale().getPostfix();
        String newLocale = oldLocale.equals(AccountLocale.ENGLISH.getPostfix())
                ? AccountLocale.RUSSIAN.getPostfix()
                : AccountLocale.ENGLISH.getPostfix();
        restoreRequestAttributes(request);
        user.getAccount().setLocale(AccountLocale.localeByPostfix(newLocale));
        String prevPage = getPreviousPage(request);
        PagePath page = Arrays.stream(PagePath.values())
                .filter(p -> p.getDirectUrl().equals(prevPage)).findFirst().orElse(PagePath.ADMIN_MAIN);
        return CommandResult.setRedirectPage(page.getServletPath());
    }
}
