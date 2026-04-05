package FantasyRPGArenaEngine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArenaEngine {
    private static ArenaEngine instance;

    private ArenaEngine() {
    }

    public static ArenaEngine getInstance(){
        if(instance == null){
            instance = new ArenaEngine();
        }
        return instance;
    }

    // A hősök listája
    private List<Hero> roster = new ArrayList<>();

    public void addHero(Hero h) {
        roster.add(h);
    }

    // --- STREAM API FELADATOK ---

    // 1. Csak az élő hősök listázása
    public List<Hero> getAliveHeroes() {
        return roster.stream()
                .filter(Hero::isAlive)
                .toList();
    }

    // 2. Csoportosítás szerepkör (Role) alapján
    public Map<Role, List<Hero>> groupHeroesByRole() {
        return roster.stream()
                .collect(Collectors.groupingBy(Hero::getRole));
    }

    // 3. A legerősebb élő hős megtalálása HP alapján
    public Hero getHealthiestAliveHero() {
        return roster.stream()
                .filter(Hero::isAlive)
                .max(Comparator.comparingInt(Hero::getHp))
                .orElse(null);
    }
}
