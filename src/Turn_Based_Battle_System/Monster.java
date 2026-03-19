package Turn_Based_Battle_System;

import java.util.Random;

public class Monster extends Character {
    private int xpReward;
    private Random rand = new Random();

    public Monster(String name, int attackPower, int defense, int xpReward) {
        super(name, attackPower, defense);
        this.xpReward = xpReward;
    }

    public int getXpReward() {
        return xpReward;
    }

    public void attack(Character target) {
        int damage = getAttackPower() + rand.nextInt(4);
        System.out.println(getName() + " visszatámad: " + damage + " sebzés.");
        target.takeDamage(damage);
    }
}
