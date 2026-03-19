package Turn_Based_Battle_System;

public class BattleEngine {

    public void fight(Hero hero, Monster monster) {
        System.out.println("--- CSATA KEZDŐDIK ---");

        while (hero.getHealth() > 0 && monster.getHealth() > 0) {
            hero.attack(monster);

            if (monster.getHealth() > 0) {
                monster.attack(hero);
            }

            System.out.println(hero.getName() + " HP: " + hero.getHealth() + " | "
                    + monster.getName() + " HP: " + monster.getHealth());
            System.out.println("--------------------");
        }

        if (hero.getHealth() > 0) {
            System.out.println("🏆 Győzelem! " + monster.getName() + " elpusztult.");
            hero.gainXp(monster.getXpReward());
        } else {
            System.out.println("💀 Meghaltál... A játék véget ért.");
        }
    }
}