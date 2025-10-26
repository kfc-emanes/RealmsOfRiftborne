package Narration;

import java.util.Scanner;

public class Narration{
    static Scanner scanner = new Scanner(System.in);

    // Narration for Prologue
    public static void prologueNarration() {
        String[] prologue = {
            "Your days as a student have been a blur of half-hearted effort and quiet frustration. \nLessons slip past you, faces blur together, and the weight of expectations presses down heavier with each passing week. \nTonight feels no different-you collapse into bed, hoping that sleep might offer an escape, if only for a few hours.",
            
            "But this sleep is unlike any other.",
            
            "You wake with a start, not in your room, but in a place you\'ve never seen before. \nThe air is sharp and crisp, carrying the faint scent of pine and old stone. A pale moon hangs above an endless forest, \nits silver light spilling over a towering structure in the distance - a fortress, its spires reaching for the sky.",
            
            "As you take a cautious step forward, a presence brushes against you. Weightless, yet undeniable. \nA wandering spirit materializes from the air itself, its form shifting like smoke caught in a breeze. \nIts voice is calm, distant, but warm enough to ease the unease clawing at your chest.",
            
            "\"Welcome, lost one. Please to meet you, I am Louraine Aetherlight, the wandering spirit it says.\" it says \"This is Mystvale Academy… a place where magic is studied, honed, \nand wielded against the forces beyond these walls. You now stand on the threshold of its trials.\"",
            
            "\"The academy rests at the edge of a vast forest, shielding its students from the dangers beyond.\"",
            
            "\"The inner region, just past the academy\'s borders, is alive with mystical woods and wandering lesser entities.\"",
            
            "\"The middle region stretches into treacherous swamplands, where stronger beings test the skill of even seasoned students.\"",
            
            "\"And far beyond lies the outer region - the dominion of entities. Few dare tread there, \nfor it is said the most fearsome of them all awaits in its shadowed heart.\"",
            
            "Louraine's gaze lingers on you, sharp and knowing.",
            
            "\"Why you were brought here, only time will reveal. But your path begins now. \nLearn, survive, and perhaps... you may one day stand against what lies beyond the forest.\"",
            
            "Louraine, the wandering spirit, fades, leaving only silence and the academy\'s looming silhouette in the distance.",
            
            "Your story has begun."
        };

        // Array Iteration 
        playSection(prologue);
    }

    // Academy Narration
    public static void academyNarration() {
        String[] academy = {
            "The tall gates of Mystvale Academy open with a low groan...",
            
            "You pause, taking it all in, when a sudden chill brushes your shoulder...",
            
            "You nearly stumble back in shock. It's Louraine again!",
            
            "\"I didn't expect you again,\" you mutter under your breath.",
            
            "The Louraine's form flickers faintly, its voice calm and patient.",
            
            "\"Do not be alarmed. Mystvale Academy is vast...\"",
            
            "She gestures toward the campus...",
            
            "\"The Library, a hall of ancient knowledge...\"",
            
            "\"Canteen, though filled with chatter...\"",
            
            "\"The Training Ground, where strength is forged...\"",
            
            "\"And finally\" the Louraine's form stills...",
            
            "Its glow dims slightly as the explanations end.",
            
            "\"Here, you are free to choose your path...\"",
            
            "The spirit fades as quickly as it appeared..."
        };

        // Array Iteration
        playSection(academy);
    }

    // Library Narration
    public static void libraryNarration() {
        String[] library = {
            "As you step into the library, the air grows still...",
            
            "The Louraine, the wandering spirit, flickers into view...",
            
            "\"This is Mystvale's Library. May knowledge guide you...\"",
            
            "Then she fades out of your sight, leaving only the quiet rustle of unseen pages."
        };

        // Array Iteration
        playSection(library);
    }

    // Canteen Narration | Not used
    public static void canteenNarration() {
        String[] canteen = {
            "The canteen hums with the chatter of students...",
            
            "The wandering spirit, Louraine, appears at your side.",
            
            "\"This is no ordinary canteen...\"",
            
            "The spirit fades, and the scent of food and metal lingers in the air."
        };

        // Array Iteration
        playSection(canteen);
    }

