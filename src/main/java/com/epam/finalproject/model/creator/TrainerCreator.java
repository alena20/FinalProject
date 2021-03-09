package com.epam.finalproject.model.creator;

import com.epam.finalproject.model.entity.Trainer;

public final class TrainerCreator extends UserCreator<TrainerCreator> {

    private TrainerCreator() {
        user = new Trainer();
    }

    public static TrainerCreator aTrainer() {
        return new TrainerCreator();
    }

    @Override
    public Trainer build() {
        return (Trainer) user;
    }

    public TrainerCreator withInstitution(String institution) {
        ((Trainer) user).setInstitution(institution);
        return this;
    }

    public TrainerCreator withGraduationYear(int graduationYear) {
        ((Trainer) user).setGraduationYear(graduationYear);
        return this;
    }

    public TrainerCreator withShortSummary(String shortSummary) {
        ((Trainer) user).setShortSummary(shortSummary);
        return this;
    }

    /**
     * With rating trainer builder.
     *
     * @param rating the rating
     * @return the trainer builder
     */
    public TrainerCreator withRating(double rating) {
        ((Trainer) user).setRating(rating);
        return this;
    }
}

