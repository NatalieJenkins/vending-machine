package com.techelevator;

import java.math.BigDecimal;

public class Purchase {

    private BigDecimal currentMoneyProvided;

    public Purchase() { //constructor

        this.currentMoneyProvided = new BigDecimal(0);
    }

    public BigDecimal getCurrentMoneyProvided() { //getter

        return currentMoneyProvided;
    }

    public void setCurrentMoneyProvided(BigDecimal currentMoneyProvided) {
        this.currentMoneyProvided = currentMoneyProvided;
    }

    public BigDecimal feedMoney(BigDecimal floatMoney) {   //Big Decimal is IMMUTABLE -- meaning it works like a String

        return this.currentMoneyProvided = this.currentMoneyProvided.add(floatMoney);
    }

    public BigDecimal subtractMoney(BigDecimal moneySpent) {

        return this.currentMoneyProvided = this.currentMoneyProvided.subtract(moneySpent);
    }
}
