package com.epam.finalproject.model.builder;

import com.epam.finalproject.model.entity.Trainer;

public final class TrainerBuilder extends UserBuilder<TrainerBuilder> {

    private TrainerBuilder() {
        user = new Trainer();
    }

    public static TrainerBuilder aTrainer() {
        return new TrainerBuilder();
    }

    @Override
    public Trainer build() {
        return (Trainer) user;
    }

    public TrainerBuilder withInstitution(String institution) {
        ((Trainer) user).setInstitution(institution);
        return this;
    }

    public TrainerBuilder withGraduationYear(int graduationYear) {
        ((Trainer) user).setGraduationYear(graduationYear);
        return this;
    }

    public TrainerBuilder withShortSummary(String shortSummary) {
        ((Trainer) user).setShortSummary(shortSummary);
        return this;
    }

    /**
     * With rating trainer builder.
     *
     * @param rating the rating
     * @return the trainer builder
     */
    public TrainerBuilder withRating(double rating) {
        ((Trainer) user).setRating(rating);
        return this;
    }
}

