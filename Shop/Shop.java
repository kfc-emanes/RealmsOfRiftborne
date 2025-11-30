package Shop;

import Hero.*;
import java.util.*;
import java.text.DecimalFormat;

public class Shop {
    Scanner sc = new Scanner(System.in);
    DecimalFormat df = new DecimalFormat("#,###.##");

    private int[] itemPrice = {
        450,    // Small Health Potion
        1350,   // Medium Health Potion
        2750,   // Large Health Potion
        450,    // Small Mana Potion
        1350,   // Medium Mana Potion
        2750    // Large Mana Potion
    };

    private String[] item = {
        "Small Health Potion",
        "Medium Health Potion",
        "Large Health Potion",
        "Small Mana Potion",
        "Medium Mana Potion",
        "Large Mana Potion"
    };

    public void shop(Hero hero) {
        int choice = 0, select = 0;
        System.out.println();
        System.out.println();
        while(true){
            while(true){
                try{
                    System.out.println();
                    System.out.println();
                    System.out.println(" \tGold: " + df.format(hero.getGold()));
                    System.out.println(" \t┌─────────────────────────────────────────────────────────────────────────────┐");
                    System.out.println(" \t│                                                                             │");
                    System.out.println(" \t│                                                                             │");
                    System.out.println(" \t│                      WELCOME TO MYSTVALE ACADEMY SHOP                       │");
                    System.out.println(" \t│                                                                             │");
                    System.out.println(" \t│                                                                             │");
                    System.out.println(" \t│                   What would you like to purchase today?                    │");
                    System.out.println(" \t│                                                                             │");
                    System.out.println(" \t│                                                                             │");
                    System.out.println(" \t│       ┌─────────────────────────────┐  ┌─────────────────────────────┐      │");
                    System.out.println(" \t│       |   [1] Small Health Potion   |  |   [4] Small Mana Potion     |      │");
                    System.out.println(" \t│       |   [Price: 450g]             |  |   [Price: 450g]             |      │");
                    System.out.println(" \t│       └─────────────────────────────┘  └─────────────────────────────┘      │");
                    System.out.println(" \t│       ┌─────────────────────────────┐  ┌─────────────────────────────┐      │");
                    System.out.println(" \t│       |   [2] Medium Health Potion  |  |   [5] Medium Mana Potion    |      │");
                    System.out.println(" \t│       |   [Price: 1,350g]           |  |   [Price: 1,350g]           |      │");
                    System.out.println(" \t│       └─────────────────────────────┘  └─────────────────────────────┘      │");
                    System.out.println(" \t│       ┌─────────────────────────────┐  ┌─────────────────────────────┐      │");
                    System.out.println(" \t│       |   [3] Large Health Potion   |  |   [6] Large Mana Potion     |      │");
                    System.out.println(" \t│       |   [Price: 2,750g]           |  |   [Price: 2,750g]           |      │");
                    System.out.println(" \t│       └─────────────────────────────┘  └─────────────────────────────┘      │");
                    System.out.println(" \t│                                ┌────────────┐                               │");
                    System.out.println(" \t│                                |  [7] EXIT  |                               │");
                    System.out.println(" \t│                                └────────────┘                               │");
                    System.out.println(" \t└─────────────────────────────────────────────────────────────────────────────┘");
                    System.out.println("  \t  ___________________________________________________________________________");
                    System.out.println("  \t \\ \\                                                                       / /");
                    System.out.println("  \t  \\_\\_____________________________________________________________________/_/");
                    System.out.println("  \t        | │                                                         │ |");
                    System.out.println("  \t        |_│_________________________________________________________│_|");
                    System.out.println("  \t         \\___________________________________________________________/");
                    System.out.print(">>> ");
                    choice = sc.nextInt();

                    if(choice < 1 || choice > 7){
                        System.out.println("\nInvalid choice. Please enter a number between 1 and 7.\n\n");
                        continue;
                    }

                    break;

                } catch (Exception e) {
                    System.out.println("\nInvalid input. Please enter a number corresponding to your choice.\n\n");
                    sc.nextLine(); // Clear the invalid input
                }
            }

            if(choice == 7){
                System.out.println();
                System.out.println();
                System.out.println("\nThank you for visiting Mystvale Academy Shop! See you again!\n\n");
                return;
            }

            while(true){
                try{
                    System.out.println();
                    System.out.println("You have selected: " + getItem(choice));
                    System.out.println("[1] Purchase Item");
                    System.out.println("[2] View Item Details");
                    System.out.println("[3] Cancel");
                    System.out.print(">>> ");
                    select = sc.nextInt();

                    if(select < 1 || select > 3){
                        System.out.println("\nInvalid choice. Please enter a number between 1 and 3.\n\n");
                        continue;
                    }

                    break;

                } catch (Exception e) {
                    System.out.println("\nInvalid input. Please enter a number corresponding to your choice.\n\n");
                    sc.nextLine(); // Clear the invalid input
                }
            }

            switch(select){
                case 1:
                    int amount = 0;
                    int price = getItemPrice(choice);
                    while(true){
                        try{
                            System.out.print("\n\nEnter Amount to Purchase: ");
                            amount = sc.nextInt();

                            if(amount <= 0){
                                System.out.println("\nInvalid amount. Please enter a positive number.\n\n");
                                continue;
                            }

                            if(amount > 99){
                                System.out.println("\nYou can only store up to 99 " + getItem(choice) + ".\n\n");
                                continue;
                            }

                            break;
                        } catch (Exception e) {
                            System.out.println("\nInvalid input. Please enter a valid amount.\n\n");
                            sc.nextLine(); // Clear the invalid input
                            continue;
                        }
                    }
                    for(int i = 0; i < amount; i++){
                        purchaseItem(hero, choice);
                    }

                    System.out.println("\nPurchased " + amount + " " + getItem(choice) + "(s) for " + df.format(price * amount) + "g.");
                    break;
                case 2:
                    itemDetails(choice);
                    break;
                case 3:
                    System.out.println("\nTransaction cancelled.\n\n");
                    break;
            }
        }
    }

