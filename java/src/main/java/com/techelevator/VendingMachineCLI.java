package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTIONS_EXIT = "Exit";
    private static final String PURCHASE_MENU_OPTIONS_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTIONS_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTIONS_FINISH_TRANSACTION = "Finish Transaction";
    private static final String MONEY_INPUT_OPTIONS_1 = "1.00";
    private static final String MONEY_INPUT_OPTIONS_2 = "2.00";
    private static final String MONEY_INPUT_OPTIONS_5 = "5.00";
    private static final String MONEY_INPUT_OPTIONS_10 = "10.00";
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTIONS_FEED_MONEY, PURCHASE_MENU_OPTIONS_SELECT_PRODUCT, PURCHASE_MENU_OPTIONS_FINISH_TRANSACTION};
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTIONS_EXIT};
    private static final String[] MONEY_INPUT_OPTIONS = {MONEY_INPUT_OPTIONS_1, MONEY_INPUT_OPTIONS_2, MONEY_INPUT_OPTIONS_5, MONEY_INPUT_OPTIONS_10};
    public static List<String> VENDING_MACHINE_OPTIONS = new ArrayList<>();

    private Menu menu;
    List<Inventory> vendingItems = new ArrayList<>(); //make list named vendingItems containing Inventory

    Scanner userInput = new Scanner(System.in);
    Purchase purchase = new Purchase();
    Logger log = new Logger("C:\\Users\\Student\\workspace\\mod1-wk4-pairs-green-t6\\java\\log.txt");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

    public VendingMachineCLI(Menu menu) {

        this.menu = menu;
    }

    public void run() {
        //read in the file, load list or array - add file scanner

        try (Scanner fileScanner = new Scanner(new File("C:\\Users\\Student\\workspace" +
                "\\mod1-wk4-pairs-green-t6\\java\\vendingmachine.csv"))) {

            while (fileScanner.hasNext()) {
                int quantity = 5;
                String line = fileScanner.nextLine();
                String[] itemCharacteristics = line.split("\\|");
                BigDecimal price = new BigDecimal(itemCharacteristics[2]);

                String itemType = itemCharacteristics[3];
                String message = "";
                if (itemType.equals("Chip")) {
                    message="Crunch Crunch, Yum!";
                } else if(itemType.equals("Gum")) {
                    message="Chew Chew, Yum!";
                } else if(itemType.equals("Drink")) {
                    message="Glug Glug, Yum!";
                } else if (itemType.equals("Candy")) {
                    message = "Munch Munch, Yum!";
                }


                vendingItems.add(new Inventory(itemCharacteristics[0], itemCharacteristics[1], price,
                        itemCharacteristics[3], quantity, message));

            }
            for (Inventory item : vendingItems) {
                VENDING_MACHINE_OPTIONS.add(item.toString());
            }

        } catch (FileNotFoundException e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }

        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                for (Inventory item : vendingItems) {
                    //while loop with a .hasNext
                    System.out.println(item);
                }

            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                // do purchase
                boolean purchaseMenuRun = true;
                do {    // we need to loop back to this point so we can return to the purchase menu after
                    // each menu item executes, except for "FINISH_TRANSACTION", which boots you out
                    System.out.println("Current Balance: $" + purchase.getCurrentMoneyProvided().setScale(2));
                    System.out.println();
                    String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
                    //If purchase choice = 1 call FeedMoney "please insert money (1, 2, 5, 10?)"

                    if (purchaseChoice.equals(PURCHASE_MENU_OPTIONS_FEED_MONEY)) {

                        System.out.println("Please insert your money. I only accept $1, $2, $5, and $10");
                        String inputChoice = (String) menu.getChoiceFromOptions(MONEY_INPUT_OPTIONS);
                        if (inputChoice.equals(MONEY_INPUT_OPTIONS_1)) {
                            purchase.feedMoney(new BigDecimal(1));
                        } else if (inputChoice.equals(MONEY_INPUT_OPTIONS_2)) {
                            purchase.feedMoney(new BigDecimal(2));
                        } else if (inputChoice.equals(MONEY_INPUT_OPTIONS_5)) {
                            purchase.feedMoney(new BigDecimal(5));
                        } else if (inputChoice.equals(MONEY_INPUT_OPTIONS_10)) {
                            purchase.feedMoney(new BigDecimal(10));
                        }

                        ///append the log here---instantiation!!
                        log.write(LocalDate.now() + " " + LocalTime.now().format(formatter) + " " +
                                "FEED MONEY: $" + inputChoice + " $" +
                                purchase.getCurrentMoneyProvided().setScale(2));



                    } else if (purchaseChoice.equals(PURCHASE_MENU_OPTIONS_SELECT_PRODUCT)) {
                        System.out.println();
                        System.out.print("Please select item to purchase from the following options: ");
                        System.out.println();
                        //String itemChoice = (String) menu.getChoiceFromOptions(VENDING_MACHINE_OPTIONS.toArray());
                        for (Inventory item : vendingItems) {
                            System.out.println(item);
                        }
                        System.out.println("Enter your choice: ");
                        String itemChoice = userInput.nextLine();
                        boolean isFound = false;
                        for (Inventory item : vendingItems) {
                            double currentMoneyAsDoub = purchase.getCurrentMoneyProvided().doubleValue();
                            double itemPriceAsDoub = item.getItemPrice().doubleValue();
                          //  String message = item.getMessage();
                            if (item.getSlotNumber().equalsIgnoreCase(itemChoice)) {
                                isFound = true;
                                BigDecimal moneyBeforePur =  purchase.getCurrentMoneyProvided().setScale(2);

                                if (item.getItemQuantity() != 0 && currentMoneyAsDoub >= itemPriceAsDoub) {
                                    purchase.subtractMoney(item.getItemPrice());
                                    item.deductInventory();
                                    log.write(LocalDate.now() + " " + LocalTime.now().format(formatter) + " "
                                            + item.getItemName() + " " + item.getSlotNumber() + " $" +
                                            moneyBeforePur +  " $" +  purchase.getCurrentMoneyProvided());
                                    System.out.println("You chose: " + item.getItemName() + " which costs $"
                                            + item.getItemPrice() + ", Remaining balance: $" +
                                            purchase.getCurrentMoneyProvided().setScale(2));
                                    System.out.println(item.getMessage());   //MESSAGE!!

                                }
                                else if(currentMoneyAsDoub < itemPriceAsDoub && item.getItemQuantity() != 0){
                                    System.out.println("Not enough money! Please feed me.");
                                }
                                else {
                                    System.out.println("Item is SOLD OUT! Please select another item.");
                                }
                            }
                        }
                        if (!isFound) {
                            System.out.println("Invalid entry.");
                        }
                    } else if (purchaseChoice.equals(PURCHASE_MENU_OPTIONS_FINISH_TRANSACTION)) {
                        //   BigDecimal balance = new BigDecimal(String.valueOf(purchase.getCurrentMoneyProvided()));
                        double changeDue = purchase.getCurrentMoneyProvided().doubleValue();
                        int change = (int) (Math.ceil(changeDue * 100));
                        //create "quarter", "dime", and "nickel" variables
                        int quarters = Math.round((int) change / 25);      //use %modulus here to pass the remaining balance
                        change = change % 25;                             //first to %25
                        int dimes = Math.round((int) change / 10);          //then %10
                        change = change % 10;
                        int nickels = Math.round((int) change / 5);         //then %5
                        change = change % 5;

                        BigDecimal balanceBeforeExit =  purchase.getCurrentMoneyProvided().setScale(2);
                        
                        purchase.setCurrentMoneyProvided(BigDecimal.valueOf(0));    //currentMoneyProvided is initialized to 0 again
                        System.out.println("Your change is " + quarters + " quarters, " + dimes + " dimes, and "
                                + nickels + " nickels. You're welcome.");

                        log.write(LocalDate.now() + " " + LocalTime.now().format(formatter) + 
                               " GIVE CHANGE: $" + balanceBeforeExit +  " $" +  purchase.getCurrentMoneyProvided().setScale(2));

                        purchaseMenuRun = false;    //boots you out of the loop
                    }
                } while (purchaseMenuRun);   //continues to run while purchaseMenuRun is true

            } else if (choice.equals(MAIN_MENU_OPTIONS_EXIT)) {

                System.exit(0);
            }


        }
    }


    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
