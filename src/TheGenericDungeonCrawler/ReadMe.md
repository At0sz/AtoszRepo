🏰 Projekt Neve: "The Generic Dungeon Crawler"
A cél egy olyan rendszer, ahol különböző típusú hősök (Warrior, Mage) küzdenek szörnyek ellen, közben tárgyakat gyűjtenek a generikus raktárukba, és speciális képességeket használnak.

📋 A Küldetés (Task List)
1. A Karakter Rendszer (Abstrakció & Öröklődés)
   Hozz létre egy Entity absztrakt osztályt (name, hp, mana, strength).

Legyen egy Combatant interfészed attack(Entity target) és isAlive() metódusokkal.

Készíts Hero és Monster osztályokat. A Hero-nak legyen egy Storage<Item> raktára (amit már megírtál!).

2. Képesség Rendszer (Generikus Interfész)
   Készíts egy Skill<T extends Entity> generikus interfészt egy void cast(T target) metódussal.

Írj egy Fireball osztályt (ami Entity-t sebez) és egy Heal osztályt (ami csak Hero-t tud gyógyítani).

3. Harci Motor (Stream & Logika)
   Készíts egy BattleArena osztályt, ami két listát vár: List<Hero> és List<Monster>.

Írj egy startMassacre() metódust:

Használj Streamet a leggyengébb (legkevesebb HP-jú) szörny kiválasztásához a hősök támadásakor.

Használj Streamet a halott szörnyek eltávolításához a kör végén (removeIf).

4. Exception Handling (A technikai csavar)
   Hozz létre egy saját NoManaException-t (checked exception).

Ha egy hős képességet használna, de nincs elég manája, dobja el ezt a kivételt, és a BattleArena kezelje le (írja ki: "A hős kifáradt, sima ütéssel próbálkozik!").

🏗️ Az Architektúra Vázlata (Hogy el tudj indulni)
Java
// Saját hibaüzenet
class NoManaException extends Exception {
public NoManaException(String message) { super(message); }
}

// Generikus képesség
interface Skill<T> {
void execute(T target) throws NoManaException;
}

// A Hős, akinek már van raktára
class Hero extends Entity implements Combatant {
private Storage<Item> backpack = new Storage<>();
private Skill<Entity> specialSkill;

    // ... konstruktor, attack, stb.
}
⚔️ A Boss Fight "Hard Mode" Extrái (Ha marad energiád):
Loot System: Ha egy szörny meghal, generálj egy random Weapon-t vagy Potion-t, és próbáld meg betenni a hős Storage-ába.

Comparator: A hősök a kör elején rendeződjenek sebesség (egy új stat) szerint csökkenő sorrendbe Stream segítségével.

Wildcards: Írj egy metódust, ami statisztikát készít: printHealth(List<? extends Entity> entities) – ez fogadja el a hősök és a szörnyek listáját is!

🛡️ Gemini technikus tipp:
Ne próbáld meg egyszerre az egészet megírni! Haladj így:

Entity + Hero/Monster (Alap harc).

Skill interfész + Fireball (Képességek).

NoManaException beépítése.

Végül a BattleArena a Stream-es szűrésekkel.