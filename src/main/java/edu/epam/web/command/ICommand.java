package edu.epam.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {

    void execute(HttpServletRequest req, HttpServletResponse resp);
}