    // Gym Narration
    public static void gymNarration() {
        String[] gym = {
            "The Training Ground resounds with the clash of practice weapons...",
            
            "Louraine, as expected, flickers into view.",
            
            "\"This is where strength is forged. Accept the challenge, and recieve the gift of strength, precision, and courage...\"",
            
            "The spirit fades, leaving the echoes of steel and determination behind."
        };

        // Array Iteration
        playSection(gym);
    }

    // Principal's Office Narration
    public static void principalOfficeNarration() {
        String[] principalOffice = {
            "The doors of the Principal\'s Office stand tall and unyielding...",
            
            "Louraine appears at your side...",
            
            "\"This office is the gateway through end of this vast land. May the odds be in your favor...\"",
            
            "Louraine fades, and the silence around the doorway feels heavier..."
        };

        //Array Iteration
        playSection(principalOffice);
    }

    // Area 1 Narration
    public static void area1Narration() {
        String[] area1 = {
            "The trees of the inner forest rise around you...",
            "Louraine, then drifts into view...",
            "\"This is the inner forest, where you\'d be able to meet the lowest of lows entities.\"",
            "Before fading away, she looked at you with a meaningful look before vanishing out of your sight,\nleaving you with the quiet but uneasy rhythm..."
        };

        playSection(area1);
    }

    // Area 2 Narration
    public static void area2Narration() {
        String[] area2 = {
            "The air thickens as you step into the swamp area. Muddy, stinky, and lacks of life. You felt a sudden chill down your spines.",
            "Louraine, as expected, flickers beside you.",
            "\"This is the middle region, the Swamp, where much stronger entities lives. Be careful, they may seem not strong, but they are more blood thirsty\"",
            "Before fading away, she touched your shoulder, giving you the look of encouragement.\n Before you could even say a thing, she then vanished into the mist..."
        };

        playSection(area2);
    }

    // Area 3 Narration
    public static void area3Narration() {
        String[] area3 = {
            "Stone ruins and jagged towers stretch across the horizon. Suprisingly, it has more life than the previous areas you have been.\nBut you cannot seem to shake off the eerie feeling, your test tighten as you feel like suffocated with the how heavy the feeling is.",
            "Louraine then appears, her face radiates something you cannot pinpoint.",
            "\"This is the outer region, the Forsaken Lands where all strong entities lives. But at the end of this land, there lies the strongest of them all.\nMy only advice to you is to never let your guard down, and may the odds be in your favor...\"",
            "Louraine lingers for a moment longer before fading away from your sight.\nBut as her presence left the area, you felt the wind lingers onto your skin, feeling like she is still there, not quite present, but watching over you."
        };

        playSection(area3);
    }

    // Shop Narration
    public static void shopNarration() {
        String[] shop = {
            "Louraine appears beside you, its form calm against the quiet sway of trees.",
            
            "\"This is the academy\'s supply shop,\" it says. \"Here, students exchange earned coin for tools of survival \n- weapons, potions, and the occasional rarity.\"",
            
            "\"The blades offered within are not forged for vanity. They are made to defend, to endure, and when necessary - to end.\"",
            
            "\"Potions line the shelves too. Restoratives, enhancers, mixtures \ndrawn from the deeper knowledge of the academy\'s alchemists. Each serves a purpose - if used wisely.\"",
            
            "\"Your currency is merit earned - through trials, tasks, and time. \nSpend it with intention. Not everything you need will be here twice.\"",
            
            "Louraine's gaze lingers on the door before fading again into the still air.",
            
            "You step forward. The scent of old leather, steel, \nand something faintly herbal greets you as the shop door creaks open."
        };
    
        playSection(shop);
    }

