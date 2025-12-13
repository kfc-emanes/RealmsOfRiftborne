package DesignRelated;

import Hero.*;
import java.util.Scanner;

public class Stats {
    Scanner scanner = new Scanner(System.in);
    public void sackOfGoldAfterTraining(Hero hero, int gold, int xp) {

        int totalGold = hero.getGold() + gold;
        hero.setGold(totalGold);
        
       hero.setGold(gold);
        System.out.println("░░░░░█    ░░░░░░  ░░      ░█    ░░░░    ░░░░░█    ░░░░░█      ░░░░░█");
        System.out.println("░░    ░░  ░█      ░░  ░░  ░█  ░░    █░  ░░    ░░  ░░    ░░  ░█");
        System.out.println("░░░░░░    ░░░░    ░█  ░░  ░░  ░░░░░░░░  ░░░░░░    ░░    ░░    ░░░░");
        System.out.println("░░    ░█  ░█      ░█  ░░  ░░  ░█    ░░  ░█    ░░  ░░    ░█        ░░");
        System.out.println("░░    ░█  ░░░░░░    ░░  ░░    ░░    ░░  ░█    ░░  ░░░░░█    ░░░░░█") ;
        System.out.println();
        System.out.println("          ┌────────────────────────────────────────┐");
        System.out.println("          │        Here's a chest with gold        |");
        System.out.println("          │  May it help you conquer your battles  │");
        System.out.println("          └────────────────────────────────────────┘");
        System.out.printf("           >>> %-15s : +%6d%n", "Gold Earned", gold);
        System.out.printf("           >>> %-15s : %6d%n", "Total Gold", totalGold);
        System.out.printf("           >>> %-15s : +%6d%n", "Experience Earned", xp);
        System.out.println("                                                                   ");
        System.out.println("                            ░░░░░░░░  ");
        System.out.println("                        ░░░░▓▓▓▓▓▓▒▒░░░░");
        System.out.println("                    ░░░░▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓░░░░");
        System.out.println("                ░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▓▓░░");
        System.out.println("            ░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▓▓░░");
        System.out.println("        ░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓░░");
        System.out.println("    ░░░░▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓░░");
        System.out.println("  ░░▓▓▓▓▓▓▓▓▓▓▓▓▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▓▓▒▒▒▒▒▒▓▓▓▓▓▓▓▓▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒░░");
        System.out.println("░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓▓▓░░");
        System.out.println("░░▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒▒▒▒▒░░░░▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒░░░██░░░▓▓▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▓▓▓▓▒▒▒▒▓▓▓▓▒▒▒▒▓▓▓▓▒▒▓▓▓▓▓▓░░░██░░░▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▓▓▓▓▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒░░░░▒▒▒▒▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▓▓▒▒▓▓▓▓▒▒▒▒▓▓▓▓▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▓▓▓▓▒▒▓▓▓▓▓▓▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓░░░░");
        System.out.println("  ░░▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒▒▒▒▒▓▓▓▓░░░░");
        System.out.println("    ░░░░▓▓▓▓▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒▓▓▓▓░░░░");
        System.out.println("        ░░░░▓▓▓▓▒▒▒▒▓▓▓▓▒▒▓▓▓▓▓▓░░░░");
        System.out.println("            ░░░░▓▓▓▓▓▓▓▓▓▓▓▓░░░░");
        System.out.println("                ░░░░▓▓▓▓░░░░");
        System.out.println("                    ░░░░ | Press ENTER to continue... |");
        scanner.nextLine();
        hero.levelUp(xp);
        

    }
    

    public void rewards(Hero hero, int gold, int xp) {

        int totalGold = hero.getGold() + gold;
        hero.setGold(totalGold);
       
        System.out.println("░░░░░█    ░░░░░░  ░░      ░█    ░░░░    ░░░░░█    ░░░░░█      ░░░░░█");
        System.out.println("░░    ░░  ░█      ░░  ░░  ░█  ░░    █░  ░░    ░░  ░░    ░░  ░█");
        System.out.println("░░░░░░    ░░░░    ░█  ░░  ░░  ░░░░░░░░  ░░░░░░    ░░    ░░    ░░░░");
        System.out.println("░░    ░█  ░█      ░█  ░░  ░░  ░█    ░░  ░█    ░░  ░░    ░█        ░░");
        System.out.println("░░    ░█  ░░░░░░    ░░  ░░    ░░    ░░  ░█    ░░  ░░░░░█    ░░░░░█") ;
        System.out.println();
        System.out.println("          ┌────────────────────────────────────────┐");
        System.out.println("          │        Here's a chest with gold        |");
        System.out.println("          │  May it help you conquer your battles  │");
        System.out.println("          └────────────────────────────────────────┘");
        System.out.printf("           >>> %-15s : +%6d%n", "Gold Earned", gold);
        System.out.printf("           >>> %-15s : %6d%n", "Total Gold", totalGold);
        System.out.printf("           >>> %-15s : +%6d%n", "Experience Earned", xp);
        System.out.println("                                                                   ");
        System.out.println("                            ░░░░░░░░  ");
        System.out.println("                        ░░░░▓▓▓▓▓▓▒▒░░░░");
        System.out.println("                    ░░░░▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓░░░░");
        System.out.println("                ░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▓▓░░");
        System.out.println("            ░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▓▓░░");
        System.out.println("        ░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓░░");
        System.out.println("    ░░░░▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓░░");
        System.out.println("  ░░▓▓▓▓▓▓▓▓▓▓▓▓▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▓▓▒▒▒▒▒▒▓▓▓▓▓▓▓▓▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒░░");
        System.out.println("░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓▓▓░░");
        System.out.println("░░▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒▒▒▒▒░░░░▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒░░░██░░░▓▓▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▓▓▓▓▒▒▒▒▓▓▓▓▒▒▒▒▓▓▓▓▒▒▓▓▓▓▓▓░░░██░░░▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▓▓▓▓▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒░░░░▒▒▒▒▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▓▓▒▒▓▓▓▓▒▒▒▒▓▓▓▓▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▓▓▓▓▒▒▓▓▓▓▓▓▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓░░░░");
        System.out.println("  ░░▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒▒▒▒▒▓▓▓▓░░░░");
        System.out.println("    ░░░░▓▓▓▓▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒▓▓▓▓░░░░");
        System.out.println("        ░░░░▓▓▓▓▒▒▒▒▓▓▓▓▒▒▓▓▓▓▓▓░░░░");
        System.out.println("            ░░░░▓▓▓▓▓▓▓▓▓▓▓▓░░░░");
        System.out.println("                ░░░░▓▓▓▓░░░░");
        System.out.println("                    ░░░░");
        
        scanner.nextLine();

        hero.levelUp(xp);

    }

}