    public void itemDetails(int choice) {
        System.out.println();

        switch (choice) {
            case 1 -> System.out.println("Small Health Potion: Restores 20% HP.");
            case 2 -> System.out.println("Medium Health Potion: Restores 45% HP.");
            case 3 -> System.out.println("Large Health Potion: Restores 70% HP.");
            case 4 -> System.out.println("Small Mana Potion: Restores 20% Mana.");
            case 5 -> System.out.println("Medium Mana Potion: Restores 45% Mana.");
            case 6 -> System.out.println("Large Mana Potion: Restores 70% Mana.");
            default -> System.out.println("Invalid choice.");
        }

        System.out.println();
    }

    public void purchaseItem(Hero hero, int choice) {

        // capacity check per item type
        switch (choice) {
            case 1 -> {
                if (hero.getInventory().getSmallHealthPotion() >= hero.getInventory().getCapacity()) {
                    System.out.println("\nCapacity reached for Small Health Potions.");
                    return;
                }
            }
            case 2 -> {
                if (hero.getInventory().getMediumHealthPotion() >= hero.getInventory().getCapacity()) {
                    System.out.println("\nCapacity reached for Medium Health Potions.");
                    return;
                }
            }
            case 3 -> {
                if (hero.getInventory().getLargeHealthPotion() >= hero.getInventory().getCapacity()) {
                    System.out.println("\nCapacity reached for Large Health Potions.");
                    return;
                }
            }
            case 4 -> {
                if (hero.getInventory().getSmallManaPotion() >= hero.getInventory().getCapacity()) {
                    System.out.println("\nCapacity reached for Small Mana Potions.");
                    return;
                }
            }
            case 5 -> {
                if (hero.getInventory().getMediumManaPotion() >= hero.getInventory().getCapacity()) {
                    System.out.println("\nCapacity reached for Medium Mana Potions.");
                    return;
                }
            }
            case 6 -> {
                if (hero.getInventory().getLargeManaPotion() >= hero.getInventory().getCapacity()) {
                    System.out.println("\nCapacity reached for Large Mana Potions.");
                    return;
                }
            }
        }

        int price = getItemPrice(choice);

        if (hero.getGold() < price) {
            System.out.println("\nYou do not have enough gold.\n");
            return;
        }

        hero.setGold(hero.getGold() - price);

        switch (choice) {
            case 1 -> hero.getInventory().setSmallHealthPotion(hero.getInventory().getSmallHealthPotion() + 1);
            case 2 -> hero.getInventory().setMediumHealthPotion(hero.getInventory().getMediumHealthPotion() + 1);
            case 3 -> hero.getInventory().setLargeHealthPotion(hero.getInventory().getLargeHealthPotion() + 1);
            case 4 -> hero.getInventory().setSmallManaPotion(hero.getInventory().getSmallManaPotion() + 1);
            case 5 -> hero.getInventory().setMediumManaPotion(hero.getInventory().getMediumManaPotion() + 1);
            case 6 -> hero.getInventory().setLargeManaPotion(hero.getInventory().getLargeManaPotion() + 1);
        }
    }

    public int getItemPrice(int choice) {
        return itemPrice[choice - 1];
    }

    public String getItem(int choice) {
        return item[choice - 1];
    }
}