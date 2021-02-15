package com.epam.finalproject.command;

public class CommandResult {
    private final String page;
    private final boolean isRedirect;

    private CommandResult(String page, boolean isRedirect) {
        this.page = page;
        this.isRedirect = isRedirect;
    }

    public static CommandResult setRedirectPage(String page) {
        return new CommandResult(page, true);
    }

    public static CommandResult setForwardPage(String page) {
        return new CommandResult(page, false);
    }

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }
}
