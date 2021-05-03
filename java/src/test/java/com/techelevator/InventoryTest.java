package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class InventoryTest {

    @Test
    public void deductInventoryTest_starts_inventory_5_returns_4() {
       //Arrange
       Inventory inventory = new Inventory("A1", "Potato Crisps", new BigDecimal(3.05),
               "Chip", 5, "Crunch Crunch, Yum!") ;
       //Act
      int actual = inventory.deductInventory();
      int expected = 4;

       // 3. call the getter and save the current inventory level

        //Assert
        Assert.assertEquals(expected, actual);
       // 4. assert that the value from step 3 is 4.
    }

    @Test
    public void deductInventoryTest_starts_inventory_1_returns_0() {
        //Arrange
        Inventory inventory = new Inventory("C2", "Dr. Salt", new BigDecimal(1.50),
                "Drink", 1, "Glug Glug, Yum!") ;
        //Act
        int actual = inventory.deductInventory();
        int expected = 0;

        // 3. call the getter and save the current inventory level

        //Assert
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getSlotNumberTest() {
        //Arrange
        Inventory inventory = new Inventory("C1", "Cola", new BigDecimal(1.25),
                "Drink", 2, "Glug Glug, Yum!") ;
        //Act
        String actual = inventory.getSlotNumber();
        String expected = "C1";

        //Assert
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getItemNameTest() {
        //Arrange
        Inventory inventory = new Inventory("A4", "Cloud Popcorn", new BigDecimal(3.65),
                "Chip", 5, "Crunch Crunch, Yum!") ;
        //Act
        String actual = inventory.getItemName();
        String expected = "Cloud Popcorn";

        //Assert
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getItemPriceTest() {
        //Arrange
        Inventory inventory = new Inventory("C4", "Heavy", new BigDecimal(1.50),
                "Drink", 4, "Glug Glug, Yum!") ;
        //Act
        BigDecimal actual = inventory.getItemPrice();
        BigDecimal expected = new BigDecimal(1.50);

        //Assert
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getTypeOfItemTest() {
        //Arrange
        Inventory inventory = new Inventory("D3", "Chiclets", new BigDecimal(.75),
                "Gum", 3, "Chew Chew, Yum!") ;
        //Act
        String actual = inventory.getTypeOfItem();
        String expected = "Gum";

        //Assert
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getItemQuantityTest() {
        //Arrange
        Inventory inventory = new Inventory("B2", "Cowtales", new BigDecimal(1.50),
                "Candy", 1, "Munch Munch, Yum!") ;
        //Act
        int actual = inventory.getItemQuantity();
        int expected = 1;

        //Assert
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getMessageTest() {
        //Arrange
        Inventory inventory = new Inventory("D3", "Chiclets", new BigDecimal(.75),
                "Gum", 3, "Chew Chew, Yum!") ;
        //Act
        String actual = inventory.getMessage();
        String expected = "Chew Chew, Yum!";

        //Assert
        Assert.assertEquals(expected, actual);

    }


}