package com.epam.finalproject.model.builder;

import com.epam.finalproject.model.entity.Training;

import java.sql.Date;
import java.sql.Time;

public class TrainingBuilder {
    private final Training training;

    private TrainingBuilder() {
        training = new Training();
    }

    public static TrainingBuilder aTraining() {
        return new TrainingBuilder();
    }

    public TrainingBuilder withTrainingId(int trainingId) {
        training.setTrainingId(trainingId);
        return this;
    }

    public TrainingBuilder withTrainerId(int trainerId) {
        training.setTrainerId(trainerId);
        return this;
    }

    public TrainingBuilder withTrainerFirstName(String trainerFirstName) {
        training.setTrainerFirstName(trainerFirstName);
        return this;
    }

    public TrainingBuilder withTrainerLastName(String trainerLastName) {
        training.setTrainerLastName(trainerLastName);
        return this;
    }

    public TrainingBuilder withClientId(int clientId) {
        training.setClientId(clientId);
        return this;
    }

    public TrainingBuilder withClientFirstName(String clientFirstName) {
        training.setClientFirstName(clientFirstName);
        return this;
    }

    public TrainingBuilder withClientLastName(String clientLastName) {
        training.setClientLastName(clientLastName);
        return this;
    }

    public TrainingBuilder withDate(Date date) {
        training.setTrainingDate(date);
        return this;
    }

    public TrainingBuilder withTime(Time time) {
        training.setTrainingTime(time);
        return this;
    }

    public TrainingBuilder withIsDone(boolean isDone) {
        training.setIsDone(isDone);
        return this;
    }

    public TrainingBuilder withDescription(String description) {
        training.setDescription(description);
        return this;
    }

    public TrainingBuilder withRating(int rating) {
        training.setRating(rating);
        return this;
    }

    public Training build() {
        return training;
    }
}

