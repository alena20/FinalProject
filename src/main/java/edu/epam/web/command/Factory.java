package edu.epam.web.command;

import edu.epam.web.command.impl.*;
import edu.epam.web.entity.ECommand;

public class Factory {
    private static final Factory instance = new Factory();
    private Factory() {
    }

    public static Factory getInstance(){
        return instance;
    }

    public ICommand getCommand(ECommand action){
        ICommand command = null;
        switch (action){
            case EXIT: command = new ExitImpl();
                break;
            case LOGIN: command = new LoginImpl();
                break;
            case REGISTRATION: command = new RegistrationImpl();
                break;
            case REGISTRATION_FORM: command = new RegistrationFormImpl();
                break;
            case LOGIN_FORM: command = new LoginFormImpl();
        }
        return command;
    }
}
