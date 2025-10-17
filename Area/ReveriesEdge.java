package Area;
import BattleMechanics.BattleMechanic;
import Boss.*;
import Hero.*;
import java.util.*;

public class ReveriesEdge {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    static Boss enemyBoss = new Morgrath();

    public void enter(Hero hero) {
        System.out.println();
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│       You have entered the Reverie's Edge  │");
        System.out.println("└────────────────────────────────────────────┘");
        System.out.println();

        exploreEntry(hero);
    }

    public void exploreEntry(Hero hero) {
        System.out.print("\nDo you want to explore the Reverie's Edge? (y/n): ");
        char choice = scan.next().toLowerCase().charAt(0);
        System.out.println();

        if (choice == 'y') {
            System.out.println("Venturing cautiously into the dark borderlands of Reverie's Edge...");
            exploreOutsideArea(hero);
        } else if (choice == 'n') {
            System.out.println("You chose not to explore the Reverie's Edge.");
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
                System.out.println("As you wander near the edge of the forest, strange whispers echo in the mist...");
                System.out.println("A dark creature emerges from the shadows to attack!");
            } else {
                System.out.println("You tread carefully through the dim forest border. The air feels heavy, but calm... for now.");
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
                System.out.println("\nYou chose to head back and return to the edge entrance.");
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
                System.out.println("The deeper you go, the less life you see. Faint growls surround you...");
                System.out.println("A corrupted beast leaps from the dark brush to strike!");
            } else {
                System.out.println("The path grows darker and colder. The trees twist unnaturally, and silence fills the air.");
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
                System.out.println("\nYou chose to head back and return to the previous area.");
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
                System.out.println("You enter the heart of Reverie's Edge, where light barely pierces the canopy...");
                System.out.println("\nSuddenly, a powerful ELITE-type creature emerges from the darkness!");
            } else {
                System.out.println("The air here is suffocating. You feel eyes watching from the shadows — the calm before something dreadful.");
            }
        }
        System.out.println();

        String[] morgrath = {
            "\nThe atmosphere grows unnaturally still...",
            "A black mist begins to swirl around you, distorting your senses.",
            "Out from the veil of shadows steps a towering figure draped in torn robes.",
            "The creature's eyes burn crimson — a remnant of pure malice.",
            "It's the Dread Warden of the Edge — Morgrath!"
        };

        playSection(morgrath);

        while (valid) {
            System.out.print("Do you want to fight the Dread Warden, Morgrath? (y/n): ");
            char choice = scan.next().toLowerCase().charAt(0);

            if (choice == 'y') {
                System.out.println();
                System.out.println("┌────────────────────┐");
                System.out.println("│       BATTLE       │");
                System.out.println("└────────────────────┘");
                battle.fight(hero, enemyBoss);
                valid = false;
            } else if (choice == 'n') {
                System.out.println("\nYou chose to flee from Morgrath's presence and return to the previous area.");
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
        System.out.println("│     You have exited the Reverie's Edge.    │");
        System.out.println("└────────────────────────────────────────────┘");
        System.out.println();
    }
}
