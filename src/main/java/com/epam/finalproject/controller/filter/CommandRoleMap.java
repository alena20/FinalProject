package com.epam.finalproject.controller.filter;

import com.epam.finalproject.command.CommandType;
import com.epam.finalproject.model.entity.UserRole;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class CommandRoleMap {
    private static final CommandRoleMap INSTANCE = new CommandRoleMap();
    private static final EnumMap<CommandType, List<UserRole>> ROLE_MAP;

    static {
        ROLE_MAP = new EnumMap<>(CommandType.class);
        ROLE_MAP.put(CommandType.CHANGE_ADMIN_LOCALE, Arrays.asList(UserRole.ADMIN));
        ROLE_MAP.put(CommandType.CONFIRM_ACCOUNT,
                Arrays.asList(UserRole.TRAINER, UserRole.CLIENT, UserRole.GUEST, UserRole.ADMIN));
        ROLE_MAP.put(CommandType.INVALID,
                Arrays.asList(UserRole.TRAINER, UserRole.CLIENT, UserRole.GUEST, UserRole.ADMIN));
        ROLE_MAP.put(CommandType.LOGIN, Arrays.asList(UserRole.GUEST));
        ROLE_MAP.put(CommandType.LOGOUT,
                Arrays.asList(UserRole.TRAINER, UserRole.CLIENT, UserRole.ADMIN, UserRole.GUEST));
        ROLE_MAP.put(CommandType.OPEN_ADMIN_MAIN, Arrays.asList(UserRole.ADMIN));
        ROLE_MAP.put(CommandType.OPEN_ADMIN_REGISTRATIONS, Arrays.asList(UserRole.ADMIN));
        ROLE_MAP.put(CommandType.OPEN_CONTACTS,
                Arrays.asList(UserRole.TRAINER, UserRole.CLIENT, UserRole.GUEST, UserRole.ADMIN));
        ROLE_MAP.put(CommandType.OPEN_HOME,
                Arrays.asList(UserRole.TRAINER, UserRole.CLIENT, UserRole.GUEST, UserRole.ADMIN));
        ROLE_MAP.put(CommandType.OPEN_PERSONAL_ACCOUNT, Arrays.asList(UserRole.TRAINER, UserRole.CLIENT));
        ROLE_MAP.put(CommandType.REGISTER, Arrays.asList(UserRole.GUEST));
       /* ROLE_MAP.put(CommandType.MAKE_DEPOSIT, Arrays.asList(UserRole.CLIENT));
        ROLE_MAP.put(CommandType.OPEN_ADMIN_FEEDBACKS, Arrays.asList(UserRole.ADMIN));
        ROLE_MAP.put(CommandType.ADD_FEEDBACK, Arrays.asList(UserRole.TRAINER, UserRole.CLIENT, UserRole.GUEST));
        ROLE_MAP.put(CommandType.ADD_TRAINING, Arrays.asList(UserRole.CLIENT));
        ROLE_MAP.put(CommandType.APPROVE_TRAINER_APPLICATION, Arrays.asList(UserRole.ADMIN));
        ROLE_MAP.put(CommandType.BLOCK_USER, Arrays.asList(UserRole.ADMIN));
        ROLE_MAP.put(CommandType.BUY_TRAININGS, Arrays.asList(UserRole.CLIENT));
        ROLE_MAP.put(CommandType.CANCEL_TRAINING, Arrays.asList(UserRole.TRAINER, UserRole.CLIENT));
        ROLE_MAP.put(CommandType.OPEN_PERSONAL_DATA, Arrays.asList(UserRole.TRAINER, UserRole.CLIENT));
        ROLE_MAP.put(CommandType.OPEN_PERSONAL_FINANCE, Arrays.asList(UserRole.CLIENT));
        ROLE_MAP.put(CommandType.OPEN_SCHEDULE, Arrays.asList(UserRole.TRAINER, UserRole.CLIENT));
        ROLE_MAP.put(CommandType.RATE_TRAINING, Arrays.asList(UserRole.CLIENT));
        ROLE_MAP.put(CommandType.REFUSE_TRAINER_APPLICATION, Arrays.asList(UserRole.ADMIN));
        ROLE_MAP.put(CommandType.SEND_FEEDBACK_REPLY, Arrays.asList(UserRole.ADMIN));
        ROLE_MAP.put(CommandType.SEND_TRAINER_APPLICATION, Arrays.asList(UserRole.CLIENT));
        ROLE_MAP.put(CommandType.SET_TRAINING_DONE, Arrays.asList(UserRole.TRAINER));
        ROLE_MAP.put(CommandType.UNBLOCK_USER, Arrays.asList(UserRole.ADMIN));
        ROLE_MAP.put(CommandType.UPDATE_ACCOUNT_DATA, Arrays.asList(UserRole.TRAINER, UserRole.CLIENT));
        ROLE_MAP.put(CommandType.UPDATE_DISCOUNT, Arrays.asList(UserRole.ADMIN));
        ROLE_MAP.put(CommandType.UPDATE_PERSONAL_DATA, Arrays.asList(UserRole.TRAINER, UserRole.CLIENT));
        ROLE_MAP.put(CommandType.UPDATE_SHORT_SUMMARY, Arrays.asList(UserRole.TRAINER));
        ROLE_MAP.put(CommandType.UPDATE_TRAINING, Arrays.asList(UserRole.CLIENT));
        ROLE_MAP.put(CommandType.UPDATE_TRAINING_DESCRIPTION, Arrays.asList(UserRole.TRAINER));
        ROLE_MAP.put(CommandType.UPLOAD_IMAGE, Arrays.asList(UserRole.TRAINER, UserRole.CLIENT));*/
    }

    private CommandRoleMap() {

    }

    static CommandRoleMap getInstance() {
        return INSTANCE;
    }

    public boolean containsRole(CommandType type, UserRole role) {
        return ROLE_MAP.get(type).contains(role);
    }
}

