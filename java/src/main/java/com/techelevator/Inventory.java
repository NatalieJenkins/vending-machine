package com.techelevator;

import java.math.BigDecimal;

public class Inventory {  //Implements interface for message printout

    private String slotNumber;
    private String itemName;
    private BigDecimal itemPrice;
    private String typeOfItem;
    private int itemQuantity;
    private String message;


    public Inventory(String slotNumber, String itemName, BigDecimal itemPrice, String typeOfItem, int itemQuantity, String message) {
        this.slotNumber = slotNumber;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.typeOfItem = typeOfItem;
        this.itemQuantity = itemQuantity;
        this.message = message;
    }


    public String getSlotNumber() {

        return slotNumber;
    }

    public String getItemName() {

        return itemName;
    }

    public BigDecimal getItemPrice() {

        return itemPrice;
    }

    public String getTypeOfItem() {

        return typeOfItem;
    }
    public int getItemQuantity() {

        return itemQuantity;
    }

    public String getMessage() {

        return message;
    }

    public int deductInventory () {

        return this.itemQuantity -= 1;
    }

    @Override
    public String toString(){
        return getSlotNumber() + " " + getItemName() + " " + getItemPrice() + " " + getTypeOfItem()
                + " Quantity: " + getItemQuantity();
    }


}
