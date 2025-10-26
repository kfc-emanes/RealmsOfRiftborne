package Office;

import Hero.*;
import Narration.*;
import TrainingGround.StatProgress;

public class PrincipalOffice extends StatProgress {

    private OfficeNarration narrationHandler = new OfficeNarration();

    public void principalOffice(Hero hero){
        
        if(!hero.haveEntered()){
            narrationHandler.officeNarration(); 
            hero.setHaveEntered(true);
        }

        System.out.println();
        System.out.println("┌──────────────────────────────────────────────┐");
        System.out.println("│   Determining your elegibility. Standby~~~   │");
        System.out.println("└──────────────────────────────────────────────┘");


        boolean eligible = false;

        if (!hero.canEnterArea1()) {
            if(hero.hasFinishedAllTraining()) {
                narrationHandler.area1Eligible();
                hero.unlockArea1();

                System.out.println();
                System.out.println("┌──────────────────────────────────────────────────┐");
                System.out.println("│   + You may now enter The Forest of Reverie +    │");
                System.out.println("└──────────────────────────────────────────────────┘");

                currencyProgress(hero);

                eligible = true;
            }

            

        } else if (!hero.canEnterArea2() && hero.canEnterArea1()) {
            if(hero.getHaveDefeatedArea1Boss()) {

                narrationHandler.area2Eligible();
                hero.unlockArea2();

                System.out.println();
                System.out.println("┌───────────────────────────────────────────┐");
                System.out.println("│   + You may now enter The Reverie Edge +  │");
                System.out.println("└───────────────────────────────────────────┘");

                currencyProgress(hero);

                eligible = true;
            }
            

        } else if (!hero.canEnterArea3() && hero.canEnterArea2()) {
            if(hero.getHaveDefeatedArea2Boss()) {
                narrationHandler.area3Eligible();
                hero.unlockArea3();
            
                System.out.println();
                System.out.println("┌───────────────────────────────────────────────┐");
                System.out.println("│   + You may now enter The Forsaken Lands +    │");
                System.out.println("└───────────────────────────────────────────────┘");

                currencyProgress(hero);

                eligible = true;
            }
            
        }

        if (!eligible) {
            System.out.println();
            System.out.println("┌───────────────────────────────────────────────────┐");
            System.out.println("│   You are not eligible for any outside premises   │");
            System.out.println("│     Gain more experience to claim eligibility     │");
            System.out.println("└───────────────────────────────────────────────────┘");
            System.out.println("┌───────────────────────────────────────────────────┐");
            System.out.println("│   >>> Exiting from the Principal\'s Office <<<    │");
            System.out.println("└───────────────────────────────────────────────────┘");

        }
    }

}