    public static void shopConversationNarration() { // narration not used | to be followed
        String[] shopOwner = {
            "You push open the creaking door, and the scent of herbs and aged wood fills the air. Sunlight glints off shelves stacked with mysterious trinkets and glowing vials.",

            "A small bell jingles. Behind the counter, the shopkeeper peers over his spectacles.",

            "\"Ah! A new face,\" he says. \"Welcome to Mystic Curiosities! I\'m Kabang Cobbleton. Handle the items wisely—they have stories… and secrets.\"",

            "You nod, heart racing with curiosity. Your adventure in the shop has begun."
        };

        playSection(shopOwner);
    }

    // Inventory Narration | Not Used
    public static void inventoryNarration() {
        String[] inventory = {
            "Louraine emerges without a sound, its form quiet and steady beside you.",

            "\"All that you carry tells a story,\" it begins. \"Not just of what you\'ve gathered - but of what you\'ve chosen to keep.\"",
            
            "\"Your inventory is more than a sack of tools. It holds your weapons, potions, relics - the fragments of your journey so far.\"",
            
            "\"Some items will aid you in battle. Others may offer insight, or protection, or... choices you do not yet understand.\"",
            
            "\"Know this — your space is not endless. What you carry reflects what you value. Choose wisely, discard carefully.\"",
            
            "The spirit lingers a moment longer, then drifts away — leaving you with your pack, your thoughts, and the quiet shifting of leaves overhead."
        };

        playSection(inventory);
    }

    //box format
    public static void choiceSwordsman(){
        System.out.println("┌────────────────────┐(_ _)");
        System.out.println("│  Player Character  │  \\/)");
        System.out.println("└────────────────────┘___||");
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│                      Kael Solmere - Details                │");
        System.out.println("├────────────────────────────────────────────────────────────┤");
        System.out.println("│ Name: Kael Solmere                                         │");
        System.out.println("│ Age: 15                                                    │");
        System.out.println("│ Personality: Intense, Brooding, Fiercely Loyal             │");
        System.out.println("│ Grade Level: First Year                                    │");
        System.out.println("│ Fun Facts:                                                 │");
        System.out.println("│   - Siblings with Aria Caelith                             │");
        System.out.println("│   - Talks to his shadow when thinking                      │");
        System.out.println("│   - Collects old maps and mysterious relics                │");
        System.out.println("│   - Can\'t resist chocolate during stressful situations    │");
        System.out.println("│   - Has a soft spot for stray animals                      │");
        System.out.println("└────────────────────────────────────────────────────────────┘");


        System.out.println("┌───────────────────────────────────────────┐");
        System.out.println("│  Swordsman (MAX LVL: 60)                  │");
        System.out.println("│  Basic Attack                             │");
        System.out.println("│  Skill 1 - Blade Dance                    │");
        System.out.println("│  Skill 2 - Blinding Silhouette            │");
        System.out.println("│  Ultimate - Shattered Sun                 │");
        System.out.println("│                                           │");
        System.out.println("│  HP (Health Points): 4000                 │");
        System.out.println("│  MP (Mana Points):    700                 │");
        System.out.println("│  ATK (Attack):        500                 │");
        System.out.println("│  DEF (Defence):       350                 │");
        System.out.println("│  SPD (Speed):         200                 │");
        System.out.println("└───────────────────────────────────────────┘");

        System.out.println("┌───────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                                  Backstory                                    │");
        System.out.println("├───────────────────────────────────────────────────────────────────────────────┤");
        System.out.println("│ Kael Solmere was born into a family burdened with a dark legacy. The Solmeres │");
        System.out.println("│ were once the guardians of the Kim Morvain, a forbidden artifact of immense   │");
        System.out.println("│ power. Centuries ago, a treacherous betrayal shattered the family, scattering │");
        System.out.println("│ its members and leaving a lingering curse upon their bloodline.               │");
        System.out.println("│                                                                               │");
        System.out.println("│ Now, Kael and his sister Aria are the last heirs. Their path is not only about│");
        System.out.println("│ mastering their extraordinary abilities but also unraveling the secrets       │");
        System.out.println("│ haunting their family. Bound by loyalty and shared struggle, the siblings     │");
        System.out.println("│ rely on each other to survive trials, uncover hidden truths, and confront the │");
        System.out.println("│ forces behind the Kim Morvain. Every step they take brings them closer to     │");
        System.out.println("│ breaking the curse—if they can withstand the shadows of their bloodline.      │");
        System.out.println("└───────────────────────────────────────────────────────────────────────────────┘");


}
    
