package Area;
import BattleMechanics.BattleMechanic;
import Boss.*;
import Hero.*;
import java.util.*;

public class ForsakenLands {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    static Boss enemyBoss = new Azrael();

    public void enter(Hero hero) {
        System.out.println();
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│         You have entered the Forsaken Lands │");
        System.out.println("└────────────────────────────────────────────┘");
        System.out.println();

        exploreEntry(hero);
    }

    public void exploreEntry(Hero hero) {
        System.out.print("\nDo you wish to explore the Forsaken Lands? (y/n): ");
        char choice = scan.next().toLowerCase().charAt(0);
        System.out.println();

        if (choice == 'y') {
            System.out.println("You step into the Forsaken Lands, where silence and decay reign...");
            exploreOutsideArea(hero);
        } else if (choice == 'n') {
            System.out.println("You chose not to enter the Forsaken Lands.");
            exit();
        } else {
            System.out.println("Invalid choice. Please enter 'y' or 'n'.");
            System.out.println();
            exploreEntry(hero);
        }
    }

    public void exploreOutsideArea(Hero hero) {
        boolean valid = true;
        boolean explore = true;

        System.out.println();
        if (explore) {
            if (rand.nextBoolean()) {
                System.out.println("The air turns cold as hollow wails echo in the distance...");
                System.out.println("A tormented spirit manifests from the ground and attacks!");
            } else {
                System.out.println("You wander among ruined statues and cracked earth. Nothing stirs — only the wind mourns.");
            }
        }
        System.out.println();

        while (valid) {
            System.out.print("Do you want to continue exploring? (y/n): ");
            char choice = scan.next().toLowerCase().charAt(0);

            if (choice == 'y') {
                exploreMiddleArea(hero);
                valid = false;
            } else if (choice == 'n') {
                System.out.println("\nYou chose to return to the edge of the Forsaken Lands.");
                valid = false;
                exploreEntry(hero);
            } else {
                System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                System.out.println();
            }
        }
    }

    public void exploreMiddleArea(Hero hero) {
        boolean valid = true;
        boolean explore = true;

        System.out.println();
        if (explore) {
            if (rand.nextBoolean()) {
                System.out.println("Shadows twist around the ruins. The ground trembles under your feet...");
                System.out.println("A corrupted revenant claws its way from the ashes to confront you!");
            } else {
                System.out.println("You move deeper into the wasteland. The sky above is gray, and even time seems to fade.");
            }
        }
        System.out.println();

        while (valid) {
            System.out.print("Do you want to continue exploring? (y/n): ");
            char choice = scan.next().toLowerCase().charAt(0);

            if (choice == 'y') {
                exploreInnerArea(hero);
                valid = false;
            } else if (choice == 'n') {
                System.out.println("\nYou turn back and make your way to the previous area.");
                valid = false;
                exploreOutsideArea(hero);
            } else {
                System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                System.out.println();
            }
        }
    }

    public void exploreInnerArea(Hero hero) {
        boolean valid = true;
        boolean explore = true;
        BattleMechanic battle = new BattleMechanic();

        System.out.println();
        if (explore) {
            if (rand.nextBoolean()) {
                System.out.println("You reach the heart of the Forsaken Lands, where light no longer exists...");
                System.out.println("\nSuddenly, a chilling presence descends upon you — an ELITE entity approaches!");
            } else {
                System.out.println("The ground cracks beneath your feet. The air hums with dreadful energy — something is watching you.");
            }
        }
        System.out.println();

        String[] azrael = {
            "\nThe silence becomes unbearable.",
            "A gust of black feathers swirls in front of you, and the air turns heavy with dread.",
            "A figure descends slowly from the sky — wings torn, eyes glowing faint blue.",
            "Each step it takes radiates despair and power.",
            "Before you stands the Fallen Seraph of Despair — Azrael!"
        };

        playSection(azrael);

        while (valid) {
            System.out.print("Do you want to face the Fallen Seraph, Azrael? (y/n): ");
            char choice = scan.next().toLowerCase().charAt(0);

            if (choice == 'y') {
                System.out.println();
                System.out.println("┌────────────────────┐");
                System.out.println("│       BATTLE       │");
                System.out.println("└────────────────────┘");
                battle.fight(hero, enemyBoss);
                valid = false;
            } else if (choice == 'n') {
                System.out.println("\nYou retreat from Azrael’s overwhelming aura and head back to the previous area.");
                valid = false;
                exploreMiddleArea(hero);
            } else {
                System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                System.out.println();
            }
        }
    }

    public static void playSection(String[] section) {
        System.out.println("Press ENTER to continue...");
        scan.nextLine(); 
        for (String line : section) {
            scan.nextLine();
            System.out.println(line);
        }
        System.out.println();
    }

    public void exit() {
        System.out.println();
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│           <<< Location Exited >>>          │");
        System.out.println("│     You have left the Forsaken Lands.      │");
        System.out.println("└────────────────────────────────────────────┘");
        System.out.println();
    }
}
