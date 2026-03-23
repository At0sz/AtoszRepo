package TheGenericDungeonCrawler;

import TheGenericDungeonCrawler.Entities.Entity;
import TheGenericDungeonCrawler.Entities.Hero;
import TheGenericDungeonCrawler.Entities.Monster;
import TheGenericDungeonCrawler.GenericInterface.NoManaException;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BattleArena {
    private List<Hero> heroList = new ArrayList<>();
    private List<Monster> monsterList = new ArrayList<>();

    public BattleArena(List<Hero> heroList, List<Monster> monsterList) {
        this.heroList = heroList;
        this.monsterList = monsterList;
    }

    public void startMassacre() {
        System.out.println("--- A CSATA KEZDŐDIK! ---");


        while (!heroList.isEmpty() && !monsterList.isEmpty()) {
            for (Hero hero : heroList) {

                Monster target = monsterList.stream()
                        .filter(Monster::isAlive)
                        .min(Comparator.comparingInt(Monster::getHp))
                        .orElse(null);

                if (target != null) {
                    try {
                        hero.useSpecialSkill(target);
                        System.out.println("\n"+hero.getName() + " különleges képességet használt!");
                    } catch (NoManaException e) {
                        System.out.println("HIBA: " + e.getMessage());
                        System.out.println(hero.getName() + " mana híján csak simán üt...");
                        hero.attack(target);
                    }
                }
            }
            System.out.println();

            for (Monster monster : monsterList) {
                Hero heroTarget = heroList.get(0);
                if (monster.isAlive()) {
                    monster.attack(heroTarget);
                }
            }

            monsterList.removeIf(m -> !m.isAlive());
            heroList.removeIf(h -> !h.isAlive());

            System.out.println("Kör vége. Szörnyek száma: " + monsterList.size());
            System.out.println();
        }

        if (heroList.isEmpty()) {
            System.out.println("A szörnyek győztek... A Dungeon elnyelt titeket.");
        } else {
            System.out.println("GYŐZELEM! A hősök megtisztították a területet.");
        }
    }


    public void addEntity(Entity entity) {
        if (entity instanceof Hero) {
            heroList.add((Hero) entity);
        } else if (entity instanceof Monster) {
            monsterList.add((Monster) entity);
        } else {
            System.out.println("Error, unknown entity type");
        }
    }

    public List<Hero> getHeroList() {
        return heroList;
    }

    public List<Monster> getMonsterList() {
        return monsterList;
    }
}
