package Library;

import Hero.*;
import Narration.LibraryNarration;

import java.util.Scanner;
import java.util.Random;

public class LibraryQuest extends LibraryNarration{

    private static Scanner scanner = new Scanner(System.in);
    Random random = new Random(System.nanoTime());
    private static int whichShelf = new Random().nextInt(5) + 1;
    private StatsProgressLibrary progressHandler = new StatsProgressLibrary();

    public void findTheLostBook(Hero hero) {
        System.out.println();
        System.out.println("┌────────────────────────────┐");
        System.out.println("│   + Find The Lost Book +   │");
        System.out.println("└────────────────────────────┘");

        boolean found = false;

        while (!found) {
            try {
                System.out.println("+-------------------------------------+");
                System.out.println("|   The lost book could be here...    |");
                System.out.println("+-------------------------------------+");
                System.out.println("| [1] Shelf Fiction                   |");
                System.out.println("| [2] Shelf Science                   |");
                System.out.println("| [3] Shelf Mythology                 |");
                System.out.println("| [4] Shelf Magic and Spells          |");
                System.out.println("| [5] Shelf History                   |");
                System.out.println("+-------------------------------------+");
                System.out.print("-->| ");

                int shelfChoice = Integer.parseInt(scanner.nextLine());

                if (shelfChoice < 1 || shelfChoice > 5) {
                    System.out.println();
                    System.out.println("┌───────────────────────────────────────────────────────────┐");
                    System.out.println("│   Hmmm...That's not valid. Choose a number between 1-5   │");
                    System.out.println("└───────────────────────────────────────────────────────────┘");
                } else if (shelfChoice == whichShelf) {
                    System.out.println();
                    System.out.println("┌───────────────────────────────────────────────────────┐");
                    System.out.println("│   You've uncovered the lost book. Well done, hero!    │");
                    System.out.println("└───────────────────────────────────────────────────────┘");

                    ifQuest1Success();
                    progressHandler.randomStatsProgress(hero);
                    found = true;
                } else {
                    System.out.println();
                    System.out.println("┌──────────────────────────────────────────────────────┐");
                    System.out.println("│   No luck here, brave seeker. Continue your quest.   │");
                    System.out.println("└──────────────────────────────────────────────────────┘");
                }
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────────┐");
                System.out.println("│   Invalid input! Please enter a number 1-5.  │");
                System.out.println("└──────────────────────────────────────────────┘");
            } catch (Exception e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────────┐");
                System.out.println("│   An unexpected error occurred. Try again.   │");
                System.out.println("└──────────────────────────────────────────────┘");
                scanner.nextLine();
            }
        }
    }

    public void riddles(Hero hero){

        System.out.println();
        System.out.println("┌────────────────────────────┐");
        System.out.println("│   + Decode The Riddles +   │");
        System.out.println("└────────────────────────────┘");

        if(hero.isRiddle1Done() && hero.isRiddle2Done() && hero.isRiddle3Done()) {
            System.out.println("┌───────────────────────────────────────────────────┐");
            System.out.println("│   + Victory! All riddles have been conquered! +   │");
            System.out.println("└───────────────────────────────────────────────────┘");

            return;
        }

        int whatRiddle;
        boolean isCorrect = false;

        do {
            whatRiddle = random.nextInt(3) + 1;
        } while ((whatRiddle == 1 && hero.isRiddle1Done()) || (whatRiddle == 2 && hero.isRiddle2Done()) || (whatRiddle == 3 && hero.isRiddle3Done()));
 
        switch(whatRiddle){
            case 1:
                isCorrect = riddle1();
                
                if(isCorrect){
                    hero.setRiddle1Done(true);
                }
                break;

            case 2: 
                isCorrect = riddle2();
                
                if(isCorrect){
                    hero.setRiddle2Done(true);
                }

                break;

            case 3:
                isCorrect = riddle3();
                
                if(isCorrect) {
                    hero.setRiddle3Done(true);
                }
                break;
        }

        if(isCorrect){
            progressHandler.randomStatsProgress(hero);
        }
        
    }
    
}