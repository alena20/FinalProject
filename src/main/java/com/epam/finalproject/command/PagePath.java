package com.epam.finalproject.command;

public enum PagePath {
    ERROR_404("/error/error_404.jsp", "/mainController?command=invalid"),
    ERROR_500("/error/error_500.jsp", ""),
    HOME("/pages/home.jsp", "/mainController?command=open_home"),
    INDEX("/index.jsp", "/mainController?command=open_home"),
    PERSONAL_ACCOUNT("/pages/personal_account.jsp", "/mainController?command=open_personal_account"),
    PERSONAL_DATA("/pages/personal_data.jsp", "/mainController?command=open_personal_data"),
    ADMIN_MAIN("/pages/admin_main.jsp", "/mainController?command=open_admin_main");

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
