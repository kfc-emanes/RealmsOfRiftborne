public class Elderthorn {
    private int hp;
    private int attack;
    private int mana;
    private int defense;
    private int speed;
    private String name;
    

    public Elderthorn() {
        this.hp = 10000;
        this.attack = 450;
        this.mana = 1000;
        this.defense = 150;
        this.speed = 20;
        this.name = "Elderthorn";



    }

    public int thornSlash() {
        if(this.mana >= 300){
            this.mana -= 300;
            int damage = this.attack * 2;
            System.out.println(this.name + " used Thorn Slash! Damage: " + damage);
            return damage;
        } else {
            System.out.println(this.name + " does not have enough mana! ");
            return 0;
        }
    }

    public int eternalWildstorm() {
        if(this.mana >= 600){
            this.mana -= 600;
            int damage = this.attack * 4;
            System.out.println(this.name + " unleashed Eternal Wildstorm! Damage: " + damage);
            return damage;
        } else {
            System.out.println(this.name + " does not have enough mana! ");
            return 0;
        }

    }


    
}