    public static void choiceGunner() {
        System.out.println();
        System.out.println("┌────────────────────┐");
        System.out.println("│  Player Character  │");
        System.out.println("└────────────────────┘");
        System.out.println("┌────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                          Aria Caelith (Mother's Surname)                       │");
        System.out.println("├────────────────────────────────────────────────────────────────────────────────┤");
        System.out.println("│ Age: 15                                                                        │");
        System.out.println("│ Personality: Quick-Witted, Resourceful, Fiercely Protective of Kael            │");
        System.out.println("│ Grade Level: First Year Student                                                │");
        System.out.println("│ Fun Facts:                                                                     │");
        System.out.println("│   - Siblings with Kael Solmere                                                 │");
        System.out.println("│   - Skilled gunner and inventor                                                │");
        System.out.println("│   - Always carries a small notebook for invention ideas                        │");
        System.out.println("│   - Loves collecting rare crystals and gemstones                               │");
        System.out.println("│   - Has a habit of whistling when nervous                                      │");
        System.out.println("│   - Can memorize complex mechanisms after seeing them once                     │");
        System.out.println("│   - Prefers fast-paced games over slow strategy games                          │");
        System.out.println("└───────────────────────────────────────────────────────────────────────────────┘");

        System.out.println("┌───────────────────────────────────────────┐");
        System.out.println("│  Gunner (MAX LVL: 60)                     │");
        System.out.println("│  Basic Attack                             │");
        System.out.println("│  Skill 1 - Flurry shots                   │");
        System.out.println("│  Skill 2 - Frostwind Bullet               │");
        System.out.println("│  Ultimate - Judgement Phantom             │");
        System.out.println("│                                           │");
        System.out.println("│  HP (Health Points): 4000                 │");
        System.out.println("│  MP (Mana Points):    600                 │");
        System.out.println("│  ATK (Attack):        450                 │");
        System.out.println("│  DEF (Defence):       300                 │");
        System.out.println("│  SPD (Speed):         200                 │");
        System.out.println("└───────────────────────────────────────────┘");
            
        System.out.println("┌────────────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                                        Backstory                                           │");
        System.out.println("├────────────────────────────────────────────────────────────────────────────────────────────┤");
        System.out.println("│ Aria Caelith shares the burden of her family\'s cursed legacy alongside her brother Kael.  │");
        System.out.println("│ As the last heirs of the Solmere lineage, the siblings train tirelessly to master their    │");
        System.out.println("│ skills and uncover the secrets of the forbidden artifact known as the Kim Morvain.         │");
        System.out.println("│ Fiercely loyal to each other, Aria uses her quick wit and resourcefulness to protect Kael  │");
        System.out.println("│ and navigate the dangerous path left by centuries of betrayal. Together, they strive to    │");
        System.out.println("│ break the curse, restore their family\'s honor, and bring justice to those who threaten    │");
        System.out.println("│ the legacy of the Kim Morvain.                                                             │");
        System.out.println("└────────────────────────────────────────────────────────────────────────────────────────────┘");

}
    
