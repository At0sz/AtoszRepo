package FantasyRPGArenaEngine;

class Assassin extends Hero {
    public Assassin(String name) {
        super(name,90,Role.DPS);
    }

    @Override
    public void basicAttack(Hero target) {
        target.takeDamage(40);
    }

    @Override
    public void castUltimate(Hero target) {
        if (target.getHp() <= 30) {
            target.takeDamage(target.getHp());
        }
    }

}
