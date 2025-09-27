public class Azrael {
    private int hp;
    private int attack;
    private int mana;
    private int defense;
    private int speed;
    private String name;
    

    public Azrael() {
        this.hp = 30000;
        this.attack = 5000;
        this.mana = 4500;
        this.defense = 1000;
        this.speed = 40;
        this.name = "Azrael - Annihilation of Realms";



    }
    public int eclipseBlades() {
        if(this.mana >= 300){
            this.mana -= 300;
            int damage = this.attack * 2;
            System.out.println(this.name + " used Eclipse Blades! damage: " + damage);
            return damage;
        } else {
            System.out.println(this.name + " does not have enough mana! ");
            return 0;
        }
    }
    public int graviticCollapse() {
        if(this.mana >= 750){
            this.mana -= 750;
            int damage = this.attack * 4;
            System.out.println(this.name + " used Gravitic Collapse! Damage: " + damage);
            return damage;
        } else {
            System.out.println(this.name + " does not have enough mana! ");
            return 0;
        }
    }


    public int shattershock() {
        if(this.mana >= 1500){
            this.mana -= 1500;
            int damage = this.attack * 6;
            System.out.println(this.name + " unleashed Shattershock! Damage: " + damage);
            return damage;
        } else {
            System.out.println(this.name + " does not have enough mana! ");
            return 0;
        }

    }
    public int oblivionsEmbrace() {
        if(this.mana >= 2000){
            this.mana -= 2000;
            int damage = this.attack * 8;
            System.out.println(this.name + " activated Oblivion's Embrace! Damage: " + damage);
            return damage;
        } else {
            System.out.println(this.name + " does not have enough mana! ");
            return 0;
        }

    }


    
}
