public class Morgrath {
    private int hp;
    private int attack;
    private int mana;
    private int defense;
    private int speed;
    private String name;
    

    public Morgrath  () {
        this.hp = 20000;
        this.attack = 1000;
        this.mana = 3000;
        this.defense = 1000;
        this.speed = 40;
        this.name = "Morgrath";



    }
    public int swampMaw() {
        if(this.mana >= 500){
            this.mana -= 500;
            int damage = this.attack * 2;
            System.out.println(this.name + " used Swamp Maw! Damage: " + damage);
            return damage;
        } else {
            System.out.println(this.name + " does not have enough mana! ");
            return 0;
        }
    }
    public int pestilentOoze() {
        if(this.mana >= 750){
            this.mana -= 750;
            int damage = this.attack * 4;
            System.out.println(this.name + " used Pestilent Ooze! Damage: " + damage);
            return damage;
        } else {
            System.out.println(this.name + " does not have enough mana! ");
            return 0;
        }
    }


    public int crownOfTheMire() {
        if(this.mana >= 1500){
            this.mana -= 1500;
            int damage = this.attack * 6;
            System.out.println(this.name + " unleashed Eternal Wildstorm! Damage: " + damage);
            return damage;
        } else {
            System.out.println(this.name + " does not have enough mana! ");
            return 0;
        }

    }
    
}
