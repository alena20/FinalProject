package com.epam.finalproject.model.entity;

import java.sql.Date;

public class Account {
    private int id;
    private String login;
    private String email;
    private UserRole role;
    private Date registrationDate;
    private boolean isActive;
    private AccountLocale locale;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String name) {
        this.login = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public AccountLocale getLocale() {
        return locale;
    }

    public void setLocale(AccountLocale locale) {
        this.locale = locale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        if (id != account.id) {
            return false;
        }
        if (isActive != account.isActive) {
            return false;
        }
        if (login != null ? !login.equals(account.login) : account.login != null) {
            return false;
        }
        if (email != null ? !email.equals(account.email) : account.email != null) {
            return false;
        }
        if (role != account.role) {
            return false;
        }
        if (registrationDate != null
                ? !registrationDate.equals(account.registrationDate)
                : account.registrationDate != null) {
            return false;
        }
        return locale == account.locale;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (locale != null ? locale.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", role=").append(role);
        sb.append(", registrationDate=").append(registrationDate);
        sb.append(", isActive=").append(isActive);
        sb.append(", locale=").append(locale);
        sb.append('}');
        return sb.toString();
    }
}

