package Menu;

import TrainingGround.Training;

public class AcademyMenu extends Menu{

    private boolean hasVisitedLibrary = false; // not used. was used in academy
    private boolean hasVisitedGym = false; // not used. was used in academy
    private boolean hasVisitedOffice = false; // not used. was used in academy

    public void academyMapMenu() {
        boolean academyMapMenu = true;
        Training trainingGroundHandler = new Training();

        while (academyMapMenu) {
            System.out.println("+-------------------------------------+");
            System.out.println("|  --- MYSTVALE ACADEMY MAP MENU ---  |");
            System.out.println("+-------------------------------------+");
            System.out.println("| [1] Library                         |");
            System.out.println("| [2] Gym                             |");
            System.out.println("| [3] Principal's Office              |");
            System.out.println("| [4] Exit Current Location           |");
            System.out.println("+-------------------------------------+");
            System.out.print(">>> ");

            String academyMapMenuChoice = scanner.nextLine();

            switch (academyMapMenuChoice) {
                case "1":

                    if(!hasVisitedLibrary){
                        libraryNarration();
                        hasVisitedLibrary = true;
                    }

                    System.out.println();
                    System.out.println("┌──────────────────────────────────────────────┐");
                    System.out.println("│        You are now inside the Library        │");
                    System.out.println("└──────────────────────────────────────────────┘");
                    System.out.println("┌──────────────────────────────────────────────┐");
                    System.out.println("│     Shh! Be mindful, others are studying     │");
                    System.out.println("└──────────────────────────────────────────────┘");
                    System.out.println();
                    break;
                 
                /*case "2":
                    if (!hasVisitedCanteen) {
                        canteenNarration();
                        hasVisitedCanteen = true;
                    } 

                    System.out.println();
                    System.out.println("┌──────────────────────────────────────────────┐");
                    System.out.println("│        You are now inside the Canteen        │");
                    System.out.println("└──────────────────────────────────────────────┘");
                    System.out.println("┌────────────────────────────┐");
                    System.out.println("│    Buy at your own risk    │");
                    System.out.println("└────────────────────────────┘");
                    System.out.println();

                    break;
                    */

                case "2":

                    System.out.println();
                    System.out.println("┌──────────────────────────────────────────────┐");
                    System.out.println("│    You are now inside the Training Ground    │");
                    System.out.println("└──────────────────────────────────────────────┘");
                    System.out.println("┌────────────────────────────────────────────┐");
                    System.out.println("│             Be ready to train              │");
                    System.out.println("└────────────────────────────────────────────┘");
                    System.out.println();

                    if (!hasVisitedGym) {
                        gymNarration();

                        System.out.println();
                        System.out.println("┌─────────────────────────────────────────────┐");
                        System.out.println("│ Do you want to explore the training ground? │");
                        System.out.println("│                   (y/n)                     │");
                        System.out.println("└─────────────────────────────────────────────┘");
                        System.out.print(">>> ");

                        String exploreInput = scanner.nextLine();

                        if (exploreInput.equals("y")) {
                            trainingGroundHandler.trainingGround();
                        } else {
                            break;
                        }

                        hasVisitedGym = true;
                    } 

                    if(hasVisitedGym){
                        System.out.println();
                        System.out.println("┌───────────────────────────────────────────────┐");
                        System.out.println("│      Do you want to stay for training and     │");
                        System.out.println("│                side quests? (y/n)             │");
                        System.out.println("└───────────────────────────────────────────────┘");
                        System.out.print(">>> ");

                         String wouldStay = scanner.nextLine();

                        if (wouldStay.equals("y")) {
                            trainingGroundHandler.trainingAndQuests();
                        } else {
                            System.out.println();
                            System.out.println("┌───────────────────────────────┐");
                            System.out.println("│    Exiting from the Gym...    │");
                            System.out.println("└───────────────────────────────┘");
                        }
                    }
                    
                    break;

                case "3":
                    
                    // add restrictions
                    
                    if (!hasVisitedOffice) {
                        principalOfficeNarration();
                        hasVisitedOffice = true;
                    } 

                    System.out.println();
                    System.out.println("┌──────────────────────────────────────────────┐");
                    System.out.println("│         You are now inside the Office        │");
                    System.out.println("└──────────────────────────────────────────────┘");
                    System.out.println("┌────────────────────────────────────────────┐");
                    System.out.println("│       May the odds be in your favor        │");
                    System.out.println("└────────────────────────────────────────────┘");
                    System.out.println();
                    break;

                case "4":
                    System.out.println();
                    System.out.println("┌───────────────────────────────────────────┐");
                    System.out.println("│    You have left your current location    │");
                    System.out.println("└───────────────────────────────────────────┘");

                    academyMapMenu = false;
                    
                    // Polish this part
                    //defaultMainMenu();
                    //academyMapMenu();
                    break;
                

                default:
                    System.out.println();
                    System.out.println("┌─────────────────────────────┐");
                    System.out.println("│  Invalid choice. Try again! │");
                    System.out.println("└─────────────────────────────┘");
            }
        }    
    }
}