package com.epam.finalproject.model.builder;

import com.epam.finalproject.model.entity.Client;

public final class ClientBuilder extends UserBuilder<ClientBuilder> {
    private ClientBuilder() {
        user = new Client();
    }

    /**
     * A client client builder.
     *
     * @return the client builder
     */
    public static ClientBuilder aClient() {
        return new ClientBuilder();
    }

    @Override
    public Client build() {
        return (Client) user;
    }

    /**
     * With money balance client builder.
     *
     * @param moneyBalance the money balance
     * @return the client builder
     */
    public ClientBuilder withMoneyBalance(double moneyBalance) {
        ((Client) user).setMoneyBalance(moneyBalance);
        return this;
    }

    /**
     * With personal discount client builder.
     *
     * @param personalDiscount the personal discount
     * @return the client builder
     */
    public ClientBuilder withPersonalDiscount(double personalDiscount) {
        ((Client) user).setPersonalDiscount(personalDiscount);
        return this;
    }

    /**
     * With bought trainings client builder.
     *
     * @param boughtTrainings the bought trainings
     * @return the client builder
     */
    public ClientBuilder withBoughtTrainings(int boughtTrainings) {
        ((Client) user).setBoughtTrainings(boughtTrainings);
        return this;
    }
}
