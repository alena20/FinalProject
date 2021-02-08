package com.epam.finalproject.model.entity;

import com.epam.finalproject.model.creator.AccountCreator;

public class Client extends User {
    private double moneyBalance;
    private double personalDiscount;
    private int boughtTrainings;

    public Client() {
        account = AccountCreator.anAccount().build();
        account.setRole(UserRole.CLIENT);
    }

    public Client(Account account) {
        account.setRole(UserRole.CLIENT);
        this.account = account;
    }

    public double getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(double moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public double getPersonalDiscount() {
        return personalDiscount;
    }

    public void setPersonalDiscount(double personalDiscount) {
        this.personalDiscount = personalDiscount;
    }

    public int getBoughtTrainings() {
        return boughtTrainings;
    }

    public void setBoughtTrainings(int boughtTrainings) {
        this.boughtTrainings = boughtTrainings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Client client = (Client) o;

        if (Double.compare(client.moneyBalance, moneyBalance) != 0) {
            return false;
        }
        if (Double.compare(client.personalDiscount, personalDiscount) != 0) {
            return false;
        }
        if (boughtTrainings != client.boughtTrainings) {
            return false;
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(moneyBalance);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(personalDiscount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + boughtTrainings;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("moneyBalance=").append(moneyBalance);
        sb.append(", personalDiscount=").append(personalDiscount);
        sb.append(", boughtTrainings=").append(boughtTrainings);
        sb.append(", account=").append(account);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", imageName='").append(imageName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
