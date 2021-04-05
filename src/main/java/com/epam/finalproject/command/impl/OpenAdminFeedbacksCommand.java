package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.model.entity.Feedback;
import com.epam.finalproject.service.FeedbackService;
import com.epam.finalproject.service.impl.FeedbackServiceImpl;
import com.epam.finalproject.util.RequestAttributeName;
import com.epam.finalproject.util.SidebarTabName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OpenAdminFeedbacksCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(OpenAdminFeedbacksCommand.class);
    private final FeedbackService service = FeedbackServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String page;
        try {
            List<Feedback> feedbacks = service.findAllFeedbacks();
            request.setAttribute(RequestAttributeName.ACTIVE_TAB, SidebarTabName.FEEDBACKS_TAB);
            request.setAttribute(RequestAttributeName.FEEDBACKS, feedbacks);
            page = PagePath.ADMIN_FEEDBACKS.getDirectUrl();
        } catch (ServiceException e) {
            LOGGER.error(e);
            page = PagePath.ERROR_500.getDirectUrl();
        }
        return CommandResult.setRedirectPage(page);
    }
}
