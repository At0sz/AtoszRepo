package Turn_Based_Battle_System;

import java.util.Random;

public class Hero extends Character {
    private int level = 1;
    private int xp = 0;
    private Random random = new Random();
    private Weapon weapon;

    public Hero(String name, int attackPower, int defense, Weapon weapon) {
        super(name, attackPower, defense);
        this.weapon= weapon;
    }

    // Kritikus sebzés esélye (pl. 15%)
    private boolean isCritical() {
        return random.nextInt(100) < 15;
    }

    public void attack(Character target) {
        int baseDamage = getAttackPower() + random.nextInt(5);

        if (isCritical()) {
            baseDamage *= 2;
            System.out.println("💥 KRITIKUS TALÁLAT! 💥");
        }

        System.out.println(getName() + " ütése: " + baseDamage + " sebzés.");
        target.takeDamage(baseDamage);
    }

    public void gainXp(int amount) {
        this.xp += amount;
        System.out.println("✨ Kapott XP: " + amount + " | Összes: " + xp + "/100");
        if (this.xp >= 100) {
            levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        this.xp -= 100; // Maradék XP megmarad
        setAttackPower(getAttackPower() + 5);
        setDefense(getDefense() + 2);
        setHealth(100); // Reset HP
        System.out.println("⬆️ SZINTLÉPÉS! Szint: " + level + ". Az erőd növekszik!");
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}

