package com.epam.finalproject.model.creator;

import com.epam.finalproject.model.entity.Account;
import com.epam.finalproject.model.entity.AccountLocale;
import com.epam.finalproject.model.entity.UserRole;

import java.sql.Date;

public final class AccountCreator {
    private final Account account;

    private AccountCreator() {
        account = new Account();
    }

    public static AccountCreator anAccount() {
        return new AccountCreator();
    }

    public AccountCreator withId(int id) {
        account.setId(id);
        return this;
    }

    public AccountCreator withName(String login) {
        account.setLogin(login);
        return this;
    }

    public AccountCreator withEmail(String email) {
        account.setEmail(email);
        return this;
    }

    public AccountCreator withRole(UserRole role) {
        account.setRole(role);
        return this;
    }

    public AccountCreator withRegistrationDate(Date registrationDate) {
        account.setRegistrationDate(registrationDate);
        return this;
    }

    public AccountCreator withIsActive(boolean isActive) {
        account.setIsActive(isActive);
        return this;
    }

    public AccountCreator withLocale(AccountLocale locale) {
        account.setLocale(locale);
        return this;
    }

    public Account build() {
        return account;
    }
}
