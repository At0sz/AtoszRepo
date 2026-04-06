package LegendaryLootAndQuestManager;

import LegendaryLootAndQuestManager.Player.Hero;
import LegendaryLootAndQuestManager.Player.Item;
import LegendaryLootAndQuestManager.Player.Rarity;
import LegendaryLootAndQuestManager.Player.Weapon;
import LegendaryLootAndQuestManager.Quests.BossFight;
import LegendaryLootAndQuestManager.Quests.LevelTooLowException;
import LegendaryLootAndQuestManager.Quests.Quest;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // 1. Singleton elérése
        LootManager lm = LootManager.getInstance();

        // 2. Tárgyak generálása a globális poolba
        lm.addItem(new Weapon("Rozsdás Kard", 10, Rarity.COMMON));
        lm.addItem(new Weapon("Acél Fejsze", 25, Rarity.RARE));
        lm.addItem(new Weapon("Lángoló Pallos", 60, Rarity.EPIC));
        lm.addItem(new Weapon("Sárkányölő Íj", 100, Rarity.LEGENDARY));
        lm.addItem(new Weapon("Excalibur", 150, Rarity.LEGENDARY));
        lm.addItem(new Weapon("Vakító Fény", 55, Rarity.EPIC));

        // 3. Hősök létrehozása (a javított, 2 paraméteres konstruktorral)
        Hero veteran = new Hero("Rasmussen", 50);
        Hero noob = new Hero("Kezdő Pista", 5);

        // 4. Küldetés szimuláció
        Quest dragonQuest = new BossFight("Sárkány barlangja", 40);

        System.out.println("--- KÜLDETÉS INDUL ---");

        // Sikeres teljesítés
        try {
            dragonQuest.complete(veteran);
            veteran.loot(new Weapon("Sárkány Pikkely Pajzs", 80, Rarity.EPIC));
        } catch (LevelTooLowException e) {
            System.out.println("Hiba: " + e.getMessage());
        }

        // Sikertelen teljesítés (Exception teszt)
        System.out.println("\n--- PISTA PRÓBÁLKOZIK ---");
        try {
            dragonQuest.complete(noob);
        } catch (LevelTooLowException e) {
            System.err.println("ELBUKOTT: " + noob.getName() + " túl gyenge! (Hiba: " + e.getMessage() + ")");
        }

        // 5. STREAM API TESZTEK
        System.out.println("\n--- STATISZTIKÁK (STREAMS) ---");

        // Összérték
        System.out.println("A kincstár összértéke: " + lm.getTotalPoolValue() + " arany");

        // Legértékesebb tárgy (Optional teszt)
        Optional<Item> bestItem = lm.getMostValuableItem();
        bestItem.ifPresent(item -> System.out.println("A legértékesebb kincs: " + item.getName() + " (" + item.getValue() + " gold)"));

        // Legendary tárgyak sorrendben
        System.out.println("\nLegendás tárgyak (drágától az olcsóig):");
        lm.getLegendaryLootSorted().forEach(i ->
                System.out.println("- " + i.getName() + ": " + i.getValue() + " gold")
        );

        // Csoportosítás ritkaság szerint (GroupingBy + Joining)
        System.out.println("\nTárgyak listája ritkaság szerint:");
        lm.getLootSummaryByRarity().forEach((rarity, names) ->
                System.out.println("[" + rarity + "]: " + names)
        );
    }
}
