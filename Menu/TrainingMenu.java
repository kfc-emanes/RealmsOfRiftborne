package Menu;

import TrainingGround.Training;

import java.util.Scanner;

public class TrainingMenu {

    static Scanner scanner = new Scanner(System.in);
    boolean continueSparring = true;
    
    public void trainingMenu(){
        Training trainingHandler = new Training();
        boolean training = true;

        while(training){
            System.out.println();
            System.out.println("+------------------------------------------------+");
            System.out.println("|            Choose Your Training Discipline     |");
            System.out.println("+------------------------------------------------+");
            System.out.println("| 1. Endurance                                    |");
            System.out.println("|    Push your limits with stamina training and   |");
            System.out.println("|    long-distance challenges.                    |");
            System.out.println("| 2. Strength                                     |");
            System.out.println("|    Build raw power through intense physical     |");
            System.out.println("|    exercises and resistance training.           |");
            System.out.println("| 3. Durability                                   |");
            System.out.println("|    Fortify your body to withstand powerful      |");
            System.out.println("|    blows and environmental hazards.             |");
            System.out.println("| 4. Mana Refinement                              |");
            System.out.println("|    Enhance magical control and efficiency by    |");
            System.out.println("|    focusing on internal mana flow.              |");
            System.out.println("| 5. Exit Training                                |");
            System.out.println("+------------------------------------------------+");
            System.out.print(">>> ");

           String choice = scanner.nextLine();

           while(continueSparring){
                switch (choice) {
                    case "1":
                        System.out.println("Endurance Training"); // edit format

                        trainingHandler.generalTrainingPrompt();

                        continueSparring = false;
                        break;

                    case "2":
                        System.out.println("Strength Training"); // edit format

                        trainingHandler.generalTrainingPrompt();

                        continueSparring = false;
                        break;

                    case "3":
                        System.out.println("Durability"); // edit format

                        trainingHandler.generalTrainingPrompt();

                        continueSparring = false;
                        break;

                    case "4":
                        System.out.println("Mana Refinement"); // edit format

                        trainingHandler.generalTrainingPrompt();

                        continueSparring = false;
                        break;

                    default:
                        System.out.println();
                        System.out.println("┌───────────────────────────────┐");
                        System.out.println("│   Invalid input. Try again.   │");
                        System.out.println("└───────────────────────────────┘");
                }
            }
            
            training = false;
        }
    }
}
