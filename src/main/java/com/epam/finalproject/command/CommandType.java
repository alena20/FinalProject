package com.epam.finalproject.command;

import com.epam.finalproject.command.impl.*;

public enum CommandType {
    CONFIRM_ACCOUNT(new ConfirmAccountCommand()),
    INVALID(new InvalidCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    OPEN_HOME(new OpenHomeCommand()),
    OPEN_CONTACTS(new OpenContactsCommand()),
    OPEN_PERSONAL_ACCOUNT(new OpenPersonalAccountCommand()),
    OPEN_ADMIN_MAIN(new OpenAdminMainCommand()),
    OPEN_ADMIN_REGISTRATIONS(new OpenAdminRegistrationsCommand()),
    REGISTER(new RegisterCommand()),
    UPLOAD_IMAGE(new UploadImageCommand()),
    ADD_FEEDBACK(new AddFeedbackCommand()),
    ADD_TRAINING(new AddTrainingCommand()),
    APPROVE_TRAINER_APPLICATION(new ApproveTrainerApplicationCommand()),
    BLOCK_USER(new BlockUserCommand()),
    BUY_TRAININGS(new BuyTrainingsCommand()),
    CANCEL_TRAINING(new CancelTrainingCommand()),
    MAKE_DEPOSIT(new MakeDepositCommand()),
    OPEN_ADMIN_FEEDBACKS(new OpenAdminFeedbacksCommand()),
    OPEN_PERSONAL_DATA(new OpenPersonalDataCommand()),
    OPEN_PERSONAL_FINANCE(new OpenPersonalFinanceCommand()),
    OPEN_SCHEDULE(new OpenScheduleCommand()),
    RATE_TRAINING(new RateTrainingCommand()),
    REFUSE_TRAINER_APPLICATION(new RefuseTrainerApplicationCommand()),
    UPDATE_PERSONAL_DATA(new UpdatePersonalDataCommand()),
    CHANGE_ADMIN_LOCALE(new ChangeAdminLocaleCommand());

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