    public static void choiceMage() {
        System.out.println();
        System.out.println("┌────────────────────┐");
        System.out.println("│  Player Character  │");
        System.out.println("└────────────────────┘");
        System.out.println("┌───────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                                  Selene Arclight                              │");
        System.out.println("├───────────────────────────────────────────────────────────────────────────────┤");
        System.out.println("│ Age: 15                                                                       │");
        System.out.println("│ Personality: Prideful                                                         │");
        System.out.println("│ Grade Level: First Year Student                                               │");
        System.out.println("│ Fun Facts:                                                                    │");
        System.out.println("│   - Always practices spells under moonlight                                   │");
        System.out.println("│   - Can memorize complex incantations after hearing them once                 │");
        System.out.println("│   - Has a secret love for herbal teas                                         │");
        System.out.println("│   - Often talks to magical familiars when thinking                            │");
        System.out.println("│   - Collects rare magical crystals                                            │");
        System.out.println("└───────────────────────────────────────────────────────────────────────────────┘");


        System.out.println("┌───────────────────────────────────────────┐");
        System.out.println("│  Mage (MAX LVL: 60)                       │");
        System.out.println("│  Basic Attack                             │");
        System.out.println("│  Skill 1 - Stellar Shard                  │");
        System.out.println("│  Skill 2- Chains of Starlight             │");
        System.out.println("│  Ultimate - Astral Cataclysm              │");
        System.out.println("│                                           │");
        System.out.println("│  HP (Health Points): 4000                 │");
        System.out.println("│  MP (Mana Points):   1000                 │");
        System.out.println("│  ATK (Attack):        550                 │");
        System.out.println("│  DEF (Defence):       180                 │");
        System.out.println("│  SPD (Speed):         200                 │");
        System.out.println("└───────────────────────────────────────────┘");
            
        System.out.println("┌────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                                  Backstory                                     │");
        System.out.println("├────────────────────────────────────────────────────────────────────────────────┤");
        System.out.println("│ Born into the prestigious Arclight family, Selene was destined to inherit her  │");
        System.out.println("│ family's mantle as the head of the lineage. A prodigy in the arcane arts, her  │");
        System.out.println("│ every day was filled with rigorous training, mastering spells that could bend  │");
        System.out.println("│ starlight and summon cosmic energies.                                          │");
        System.out.println("│                                                                                │");
        System.out.println("│ Everything changed when her father introduced a boy claiming to be her brother,│");
        System.out.println("│ a contender for the very inheritance Selene was meant to claim. Pride and      │");
        System.out.println("│ ambition ignited, fueling her determination to surpass him in every way.       │");
        System.out.println("│                                                                                │");
        System.out.println("│ To prove her worth, Selene enrolled in the academy, immersing herself in every │");
        System.out.println("│ spell, ritual, and fragment of forbidden knowledge it offered. Her goal: to    │");
        System.out.println("│ achieve feats so extraordinary that even her father cannot deny her as the true│");
        System.out.println("│ heir. Along the way, confronting the mysterious Kim Morvain may cement her     │");
        System.out.println("│ legacy.                                                                        │");
        System.out.println("└────────────────────────────────────────────────────────────────────────────────┘");
        System.out.println();
    }

    // Array Iteration in order to avoid for loop usage repitition
    public static void playSection(String[] section) {
        while (true) {
            try {
                System.out.println();
                System.out.println("┌───────────────────────────────────────────┐");
                System.out.println("│   Do you want to skip narration? (y/n):   │");
                System.out.println("└───────────────────────────────────────────┘");
                System.out.print("-->| ");

                String input = scanner.nextLine().trim();
                if (input.isEmpty()) input = " ";
                char skipChoice = input.charAt(0);

                switch (skipChoice) {
                    case 'y':
                        System.out.println("┌───────────────────────┐");
                        System.out.println("│   Narration skipped   │");
                        System.out.println("└───────────────────────┘");
                        return;

                    case 'n':
                        System.out.println();
                        System.out.println("┌─────────────────────────────┐");
                        System.out.println("│   Press Enter to continue   │");
                        System.out.println("└─────────────────────────────┘");

                        for (String line : section) {
                            scanner.nextLine();
                            System.out.println(line);
                        }
                        System.out.println(); 
                        return; 
                    
                    default:
                        System.out.println();
                        System.out.println("┌────────────────────────────────────────┐");
                        System.out.println("│   Choice unclear! Enter 'y' or 'n'.    │");
                        System.out.println("└────────────────────────────────────────┘");
                        break;
                }
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

