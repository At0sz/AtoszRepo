package LegendaryLootAndQuestManager.Quests;

import LegendaryLootAndQuestManager.Player.Hero;

public class BossFight extends Quest {

    public BossFight(String title, int minLevel) {
        super(title, minLevel);
    }

    @Override
    public void complete(Hero hero) {
        if (hero.getLevel() < minLevel) {
            throw new LevelTooLowException("Level is too low!");
        }
        System.out.println(hero.getName() + " legyőzte a Bosst: " + title);
    }
}
