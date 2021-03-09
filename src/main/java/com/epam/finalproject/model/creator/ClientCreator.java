package com.epam.finalproject.model.creator;

import com.epam.finalproject.model.entity.Client;

public final class ClientCreator extends UserCreator<ClientCreator> {
    private ClientCreator() {
        user = new Client();
    }

    /**
     * A client client builder.
     *
     * @return the client builder
     */
    public static ClientCreator aClient() {
        return new ClientCreator();
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
    public ClientCreator withMoneyBalance(double moneyBalance) {
        ((Client) user).setMoneyBalance(moneyBalance);
        return this;
    }

    /**
     * With personal discount client builder.
     *
     * @param personalDiscount the personal discount
     * @return the client builder
     */
    public ClientCreator withPersonalDiscount(double personalDiscount) {
        ((Client) user).setPersonalDiscount(personalDiscount);
        return this;
    }

    /**
     * With bought trainings client builder.
     *
     * @param boughtTrainings the bought trainings
     * @return the client builder
     */
    public ClientCreator withBoughtTrainings(int boughtTrainings) {
        ((Client) user).setBoughtTrainings(boughtTrainings);
        return this;
    }
}
