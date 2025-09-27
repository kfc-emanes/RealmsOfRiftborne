public class Character {
    private int hp;
    private int attack;
    private int mana;
    private int defense;
    private int speed;
    private int level;
    private int experience;
    private String name;
    private String charClass;
    private String weapon;

    public Character(int hp, int attack, int mana, int defense, int speed, int level, int experience, String name, String charClass, String weapon) {
        this.hp = hp;
        this.attack = attack;
        this.mana = mana;
        this.defense = defense;
        this.speed = speed;
        this.level = level;
        this.experience = experience;
        this.name = name;
        this.charClass = charClass;
        this.weapon = weapon;
    }

    // Getters
    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getMana() {
        return mana;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public String getName() {
        return name;
    }

    public String getCharClass() {
        return charClass;
    }

    public String getWeapon() {
        return weapon;
    }

    // Setters
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCharClass(String charClass) {
        this.charClass = charClass;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
}