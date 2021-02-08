package com.epam.finalproject.controller.command;

import com.epam.finalproject.controller.command.impl.LoginCommand;
import com.epam.finalproject.controller.command.impl.LogoutCommand;
import com.epam.finalproject.controller.command.impl.RegisterCommand;

public enum CommandType {
    CONFIRM_ACCOUNT(new ConfirmAccountCommand()),
    INVALID(new InvalidCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    OPEN_HOME(new OpenHomeCommand()),
    OPEN_PERSONAL_ACCOUNT(new OpenPersonalAccountCommand()),
    REGISTER(new RegisterCommand());

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
