package com.epam.finalproject.model.entity;

import java.sql.Date;

public class TrainerApplication {
    private User user;
    private String institution;
    private int graduationYear;
    private String instagramLink;
    private Date applicationDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TrainerApplication that = (TrainerApplication) o;

        if (graduationYear != that.graduationYear) {
            return false;
        }
        if (user != null ? !user.equals(that.user) : that.user != null) {
            return false;
        }
        if (institution != null ? !institution.equals(that.institution) : that.institution != null) {
            return false;
        }
        return applicationDate != null ? applicationDate.equals(that.applicationDate) : that.applicationDate == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (institution != null ? institution.hashCode() : 0);
        result = 31 * result + graduationYear;
        result = 31 * result + (applicationDate != null ? applicationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TrainerApplication{");
        sb.append("user=").append(user);
        sb.append(", institution='").append(institution).append('\'');
        sb.append(", graduationYear=").append(graduationYear);
        sb.append(", applicationDate=").append(applicationDate);
        sb.append('}');
        return sb.toString();
    }
}
