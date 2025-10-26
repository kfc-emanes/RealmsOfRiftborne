package Menu;

import Hero.*;
import Library.LibraryAcademy;
import TrainingGround.*;
import Office.*;

public class AcademyMenu extends Menu{

    public void academyMapMenu(Hero hero) {
        boolean academyMapMenu = true;
        Training trainingGroundHandler = new Training();
        LibraryAcademy libraryHandler = new LibraryAcademy();
        PrincipalOffice officeHandler = new PrincipalOffice();

        while (academyMapMenu) {
            System.out.println();
            System.out.println("+-------------------------------------+");
            System.out.println("|  --- MYSTVALE ACADEMY MAP MENU ---  |");
            System.out.println("+-------------------------------------+");
            System.out.println("| [1] Library                         |");
            System.out.println("| [2] Training Ground                 |");
            System.out.println("| [3] Principal's Office              |");
            System.out.println("| [4] Exit Academy                    |");
            System.out.println("+-------------------------------------+");
            System.out.print("-->| ");

            String academyMapMenuChoiceStr = scanner.nextLine();

            try {
                int academyMapMenuChoice = Integer.parseInt(academyMapMenuChoiceStr);

                switch (academyMapMenuChoice) {
                    case 1:

                        if(!hero.hasVisitedLibrary()){
                            libraryNarration();
                            hero.setVisitedLibrary(true);
                        }

                        System.out.println();
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - -  - - - - <<<<<");
                        System.out.println("      ┌───────────────────────────────────────┐");
                        System.out.println("      │   + You are now inside the Library +  │");
                        System.out.println("      └───────────────────────────────────────┘");
                        System.out.println("     ┌──────────────────────────────────────────┐");
                        System.out.println("     │   Shh! Be mindful, others are studying   │");
                        System.out.println("     └──────────────────────────────────────────┘");
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - <<<<<");

                        libraryHandler.libraryAcademy(hero);

                        break;

                    case 2:
                        System.out.println();
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");
                        System.out.println("        ┌────────────────────────────────────────────────┐");
                        System.out.println("        │   + You are now inside the Training Ground +   │");
                        System.out.println("        └────────────────────────────────────────────────┘");
                        System.out.println("     ┌───────────────────────────────────────────────────────┐");
                        System.out.println("     │   The air grows tense. It's time to prove your skill  │");
                        System.out.println("     └───────────────────────────────────────────────────────┘");
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");

                        if (!hero.hasVisitedGym()) {
                            gymNarration();
                            hero.setVisitedGym(true);
                        }

                        boolean validInput = false;

                        while (!validInput) {
                            try {
                                System.out.println();
                                System.out.println("┌──────────────────────────────────────────────┐");
                                System.out.println("│   Explore Mystvale's training ground? (y/n)  │");
                                System.out.println("└──────────────────────────────────────────────┘");
                                System.out.print(">>> ");
                                String exploreInput = scanner.nextLine();

                                if (exploreInput.equalsIgnoreCase("y")) {
                                    trainingGroundHandler.trainingGround(hero);
                                    validInput = true;
                                } else if (exploreInput.equalsIgnoreCase("n")) {
                                    System.out.println("┌──────────────────────────────────────────────┐");
                                    System.out.println("│   >>> Exiting from the Training Ground <<<   │");
                                    System.out.println("└──────────────────────────────────────────────┘");
                                    return;
                                } else {
                                    System.out.println();
                                    System.out.println("┌────────────────────────────────────────┐");
                                    System.out.println("│   Choice unclear! Enter 'y' or 'n'.    │");
                                    System.out.println("└────────────────────────────────────────┘");
                                }
                            } catch (Exception e) {
                                System.out.println();
                                System.out.println("┌──────────────────────────────────────────────┐");
                                System.out.println("│   Error occurred during input. Try again.    │");
                                System.out.println("└──────────────────────────────────────────────┘");
                                scanner.nextLine(); 
                            }
                        }

                        break;


                    case 3:

                        if (!hero.hasVisitedOffice()) {
                            principalOfficeNarration();
                            hero.setVisitedOffice(true);
                        }

                        System.out.println();
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - <<<<<");
                        System.out.println("     ┌───────────────────────────────────────┐");
                        System.out.println("     │   + You are now inside the Office +   │");
                        System.out.println("     └───────────────────────────────────────┘");
                        System.out.println("       ┌───────────────────────────────────┐");
                        System.out.println("       │   May the odds be in your favor   │");
                        System.out.println("       └───────────────────────────────────┘");
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - <<<<<");
                        System.out.println();

                        officeHandler.principalOffice(hero);

                        break;

                    case 4:
                        System.out.println();
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");
                        System.out.println("           ┌─────────────────────────────────────────────────┐");
                        System.out.println("           │   >>> Exiting from the Academy's premises <<<   │");
                        System.out.println("           └─────────────────────────────────────────────────┘");
                        System.out.println("     ┌──────────────────────────────────────────────────────────────┐");
                        System.out.println("     │   The hum of magic fades as you leave the academy\'s walls    │");
                        System.out.println("     └──────────────────────────────────────────────────────────────┘");
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");

                        academyMapMenu = false;

                        break;

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