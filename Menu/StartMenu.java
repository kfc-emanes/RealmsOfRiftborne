package Menu;

import Narration.Narration;
import java.util.Scanner;

public class StartMenu extends Narration { 
    
    static Scanner scan = new Scanner(System.in);
    static boolean hasVisitedPrologue = false;

    public boolean displayStartMenu(){
        //CharacterMenu charHandler = new CharacterMenu();

        //boolean play = true;

        while(true){
            System.out.println("+------------------------------+");
            System.out.println("|        Mystvale Academy      |");
            System.out.println("+------------------------------+");
            System.out.println("|    A Text-Based Adventure    |");
            System.out.println("|  in a World of Sorcery and   |");
            System.out.println("|         Shadowed Halls       |");
            System.out.println("+------------------------------+");
            System.out.println("|          1. Start Game       |");
            System.out.println("|          2. Exit Game        |");
            System.out.println("+------------------------------+");
            System.out.print(">>> ");

            String startMenuChoice = scan.nextLine();

            switch (startMenuChoice) {
                case "1":
                    System.out.println();
                    System.out.println("┌─────────────────────────┐");
                    System.out.println("│   Starting the game...  │");
                    System.out.println("└─────────────────────────┘");

                    if (!hasVisitedPrologue) {
                        prologueNarration();
                        hasVisitedPrologue = true;
                    }
                    
                    System.out.println();
                    System.out.println("┌──────────────────────────────────────────────┐");
                    System.out.println("│          Choose a character to play          │");
                    System.out.println("└──────────────────────────────────────────────┘");
                    
                    //charHandler.chooseCharacterMenu();
                    //menuHandler.defaultMainMenu();

                    return true;

                case "2":
                    System.out.println();
                    System.out.println("┌───────────┐");
                    System.out.println("│  Goodbye! │");
                    System.out.println("└───────────┘");
                    return false;

                default:
                    System.out.println();
                    System.out.println("┌─────────────────────────────┐");
                    System.out.println("│  Invalid choice. Try again! │");
                    System.out.println("└─────────────────────────────┘");
                    break;
            }
                
        }
    }
}
