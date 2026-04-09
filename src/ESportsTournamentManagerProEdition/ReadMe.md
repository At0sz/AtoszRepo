A téma: 🎮 E-Sports Tournament Manager (Pro Edition)

Ebben a feladatban egy e-sport bajnokság backend logikáját kell leprogramoznod, ahol játékosokat, csapatokat és meccseket menedzselünk.

1. Csomagszerkezet és Alapok
   esports.exceptions csomag

Hozz létre két saját Checked Exception-t:

TeamFullException: Dobd el, ha egy csapatba a maximálisnál (5 fő) több embert akarnak felvenni.

InvalidGamerTagException: Dobd el, ha a játékos beceneve nem megy át a validáción.

esports.core csomag

GameType (Enum): Lehetséges értékek: MOBA, FPS, BATTLE_ROYALE, FIGHTING.

Rank (Enum): Lehetséges értékek: BRONZE, SILVER, GOLD, PLATINUM, DIAMOND. Mindegyikhez tartozzon egy privát multiplier (szorzó) érték (pl. Bronze: 1.0, Diamond: 5.0), amit egy getterrel el lehet érni.

Competitor (Interface): Tartalmazzon egy double calculatePower() metódust. Ezt minden játékosnak és csapatnak implementálnia kell.

2. A Szereplők (Polimorfizmus)
   esports.person.Player (Absztrakt osztály)
   Valósítsa meg a Competitor interfészt.

Példányváltozók: String realName, String gamerTag, Rank rank, int baseSkill (1-100 közötti érték).

Validáció a konstruktorban: A gamerTag csak betűket és számokat tartalmazhat, legalább 4, legfeljebb 12 karakter hosszú, és betűvel kell kezdődnie (ide kell a Regex!). Ha nem felel meg, dobjon InvalidGamerTagException-t.

Absztrakt metódus: public abstract void playMatch();

esports.person.ProGamer (Konkrét osztály, a Player leszármazottja)

Plusz mező: String sponsor.

calculatePower() implementáció: baseSkill * rank.getMultiplier() * 1.2 (a profik kapnak 20% bónuszt).

A playMatch() írja ki a konzolra: "A(z) [sponsor] által támogatott [gamerTag] belépett a szerverre."

esports.person.Streamer (Konkrét osztály, a Player leszármazottja)

Plusz mező: int followers.

calculatePower() implementáció: baseSkill * rank.getMultiplier().

A playMatch() írja ki: "Sziasztok chat! [gamerTag] vagyok, csapassuk!"

3. A Csapat és az Adatstruktúrák
   esports.team.Team (Osztály)
   Valósítsa meg a Competitor interfészt.

Példányváltozók: String teamName, GameType type, List<Player> roster.

Metódusok:

void addPlayer(Player p): Adja hozzá a listához a játékost. Ha a lista mérete eléri az 5-öt, dobjon TeamFullException-t. Csak olyan játékost lehessen hozzáadni, aki még nincs benne (használj contains ellenőrzést, amihez a Player osztályban felül kell írnod az equals és hashCode metódusokat a gamerTag alapján!).

calculatePower() implementáció: A csapat ereje a tagok erejének átlaga, megszorozva a tagok számával.

4. A Menedzser (Stream API és Fájl/Input feldolgozás)
   esports.manager.Tournament (Osztály)

Adatszerkezet: Map<String, Team> teams (A kulcs a csapat neve).

Metódusok:

void processInput(List<String> commands): Hasonló a bankos feladathoz, de itt egyből egy listát kapsz Stringekből.

Formátum: ADD_TEAM:TeamLiquid,MOBA vagy ADD_PRO:TeamLiquid,Faker,Lee Sang-hyeok,DIAMOND,98,RedBull.

Itt kell elkapni és kezelni a beolvasáskor keletkező egyedi Exception-öket úgy, hogy a feldolgozás ne álljon le hibás sornál!

List<Player> getTopPlayers(int limit): Kizárólag Stream API-val! Gyűjtse össze az összes csapat összes játékosát, rendezze őket csökkenő sorrendbe a calculatePower() alapján, és adja vissza a legjobb limit darabot.

Map<Rank, List<Player>> groupPlayersByRank(): Kizárólag Stream API-val! (Segítség: Collectors.groupingBy). Csoportosítsa az összes regisztrált játékost a rangjuk alapján.

5. A Main tesztkörnyezet
   main.App (Osztály)
   Ide jöhet a hardkódolt input lista (mint a bankosnál), amit ráküldesz a Tournament osztály processInput metódusára. A végén írasd ki a Stream API-s lekérdezések eredményét, hogy lásd, jól működik-e a szűrés és a csoportosítás.