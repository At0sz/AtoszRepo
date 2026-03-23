package TheGenericDungeonCrawler.GenericInterface;

import TheGenericDungeonCrawler.Entities.Entity;
import TheGenericDungeonCrawler.Entities.Hero;

public class Heal implements Skill<Entity> {
    private int healAmount;

    public Heal(int healAmount) {
        this.healAmount = healAmount;
    }

    @Override
    public void cast(Entity target) throws NoManaException {
        {
            if (target instanceof Hero) {
                target.setHp(target.getHp() + healAmount);
                System.out.println("HEAL 💉💊🩹 total heal is " + this.healAmount + ", " + target.getName() + " new hp ");
                target.isAlive();
            }else{
                System.out.println("Sorry heal potion only effect Heros");
            }
        }
    }

    public int getHealAmount() {
        return healAmount;
    }
}
