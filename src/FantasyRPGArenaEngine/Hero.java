package FantasyRPGArenaEngine;

abstract class Hero implements UltimateCaster {
    protected String name;
    protected int hp;
    protected Role role;
    protected boolean isAlive;

    public Hero(String name, int hp, Role role) {
        this.name = name;
        this.hp = hp;
        this.role = role;
        this.isAlive = true;
    }

    public void takeDamage(int amount){
        this.hp -= amount;
        if(this.hp <= 0){
            this.isAlive = false;
        }
    }

    // Absztrakt metódus: minden alosztálynak kötelező megírnia!
    public abstract void basicAttack(Hero target);

    public String getName() { return name; }
    public int getHp() { return hp; }
    public Role getRole() { return role; }
    public boolean isAlive() { return isAlive; }

    @Override
    public String toString() {
        return String.format("[%s] %s - HP: %d (%s)", role, name, hp, isAlive ? "Alive" : "Dead");
    }
}
