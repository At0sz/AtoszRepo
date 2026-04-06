package LegendaryLootAndQuestManager.Player;

import java.util.ArrayList;
import java.util.List;

public class Hero {
    private final String name;
    private int level;
    private List<Item> inventory;

    public Hero(String name, int level) {
        this.name = name;
        this.level = level;
        this.inventory = new ArrayList<>();
    }

    public void loot (Item item) {
        inventory.add(item);
        System.out.println("Item " + item.getName() + " has been added to the inventory!");
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public List<Item> getInventory() {
        return inventory;
    }
}
