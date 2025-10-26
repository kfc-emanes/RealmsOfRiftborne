package Menu;

import Narration.*;

import java.util.Scanner;

public class StartMenu extends Narration { 
    
    static Scanner scan = new Scanner(System.in);
    static boolean hasVisitedPrologue = false;

    public boolean displayStartMenu() {

        while (true) {
            System.out.println("+-----------------------------------------+");
            System.out.println("|             Mystvale Academy            |");
            System.out.println("+-----------------------------------------+");
            System.out.println("|         A Text-Based Adventure          |");
            System.out.println("|        in a World of Sorcery and        |");
            System.out.println("|            Shadowed Halls               |");
            System.out.println("+-----------------------------------------+");
            System.out.println("|             [1] Start Game              |");
            System.out.println("|             [2] Exit Game               |");
            System.out.println("+-----------------------------------------+");
            System.out.print("-->| ");

            try {
                int startMenuChoice = Integer.parseInt(scan.nextLine());

                switch (startMenuChoice) {
                    case 1:
                        System.out.println();
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - <<<<<");
                        System.out.println("     ┌───────────────────────────────┐");
                        System.out.println("     │   >>> Starting the game <<<   │");
                        System.out.println("     └───────────────────────────────┘");
                        System.out.println("     ┌────────────────────────────────┐");
                        System.out.println("     │   The wind whisper your name   │");
                        System.out.println("     │       Your destiny awaits      │");
                        System.out.println("     └────────────────────────────────┘");
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - <<<<<");

                        if (!hasVisitedPrologue) {
                            prologueNarration();
                            hasVisitedPrologue = true;
                        }

                        System.out.println();
                        System.out.println("┌────────────────────────────────────┐");
                        System.out.println("│   + Choose a character to play +   │");
                        System.out.println("└────────────────────────────────────┘");

                        return true;

                    case 2:
                        System.out.println();
                        System.out.println(">>>>> - - - - - - - - - - - - - - - <<<<<");
                        System.out.println("    ┌──────────────────────────────┐");
                        System.out.println("    │   >>> Exiting the game <<<   │");
                        System.out.println("    └──────────────────────────────┘");
                        System.out.println("┌──────────────────────────────────────┐");
                        System.out.println("│       You vanish into the mist       │");
                        System.out.println("│   The academy goes quiet once more   │");
                        System.out.println("└──────────────────────────────────────┘");
                        System.out.println(">>>>> - - - - - - - - - - - - - - - <<<<<");
                        return false;

                    default:
                        System.out.println();
                        System.out.println("┌─────────────────────────────────────┐");
                        System.out.println("│   Oops! Invalid choice. Try again.  │");
                        System.out.println("└─────────────────────────────────────┘");
                        break;
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
                scan.nextLine(); 
            }
        }
    }
}

