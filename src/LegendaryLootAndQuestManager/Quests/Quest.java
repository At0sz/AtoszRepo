package LegendaryLootAndQuestManager.Quests;

import LegendaryLootAndQuestManager.Player.Hero;
import LegendaryLootAndQuestManager.Player.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class Quest {
    protected String title;
    protected int minLevel;
    protected List<Item> potentialLoot = new ArrayList<>();

    protected Quest(String title, int minLevel) {
        this.title = title;
        this.minLevel = minLevel;
    }

    public abstract void complete(Hero hero);

    public List<Item> getPotentialLoot() { return potentialLoot; }
}
