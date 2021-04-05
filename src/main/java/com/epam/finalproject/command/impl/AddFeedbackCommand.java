package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.FeedbackService;
import com.epam.finalproject.service.impl.FeedbackServiceImpl;
import com.epam.finalproject.util.RequestAttributeName;
import com.epam.finalproject.util.RequestParameterName;
import com.epam.finalproject.util.XssProtector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddFeedbackCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(AddFeedbackCommand.class);
    private final FeedbackService service = FeedbackServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String name = request.getParameter(RequestParameterName.FEEDBACK_SENDER_NAME);
        String email = request.getParameter(RequestParameterName.FEEDBACK_SENDER_EMAIL);
        String subject = request.getParameter(RequestParameterName.FEEDBACK_SUBJECT);
        String message = request.getParameter(RequestParameterName.FEEDBACK_MESSAGE);
        message = XssProtector.protect(message);
        subject = XssProtector.protect(subject);
        String page;
        try {
            if (service.addFeedback(name, email, subject, message)) {
                request.getSession().setAttribute(RequestAttributeName.FEEDBACK_SENT, true);
                page = PagePath.INDEX.getDirectUrl();
            } else {
                page = PagePath.ERROR_404.getDirectUrl();
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            page = PagePath.ERROR_500.getDirectUrl();
        }
        return CommandResult.setForwardPage(page);
    }
}
