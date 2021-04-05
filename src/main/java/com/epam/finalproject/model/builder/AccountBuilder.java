package com.epam.finalproject.model.builder;

import com.epam.finalproject.model.entity.Account;
import com.epam.finalproject.model.entity.AccountLocale;
import com.epam.finalproject.model.entity.UserRole;

import java.sql.Date;

public final class AccountBuilder {
    private final Account account;

    private AccountBuilder() {
        account = new Account();
    }

    public static AccountBuilder anAccount() {
        return new AccountBuilder();
    }

    public AccountBuilder withId(int id) {
        account.setId(id);
        return this;
    }

    public AccountBuilder withName(String login) {
        account.setLogin(login);
        return this;
    }

    public AccountBuilder withEmail(String email) {
        account.setEmail(email);
        return this;
    }

    public AccountBuilder withRole(UserRole role) {
        account.setRole(role);
        return this;
    }

    public AccountBuilder withRegistrationDate(Date registrationDate) {
        account.setRegistrationDate(registrationDate);
        return this;
    }

    public AccountBuilder withIsActive(boolean isActive) {
        account.setIsActive(isActive);
        return this;
    }

    public AccountBuilder withLocale(AccountLocale locale) {
        account.setLocale(locale);
        return this;
    }

    public Account build() {
        return account;
    }
}
