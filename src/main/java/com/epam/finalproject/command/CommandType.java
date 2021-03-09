package com.epam.finalproject.command;

import com.epam.finalproject.command.impl.*;

public enum CommandType {
    CONFIRM_ACCOUNT(new ConfirmAccountCommand()),
    INVALID(new InvalidCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    OPEN_HOME(new OpenHomeCommand()),
    OPEN_CONTACTS(new OpenContactsCommand()),
    OPEN_PERSONAL_ACCOUNT(new OpenPersonalAccountCommand()),
    OPEN_ADMIN_MAIN(new OpenAdminMainCommand()),
    OPEN_ADMIN_REGISTRATIONS(new OpenAdminRegistrationsCommand()),
    REGISTER(new RegisterCommand()),
    CHANGE_ADMIN_LOCALE(new ChangeAdminLocaleCommand());

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
