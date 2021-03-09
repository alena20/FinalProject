package com.epam.finalproject.model.entity;

import com.epam.finalproject.model.creator.AccountCreator;

import java.sql.Date;

public class Trainer extends User {
    private String institution;
    private int graduationYear;
    private String description;
    private Date registerDate;
    private double rating;

    //new Trainer
    public Trainer() {
        account = AccountCreator.anAccount().build();
        account.setRole(UserRole.TRAINER);
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getShortSummary() {
        return description;
    }

    public void setShortSummary(String description) {
        this.description = description;
    }

    public Date getApplicationDate() {
        return registerDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.registerDate = applicationDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) { this.rating = rating;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Trainer trainer = (Trainer) o;

        if (graduationYear != trainer.graduationYear) {
            return false;
        }
        if (Double.compare(trainer.rating, rating) != 0) {
            return false;
        }
        if (institution != null ? !institution.equals(trainer.institution) : trainer.institution != null) {
            return false;
        }
        if (description != null ? !description.equals(trainer.description) : trainer.description != null) {
            return false;
        }
        if (registerDate != null ? registerDate.equals(trainer.registerDate) : trainer.registerDate == null) {
            return false;
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (institution != null ? institution.hashCode() : 0);
        result = 31 * result + graduationYear;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Trainer{");
        sb.append("institution='").append(institution).append('\'');
        sb.append(", graduationYear=").append(graduationYear);
        sb.append(", description='").append(description).append('\'');
        sb.append(", registerDate=").append(registerDate);
        sb.append(", account=").append(account);
        sb.append(", rating=").append(rating);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", imageName='").append(imageName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}