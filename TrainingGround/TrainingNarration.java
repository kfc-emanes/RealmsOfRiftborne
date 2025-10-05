package TrainingGround;

import java.util.Scanner;

public class TrainingNarration {

    Scanner scanner = new Scanner(System.in);

    public void exploreNarration(){

        String[] exploreGym = {
            "As you step into the left wing of the Gym, the space opens up into the Academy's main Training Ground - a place alive with energy and motion.",
            "To your right, sparks and streaks of light fly through the air as students duel with wands, chanting spells that crackle with raw magic.",
            "To your left, the clash of bodies and grunts of effort mark intense physical sparring matches - each challenger fighting hard, neither willing to back down.",
            "At the center, the sharp clang of steel rings out with every sword strike, echoing through the chamber like a challenge to all who hear it.",
            "And just beyond that, in a reinforced zone surrounded by glowing wards, sharp bangs pierce the air - students practice with enchanted firearms, weaving gunplay and magic into fast-paced, high-precision sparring.",
            "Suddenly, a student in uniform approaches you with a slight smirk.",
            "\"You new?\" they ask, glancing at your stance.",
            "\"You\'ve got the look - wide-eyed, maybe a bit eager,\" they continue, crossing their arms. \"But this place doesn\'t care how eager you are. It cares how hard you hit - and how well you hold your ground.\"",
            "You meet their gaze. There\'s no hostility in their tone - just challenge.",
            "\"Come on,\" they say, nodding toward the center of the arena. \"If you\'re serious about being here, it starts with training. No excuses.\"",
            "You follow their lead, heart beginning to pound - not from fear, but from something deeper. Determination.",
            "It\s time to prove you belong."
        };

        System.out.println();
        System.out.println("┌────────────────────────────────────┐");
        System.out.println("│     Press Enter to continue...     │");
        System.out.println("└────────────────────────────────────┘");

        playGymNarration(exploreGym);

    }

    public void playGymNarration(String[] arr){

        for(int i = 0; i < arr.length; i++){
            scanner.nextLine();
            System.out.println(arr[i]);
        }

    }
}
