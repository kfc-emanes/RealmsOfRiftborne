package Menu;

import Hero.*;
import Narration.*;

import java.util.Scanner;

public class CharacterMenu extends Narration{
    Scanner scanner = new Scanner(System.in);

    public Hero chooseCharacterMenu(){

        while(true){
            System.out.println("+------------------------------+");
            System.out.println("|      Choose a character      |");
            System.out.println("|------------------------------|");
            System.out.println("|  [1] Swordsman               |");
            System.out.println("|  [2] Gunner                  |");
            System.out.println("|  [3] Mage                    |");
            System.out.println("+------------------------------+");
            System.out.print("-->| ");

            String characterChoiceStr = scanner.nextLine();

            try {
                int characterChoice = Integer.parseInt(characterChoiceStr); 

                switch(characterChoice){
                    case 1:  
                        choiceSwordsman();
                        return new Swordsman();

                    case 2:
                        choiceGunner();
                        return new Gunner();

                    case 3: 
                        choiceMage();
                        return new Mage();

                    default:
                        System.out.println();
                        System.out.println("┌─────────────────────────────────────┐");
                        System.out.println("│  Oops! Invalid choice. Try again.   │");
                        System.out.println("└─────────────────────────────────────┘");
                }

            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────┐");
                System.out.println("│   Invalid input! Please enter a number.  │");
                System.out.println("└──────────────────────────────────────────┘");
            } catch (Exception e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────────┐");
                System.out.println("│   An unexpected error occurred. Try again.   │");
                System.out.println("└──────────────────────────────────────────────┘");
                scanner.nextLine(); 
            }
        }
    }
}

/*package Menu;

import Hero.*;
import Narration.*;

import java.util.Scanner;

public class CharacterMenu extends Narration{
    Scanner scanner = new Scanner(System.in);

    public Hero chooseCharacterMenu(){

        while(true){
            System.out.println("+------------------------------+");
            System.out.println("|      Choose a character      |");
            System.out.println("|------------------------------|");
            System.out.println("|  [1] Swordsman               |");
            System.out.println("|  [2] Gunner                  |");
            System.out.println("|  [3] Mage                    |");
            System.out.println("+------------------------------+");
            System.out.print("-->| ");

            String characterChoice = scanner.nextLine();

            switch(characterChoice){
                case "1":  
                    choiceSwordsman();
                    return new Swordsman();

                case "2":
                    choiceGunner();
                    return new Gunner();

                case "3": 
                    choiceMage();
                    return new Mage();

                default:
                    System.out.println();
                    System.out.println("┌─────────────────────────────────────┐");
                    System.out.println("│  Oops! Invalid choice. Try again.   │");
                    System.out.println("└─────────────────────────────────────┘");
            }
        }
    }
}
    */
