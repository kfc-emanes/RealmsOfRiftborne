package TrainingGround;

import Hero.*;

public class StatProgress {

    public void endurance(Hero hero){
        hero.setHp(hero.getHp() + 2); // hero.setMaxHp()
        hero.setDefense(hero.getDefense() + 2);
        hero.setMana(hero.getMana() + 3);
        hero.setSpeed(hero.getSpeed() + 1); 

    }

    public void strength(Hero hero){
        hero.setAttack(hero.getAttack() + 3);
        hero.setHp(hero.getHp() + 2);
        hero.setSpeed(hero.getSpeed() + 2);

    }

    public void durability(Hero hero){
        hero.setDefense(hero.getDefense() + 2);
        hero.setAttack(hero.getAttack() + 3);
        hero.setMana(hero.getMana() + 3);

    }

    public void mana(Hero hero){
        hero.setAttack(hero.getAttack() + 1);
        hero.setMana(hero.getMana() + 5);

    }
    
    public void displayXPandLevel(Hero hero, int xpReward) {
        hero.setExperience(hero.getExperience() + xpReward); // remove all level up related code replace it with hero.levelUp(xpReward)
        hero.setLevel(hero.getExperience() / 100); // remove

        // just do hero.levelUp(xpReward); whenever the player/hero gain exp

        System.out.println();
        System.out.printf(">>> You gained +%d experience points!%n", xpReward);
        System.out.printf(">>> Current Level: %d%n", hero.getLevel());

        displayStats(hero);
        checkHpValidation(hero); // checkLevelValidation
    }

    public void displayStats(Hero hero){
        System.out.println();
        System.out.println("=== UPDATED HERO STAT SUMMARY ===");
        System.out.printf("Attack:  %d%n", hero.getAttack());
        System.out.printf("Defense: %d%n", hero.getDefense());
        System.out.printf("Mana:    %d%n", hero.getMana());
        System.out.printf("Speed:   %d%n", hero.getSpeed());
        System.out.println("===============================");
        //polish table where it also shows its old stats and new stats
    }

    public void checkHpValidation(Hero hero){
        int level = hero.getExperience() / 100;
        hero.setLevel(level);

        if(!hero.canEnterArea1()){
            if(level >= 20){
                System.out.println();
                System.out.println("┌──────────────────────────────────────────────────────────┐");
                System.out.println("│   You meet the requirements for The Forest of Reverie    │");
                System.out.println("│ Visit the Principal's Office to get your permission slip │");
                System.out.println("└──────────────────────────────────────────────────────────┘");
            }
            

        } else if (!hero.canEnterArea2()){
            if(level >= 40) {
                System.out.println();
                System.out.println("┌─────────────────────────────────────────────────────────────┐");
                System.out.println("│     You meet the requirements for The Reverie\'s Edge       │");
                System.out.println("│  Visit the Principal's Office to get your permission slip   │");
                System.out.println("└─────────────────────────────────────────────────────────────┘");
            }
            

        } else if (!hero.canEnterArea3()){
            if(level <= 60) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────────────────────────┐");
                System.out.println("│      You meet the requirements for The Forsaken Lands        │");
                System.out.println("│  Visit the Principal's Office to get your permission slip    │");
                System.out.println("└──────────────────────────────────────────────────────────────┘");
            }
            
        } 
    }

}