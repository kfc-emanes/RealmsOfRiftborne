package TrainingGround;

import Menu.TrainingMenu;
import Menu.AcademyMenu;

import java.util.Scanner;
import java.util.Random;

public class Training {

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    public void trainingGround(){
        TrainingNarration narrationHandler = new TrainingNarration();
        TrainingMenu trainingMenuHandler = new TrainingMenu();

        narrationHandler.exploreNarration();

        System.out.println();
        System.out.println("┌───────────────────────────────────────────┐");
        System.out.println("│        You are required to train.         │");
        System.out.println("│        Press enter to continue...         │");
        System.out.println("└───────────────────────────────────────────┘");
        scanner.nextLine();

        //narrationHandler.trainingInstructions(); 
        trainingMenuHandler.trainingMenu();

    }

    public void trainingAndQuests(){
        AcademyMenu academyMenuHandler = new AcademyMenu();
        TrainingMenu trainingMenuHandler = new TrainingMenu();

        boolean continueGeneralPrompt = true;
        int trainOrQuest = random.nextInt();
        int trainingTryOuts = 3;

        while(continueGeneralPrompt){

            if(trainOrQuest % 2 == 0){
                System.out.println();
                System.out.println("┌──────────────────────────────────────────┐");
                System.out.println("│    You have received a training call.    │");
                System.out.println("│    Would you like to accept it? (y/n)    │");
                System.out.println("└──────────────────────────────────────────┘");
                System.out.print(">>> ");

                String acceptTraining = scanner.nextLine();

                if(acceptTraining.equals("y")){
                    if(trainingTryOuts > 0){
                        trainingMenuHandler.trainingMenu();
                        trainingTryOuts--;
                        continueGeneralPrompt = false;
                    } else {
                        System.out.println();
                        System.out.println("┌──────────────────────────────────────────────┐");
                        System.out.println("│        You are out of training tryout!       │");
                        System.out.println("└──────────────────────────────────────────────┘");
                        return;
                    }
                } else if(acceptTraining.equals("n")){
                    System.out.println();
                    System.out.println("┌────────────────────────────────────────────────┐");
                    System.out.println("│     Exiting from the Training Ground...        │");
                    System.out.println("└────────────────────────────────────────────────┘");

                    academyMenuHandler.academyMapMenu();
                    continueGeneralPrompt = false;
                }

            } else if(trainOrQuest % 2 != 0){
                // call fuction for quest

                System.out.println();
                System.out.println("┌──────────────────────────────────────────────┐");
                System.out.println("│                Quests are empty.             │");
                System.out.println("│                Come back later.              │");
                System.out.println("└──────────────────────────────────────────────┘");
                return;
            }

        }

    }

    public void generalTrainingPrompt(){

        System.out.println();
        System.out.println("┌──────────────────────────────────────────────┐");
        System.out.println("│             Training is on-going...          │");
        System.out.println("│            Press enter to continue...        │");
        System.out.println("└──────────────────────────────────────────────┘");

        String[] timeDelay = {
            ">>> Battle is on going... Please wait... <<<", 
            ">>> Battle is on going... Please wait... <<<", 
            ">>> Battle is on going... Please wait... <<<", 
            ">>> Battle is on going... Please wait... <<<", 
            ">>> Battle is on going... Please wait... <<<"
        };

        for(int i = 0; i < timeDelay.length; i++){
            scanner.nextLine();
            System.out.println(timeDelay[i]);
        }

        sparringMechanism();
        return;

    }

    public void sparringMechanism(){
        int chanceofWin = random.nextInt();

        if(chanceofWin % 2 != 0){
            System.out.println();
            System.out.println("┌───────────────────────────────┐");
            System.out.println("│     Success! You did well!    │");
            System.out.println("└───────────────────────────────┘");
            System.out.println("┌────────────────────────────────────────────────┐");
            System.out.println("│     Exiting from the Training Ground...        │");
            System.out.println("└────────────────────────────────────────────────┘");

        } else if(chanceofWin % 2 == 0){
            System.out.println();
            System.out.println("┌───────────────────────────────────┐");
            System.out.println("│    Defeated. You still did well   │");
            System.out.println("└───────────────────────────────────┘");
            System.out.println("┌────────────────────────────────────────────────┐");
            System.out.println("│     Exiting from the Training Ground...        │");
            System.out.println("└────────────────────────────────────────────────┘");

        }
    }

}
