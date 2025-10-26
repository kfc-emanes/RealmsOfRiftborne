package Menu;

import TrainingGround.*;
import Hero.*;

import java.util.Scanner;

public class TrainingMenu {

    static Scanner scanner = new Scanner(System.in);
    
    private Training trainingHandler;

    public TrainingMenu(Training trainingHandler) {
        this.trainingHandler = trainingHandler;
    }
    
    public void trainingMenu(Hero hero) {
        boolean training = true; 

        while (training) {
            System.out.println();
            System.out.println("+-------------------------------------------------+");
            System.out.println("|         Choose Your Training Discipline         |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [1] Endurance                                   |");
            System.out.println("|    Push your limits with stamina training and   |");
            System.out.println("|    long-distance challenges.                    |");
            System.out.println("| [2] Strength                                    |");
            System.out.println("|    Build raw power through intense physical     |");
            System.out.println("|    exercises and resistance training.           |");
            System.out.println("| [3] Durability                                  |");
            System.out.println("|    Fortify your body to withstand powerful      |");
            System.out.println("|    blows and environmental hazards.             |");
            System.out.println("| [4] Mana Refinement                             |");
            System.out.println("|    Enhance magical control and efficiency by    |");
            System.out.println("|    focusing on internal mana flow.              |");
            System.out.println("+------------------------------------------------+");
            System.out.print("-->| ");

            try {
                // Convert input to integer
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println();
                        System.out.println("┌────────────────────────────┐");
                        System.out.println("│   + Endurance Training +   │");
                        System.out.println("└────────────────────────────┘");

                        if (hero.hasFinishedEndurance()) {
                            System.out.println();
                            System.out.println("┌─────────────────────────────────────────────┐");
                            System.out.println("│     You have already mastered Endurance     │");
                            System.out.println("│     Please choose another training type     │");
                            System.out.println("└─────────────────────────────────────────────┘");
                        } else {
                            trainingHandler.generalTrainingPrompt(hero, "endurance");
                            training = false;
                        }
                        break;

                    case 2:
                        System.out.println();
                        System.out.println("┌───────────────────────────┐");
                        System.out.println("│   + Strength Training +   │");
                        System.out.println("└───────────────────────────┘");

                        if (hero.hasFinishedStrength()) {
                            System.out.println();
                            System.out.println("┌─────────────────────────────────────────────┐");
                            System.out.println("│     You have already mastered Strength!     │");
                            System.out.println("│     Please choose another training type     │");
                            System.out.println("└─────────────────────────────────────────────┘");
                        } else {
                            trainingHandler.generalTrainingPrompt(hero, "strength");
                            training = false;
                        }
                        break;

                    case 3:
                        System.out.println();
                        System.out.println("┌───────────────────────────────┐");
                        System.out.println("│    + Durability Training +    │");
                        System.out.println("└───────────────────────────────┘");

                        if (hero.hasFinishedDurability()) {
                            System.out.println();
                            System.out.println("┌─────────────────────────────────────────────┐");
                            System.out.println("│     You have already mastered Durability    │");
                            System.out.println("│     Please choose another training type     │");
                            System.out.println("└─────────────────────────────────────────────┘");
                        } else {
                            trainingHandler.generalTrainingPrompt(hero, "durability");
                            training = false;
                        }
                        break;

                    case 4:
                        System.out.println();
                        System.out.println("┌──────────────────────────────────┐");
                        System.out.println("│   + Mana Refinement Training +   │");
                        System.out.println("└──────────────────────────────────┘");

                        if (hero.hasFinishedManaRefinement()) {
                            System.out.println();
                            System.out.println("┌─────────────────────────────────────────────┐");
                            System.out.println("│  You have already mastered Mana Refinement! │");
                            System.out.println("│     Please choose another training type     │");
                            System.out.println("└─────────────────────────────────────────────┘");
                        } else {
                            trainingHandler.generalTrainingPrompt(hero, "mana refinement");
                            training = false;
                        }
                        break;

                    default:
                        System.out.println();
                        System.out.println("┌─────────────────────────────────────┐");
                        System.out.println("│   Oops! Invalid input. Try again.   │");
                        System.out.println("└─────────────────────────────────────┘");
                        continue;
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
