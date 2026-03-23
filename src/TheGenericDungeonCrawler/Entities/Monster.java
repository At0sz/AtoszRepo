package TheGenericDungeonCrawler.Entities;

public class Monster extends Entity implements Combatant {

    public Monster(String name, int strength, int hp, int mana) {
        super(name, strength, hp, mana);
    }

    @Override
    public void attack(Entity target) {
        target.setHp(target.getHp() - this.getStrength());
        System.out.print(this.getName()+" monster attacked "+ target.getName()+ " with force of "+ this.getStrength()+ " , target ");
        System.out.print(target.getHp() > 0 ?" is alive with : " + target.getHp()+" hp":" is already died ⚠");
    }

    @Override
    public boolean isAlive() {
        return this.getHp() > 0;
    }


    public void getHpInfo() {
        System.out.print(this.getHp() > 0 ? this.getName()+" is alive, hp: " + this.getHp(): this.getName()+ " is already died ⚠ ");
    }
}
