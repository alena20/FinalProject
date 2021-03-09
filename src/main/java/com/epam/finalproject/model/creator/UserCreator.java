package com.epam.finalproject.model.creator;

import com.epam.finalproject.model.entity.Account;
import com.epam.finalproject.model.entity.User;

public abstract class UserCreator<B extends UserCreator<B>> {

    protected User user;

    protected UserCreator() {
    }

    public B withAccount(Account account) {
        user.setAccount(account);
        return (B) this;
    }

    public B withFirstName(String firstName) {
        user.setFirstName(firstName);
        return (B) this;
    }

    public B withLastName(String lastName) {
        user.setLastName(lastName);
        return (B) this;
    }

    public B withPhoneNumber(String phoneNumber) {
        user.setPhoneNumber(phoneNumber);
        return (B) this;
    }

    public B withImageName(String imageName) {
        user.setImageName(imageName);
        return (B) this;
    }

    public abstract User build();
}
