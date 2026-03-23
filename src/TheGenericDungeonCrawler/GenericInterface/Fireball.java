package TheGenericDungeonCrawler.GenericInterface;

import TheGenericDungeonCrawler.Entities.Entity;
import TheGenericDungeonCrawler.Entities.Monster;

public class Fireball implements Skill<Entity> {
    private int damage;

    public Fireball(int damage) {
        this.damage = damage;
    }

    @Override
    public void cast(Entity target) throws NoManaException {
        {
            if (target instanceof Monster) {
                target.setHp(target.getHp() - damage);
                System.out.println("Hit 💥 damage was " + this.damage + ", " + target.getName() + " new hp ");
                ((Monster) target).getHpInfo();
            }
        }
    }
}
