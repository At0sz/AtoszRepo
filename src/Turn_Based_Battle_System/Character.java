package Turn_Based_Battle_System;

public abstract class Character {
    private String name;
    private int health;
    private int attackPower;
    private int defense;

    // Konstruktor, Getter/Setter


    public Character(String name, int attackPower, int defense) {
        this.name = name;
        this.attackPower = attackPower;
        this.defense = defense;
        this.health = 100;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void takeDamage(int damage) {
        // Matek: tényleges sebzés = damage - defense (de min. 0)
        // HP csökkentése

        int actualDamage = Math.max(damage - this.defense, 0);
        this.health -= actualDamage;
    }
}
