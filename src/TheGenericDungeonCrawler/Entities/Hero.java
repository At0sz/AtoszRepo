package TheGenericDungeonCrawler.Entities;

import TheGenericDungeonCrawler.GenericInterface.NoManaException;
import TheGenericDungeonCrawler.GenericInterface.Skill;

public class Hero extends Entity implements Combatant {
    private Storage<Item> inventory = new Storage<>();
    private Skill<Entity> specialSkill;
    private int skillCost = 20;

    public Hero(String name, int strength, int hp, int mana, Storage<Item> inventory) {
        super(name, strength, hp, mana);
        this.inventory = inventory;
    }

    public Storage<Item> getInventory() {
        return inventory;
    }

    public void addItemToInventory(Item item) {
        inventory.addItem(item);
    }

    @Override
    public void attack(Entity target) {
        target.setHp(target.getHp() - this.getStrength());
        System.out.print(this.getName() + " hero attacked " + target.getName() + " with force of " + this.getStrength() + " , target ");
        System.out.print(target.getHp() > 0 ? " is alive with : " + target.getHp() + " hp" : " is already died ⚠");
    }

    @Override
    public boolean isAlive() {
        return this.getHp() > 0;
    }


    public void getHpInfo() {
        System.out.print(this.getHp() > 0 ? this.getName() + " is alive, hp: " + this.getHp() : this.getName() + " is already died ⚠ ");
    }

    public void setSpecialSkill(Skill<Entity> skill) {
        this.specialSkill = skill;
    }

    public void useSpecialSkill(Entity target) throws NoManaException {
        if (this.getMana() < skillCost) {
            throw new NoManaException(this.getName() + " kifogyott a manából!");
        }

        this.setMana(this.getMana() - skillCost);
        specialSkill.cast(target);
    }

}
