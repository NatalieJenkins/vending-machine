package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING);

public class PurchaseTest {

    @Test
    public void feedMoneyTest_and_adds_5_returns_5(){
        //Arrange
     Purchase purchase = new Purchase();
 //    BigDecimal testNum = new BigDecimal(0);
     BigDecimal floatMoney = new BigDecimal(5);

        //Act
    BigDecimal actual = purchase.feedMoney(floatMoney);
    BigDecimal expected = new BigDecimal(5);

        //Assert

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void feedMoneyTest_starts_with_10_and_adds_5_returns_15(){
        //Arrange
        Purchase purchase = new Purchase();
        purchase.setCurrentMoneyProvided(BigDecimal.valueOf(10));
        BigDecimal floatMoney = new BigDecimal(5);

        //Act
        BigDecimal actual = purchase.feedMoney(floatMoney);
        BigDecimal expected = new BigDecimal(15);

        //Assert

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void feedMoneyTest_starts_with_10_and_adds_0_returns_10(){
        //Arrange
        Purchase purchase = new Purchase();
        purchase.setCurrentMoneyProvided(BigDecimal.valueOf(10));
        BigDecimal floatMoney = new BigDecimal(0);

        //Act
        BigDecimal actual = purchase.feedMoney(floatMoney);
        BigDecimal expected = new BigDecimal(10);

        //Assert

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void subtractMoneyTest_starts_with_10_and_subtracts_1_dollar_50_returns_8_50(){
        //Arrange
        Purchase purchase = new Purchase();
        purchase.setCurrentMoneyProvided(BigDecimal.valueOf(10));
        BigDecimal moneySpent = new BigDecimal(1.50);

        //Act
        BigDecimal actual = purchase.subtractMoney(moneySpent);
        BigDecimal expected = new BigDecimal(8.50);

        //Assert

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void subtractMoneyTest_starts_with_8_dollars_50_cents_subtract_50_cents_returns_8(){
        //Arrange
        Purchase purchase = new Purchase();
        purchase.setCurrentMoneyProvided(BigDecimal.valueOf(8.50));
        BigDecimal moneySpent = new BigDecimal(0.50);

        //Act
        BigDecimal actual = purchase.subtractMoney(moneySpent);
        BigDecimal expected = new BigDecimal(8.0).setScale(1);

        //Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCurrentMoneyProvidedTest_0_Balance (){
        //Assert
        Purchase purchase = new Purchase();
        // BigDecimal money = new BigDecimal();
        purchase.setCurrentMoneyProvided(BigDecimal.valueOf(0));
        //Act
        BigDecimal actual = purchase.getCurrentMoneyProvided();
        BigDecimal expected = new BigDecimal(0);
        //Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCurrentMoneyProvidedTest_8_balance(){
        //Assert
        Purchase purchase = new Purchase();
        // BigDecimal money = new BigDecimal();
        purchase.setCurrentMoneyProvided(BigDecimal.valueOf(8));
        //Act
        BigDecimal actual = purchase.getCurrentMoneyProvided();
        BigDecimal expected = new BigDecimal(8);
        //Assert
        Assert.assertEquals(expected, actual);
    }
}