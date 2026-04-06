package LegendaryLootAndQuestManager;

import LegendaryLootAndQuestManager.Player.Item;
import LegendaryLootAndQuestManager.Player.Rarity;

import java.util.*;
import java.util.stream.Collectors;

public class LootManager {
    private static LootManager instance;
    private List<Item> globalLootPool = new ArrayList<>();

    private LootManager() {
    }

    public static LootManager getInstance() {
        if (instance == null) {
            instance = new LootManager();
        }
        return instance;
    }

    public void addItem(Item item) { globalLootPool.add(item); }

    // --- STREAM API FELADATOK ---

    // 1. Szűrd ki a LEGENDARY tárgyakat, és rendezd őket érték (Value) szerint csökkenőbe!
    public List<Item> getLegendaryLootSorted() {
        return globalLootPool.stream()
                .filter(item -> item.getRarity() == Rarity.LEGENDARY)
                .sorted(Comparator.comparingInt(Item::getValue).reversed())
                .toList();
    }

    // 2. Számold ki az összes kincstárban lévő tárgy összértékét!
    public int getTotalPoolValue() {
        return globalLootPool.stream()
                .mapToInt(Item::getValue)
                .sum();
    }

    // 3. Készíts egy Map-et, ahol a kulcs a Rarity, az érték pedig a tárgyak NEVEI vesszővel elválasztva!
    // Tipp: Collectors.groupingBy és Collectors.mapping(Item::getName, Collectors.joining(", "))
    public Map<Rarity, String> getLootSummaryByRarity() {
        return globalLootPool.stream()
                .collect(Collectors.groupingBy(
                        Item::getRarity,
                        Collectors.mapping(Item::getName, Collectors.joining(", "))
                ));
    }

    // 4. Keresd meg a legértékesebb tárgyat, de adj vissza egy Optional<Item>-et!
    public Optional<Item> getMostValuableItem() {
        return globalLootPool.stream()
                .max(Comparator.comparingInt(Item::getValue));
    }
}

