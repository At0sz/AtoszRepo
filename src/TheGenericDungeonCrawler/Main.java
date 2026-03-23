package TheGenericDungeonCrawler;

import TheGenericDungeonCrawler.Entities.*;
import TheGenericDungeonCrawler.GenericInterface.Fireball;
import TheGenericDungeonCrawler.GenericInterface.Heal;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== THE GENERIC DUNGEON CRAWLER INDUL ===");

        // 1. Hősök létrehozása (Adunk nekik egy-egy üres Storage-ot)
        Hero warrior = new Hero("Aragorn", 25, 120, 30, new Storage<>());
        Hero mage = new Hero("Gandalf", 5, 80, 100, new Storage<>());

        List<Hero> party = new ArrayList<>();
        party.add(warrior);
        party.add(mage);

        // 2. Skillek kiosztása (Polimorfizmus: a Skill<Entity> interfészt használjuk)
        warrior.setSpecialSkill(new Fireball(40)); // A harcos is tud tüzet dobni, ha van manája
        mage.setSpecialSkill(new Fireball(60));

        // 3. Loot rendszer tesztelése (Generics & Storage)
        System.out.println("\n--- Looting fázis ---");
        warrior.addItemToInventory(new Weapon("Andúril", 50));
        warrior.addItemToInventory(new Weapon("Rozsdás tőr", 5));

        mage.addItemToInventory(new Potion("Mana főzet", 50));
        mage.addItemToInventory(new Potion("Élet itala", 100));

        System.out.println("Aragorn legjobb tárgya: " + warrior.getInventory().getBestItem());
        System.out.println("Gandalf legjobb tárgya: " + mage.getInventory().getBestItem());

        // 4. Szörnyek generálása
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster("Goblin 1", 10, 30, 0));
        monsters.add(new Monster("Goblin 2", 10, 30, 0));
        monsters.add(new Monster("Uruk-hai Boss", 20, 150, 0));

        // 5. A Battle Arena indítása
        BattleArena arena = new BattleArena(party, monsters);

        System.out.println("\n--- HARC ELŐTTI ÁLLAPOT ---");
        System.out.println("Hősök száma: " + party.size());
        System.out.println("Szörnyek száma: " + monsters.size());

        // BUMM! Itt fut le a Stream-es, Exception-kezelős logika
        arena.startMassacre();

        // 6. Végeredmény ellenőrzése
        System.out.println("\n--- HARC UTÁNI STATISZTIKA ---");
        party.forEach(h -> System.out.println(h.getName() + " maradék HP: " + h.getHp() + " | Mana: " + h.getMana()));
    }
}