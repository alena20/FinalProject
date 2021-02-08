package com.epam.finalproject.controller.command;

public enum PagePath {
    ERROR_404("/jsp/error_404.jsp", "/mainController?command=invalid"),
    ERROR_500("/jsp/error_500.jsp", ""),
    HOME("/jsp/home.jsp", "/mainController?command=open_home"),
    INDEX("/index.jsp", "/mainController?command=open_home"),
    PERSONAL_ACCOUNT("/jsp/personal_account.jsp", "/mainController?command=open_personal_account"),
    ADMIN_MAIN("/jsp/admin_main.jsp", "/mainController?command=open_admin_main");

    private final String directUrl;
    private final String servletPath;

    PagePath(String directUrl, String servletPath) {
        this.directUrl = directUrl;
        this.servletPath = servletPath;
    }

    public String getDirectUrl() {
        return directUrl;
    }

    public String getServletPath() {
        return servletPath;
    }
}
