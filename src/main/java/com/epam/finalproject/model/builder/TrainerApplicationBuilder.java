package com.epam.finalproject.model.builder;

import com.epam.finalproject.model.entity.TrainerApplication;
import com.epam.finalproject.model.entity.User;

import java.sql.Date;

public class TrainerApplicationBuilder {
    private final TrainerApplication trainerApplication;

    private TrainerApplicationBuilder() {
        trainerApplication = new TrainerApplication();
    }

    public static TrainerApplicationBuilder aTrainerApplication() {
        return new TrainerApplicationBuilder();
    }

    public TrainerApplicationBuilder withUser(User user) {
        trainerApplication.setUser(user);
        return this;
    }

    public TrainerApplicationBuilder withInstitution(String institution) {
        trainerApplication.setInstitution(institution);
        return this;
    }

    public TrainerApplicationBuilder withGraduationYear(int graduationYear) {
        trainerApplication.setGraduationYear(graduationYear);
        return this;
    }

    public TrainerApplicationBuilder withApplicationDate(Date applicationDate) {
        trainerApplication.setApplicationDate(applicationDate);
        return this;
    }

    public TrainerApplication build() {
        return trainerApplication;
    }
}
