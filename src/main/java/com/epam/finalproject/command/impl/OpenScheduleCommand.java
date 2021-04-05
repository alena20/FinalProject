package com.epam.finalproject.command.impl;

import com.epam.finalproject.command.ActionCommand;
import com.epam.finalproject.command.CommandResult;
import com.epam.finalproject.command.PagePath;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.model.entity.*;
import com.epam.finalproject.service.TrainingService;
import com.epam.finalproject.service.impl.TrainingServiceImpl;
import com.epam.finalproject.service.impl.UserServiceImpl;
import com.epam.finalproject.util.RequestAttributeName;
import com.epam.finalproject.util.SessionAttributeName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OpenScheduleCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(OpenScheduleCommand.class);
    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    private final TrainingService trainingService = TrainingServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(SessionAttributeName.USER);
        UserRole role = user.getAccount().getRole();
        String page;
        try {
            if (role == UserRole.CLIENT) {
                openClientSchedule(request, user);
            }
            if (role == UserRole.TRAINER) {
                openTrainerSchedule(request, user);
            }
            page = PagePath.SCHEDULE.getDirectUrl();
        } catch (ServiceException e) {
            LOGGER.error(e);
            page = PagePath.ERROR_500.getDirectUrl();
        }
        return CommandResult.setRedirectPage(page);
    }

    private void openClientSchedule(HttpServletRequest request, User user) throws ServiceException {
        int userId = user.getAccount().getId();
        List<Trainer> trainers = userService.findAllTrainers();
        Map<Integer, Trainer> trainerMap = createMap(trainers);
        List<Training> trainings = trainingService.findClientTrainings(userId);
        List<Training> planned = trainings.stream().filter(t -> !t.getIsDone()).collect(Collectors.toList());
        List<Training> previous = trainings.stream().filter(Training::getIsDone).collect(Collectors.toList());
        request.setAttribute(RequestAttributeName.PLANNED_TRAININGS, planned);
        request.setAttribute(RequestAttributeName.PREVIOUS_TRAININGS, previous);
        request.setAttribute(RequestAttributeName.TRAINERS, trainers);
        request.setAttribute(RequestAttributeName.TRAINER_MAP, trainerMap);
    }

    private Map<Integer, Trainer> createMap(List<Trainer> users) {
        Map<Integer, Trainer> userMap = new HashMap<>();
        users.forEach(u -> userMap.put(u.getAccount().getId(), u));
        return userMap;
    }

    private void openTrainerSchedule(HttpServletRequest request, User user) throws ServiceException {
        int userId = user.getAccount().getId();
        List<Training> trainings = trainingService.findTrainerTrainings(userId);
        List<Training> planned = trainings.stream().filter(t -> !t.getIsDone()).collect(Collectors.toList());
        List<Training> previous = trainings.stream().filter(Training::getIsDone).collect(Collectors.toList());
        Map<Integer, Client> clientMap = trainingService.findTrainerClients(userId);
        request.setAttribute(RequestAttributeName.PLANNED_TRAININGS, planned);
        request.setAttribute(RequestAttributeName.PREVIOUS_TRAININGS, previous);
        request.setAttribute(RequestAttributeName.CLIENT_MAP, clientMap);
    }
}

