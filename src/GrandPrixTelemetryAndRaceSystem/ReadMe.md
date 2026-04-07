🏎️ Projekt: Grand Prix Telemetry & Race System
1. Fázis: Az Építőkövek (Enum és Interface)
   Kezd a legkisebb elemekkel, amik a szabályokat diktálják.

TireType (Enum): Készíts egy enumot a gumikeverékeknek! Értékei: SOFT, MEDIUM, HARD, WET.

Követelmény: Minden guminak legyen egy speedModifier (double) tulajdonsága. (Pl. SOFT = 1.2, MEDIUM = 1.0, HARD = 0.8, WET = 0.9). Ehhez kelleni fog egy privát mező, egy konstruktor és egy getter.

PitCapable (Interface): Készíts egy interfészt!

Követelmény: Legyen benne egy void changeTires(TireType newTire) metódus. Ezt majd a boxkiállásnál használjuk.

2. Fázis: A Kivételkezelés (Custom Exception)
   EngineFailureException (Osztály): Készíts egy saját kivételt, ami a RuntimeException-ből származik le.

Követelmény: A konstruktora várjon egy üzenetet (String), és adja át az ősosztályának (super()). Ezt akkor fogjuk eldobni, ha egy autó túlhajtja magát.

3. Fázis: Az Öröklődési Fa (Abstract és Concrete)
   Itt jön a polimorfizmus.

RaceVehicle (Absztrakt Osztály): Ez minden jármű őse.

Mezők (protected): String pilotName, int durability (0-tól 100-ig, induláskor legyen 100), double baseSpeed, és egy TireType currentTire.

Konstruktor: Várja paraméterben a pilóta nevét, az alapsebességet és a kezdő gumit. A durability-t fixen állítsa be 100-ra.

Absztrakt metódus: double calculateActualSpeed(); (Minden jármű máshogy számolja a végsebességét).

Konkrét metódus: void degradeCondition(int amount). Ez csökkenti a durability-t. Szigorú szabály: Ha a durability 0 alá csökkenne, ne engedje, hanem dobja el az általad írt EngineFailureException-t azzal az üzenettel, hogy "[Pilóta neve] motorja felrobbant!".

Getterek: Írj gettert minden mezőhöz.

FormulaCar (Konkrét Osztály): Származtass le a RaceVehicle-ből ÉS implementáld a PitCapable interfészt!

Megvalósítás 1: A calculateActualSpeed() szorozza fel a baseSpeed-et a jelenlegi gumi speedModifier értékével, és ezt adja vissza.

Megvalósítás 2: A changeTires() metódus (az interfészből) írja felül a jelenlegi gumit az újra, és írja ki a konzolra: "[Pilóta neve] kiállt a boxba, új gumi: [Új gumi típusa]".

4. Fázis: Az Agy (A Manager / Maker Osztály)
   RaceDirector (Osztály): Ő fogja össze a versenyt. Nincs Singleton! Sima osztály lesz.

Mező: Egy privát List<RaceVehicle>, ami kezdetben üres (pl. roster).

Metódus: void registerVehicle(RaceVehicle vehicle) - hozzáadja az autót a listához.

Stream 1: List<RaceVehicle> getOperationalVehiclesSortedBySpeed()

Szűrd ki azokat az autókat, amiknek a durability-je nagyobb, mint 0.

Rendezd őket a tényleges sebességük (calculateActualSpeed()) alapján csökkenő sorrendbe.

Stream 2: Map<TireType, List<RaceVehicle>> groupVehiclesByTire()

Csoportosítsd a járműveket az éppen rajtuk lévő gumi alapján.

Stream 3: double getTopSpeedOfAllTime()

Keresd meg a listában az abszolút legmagasabb tényleges sebességet, és add vissza. (Tipp: a .mapToDouble() metódus után tudsz maximumot keresni. Ha a lista üres, adj vissza 0.0-t a .orElse() segítségével).

Stream 4: String getPilotNamesInDangerZone()

Szűrd ki azokat, akiknek a durability-je 30 vagy az alatt van.

Gyűjtsd ki csak a pilóták nevét.

Fűzd össze a neveket egyetlen String-gé, vesszővel elválasztva (Tipp: Collectors.joining(", ")).

5. Fázis: A Szimuláció (Main)
   Példányosíts egy RaceDirector-t.

Hozz létre 4-5 FormulaCar-t különböző pilótákkal, alapsebességekkel és gumikkal.

Regisztráld őket a RaceDirector-ba.

Szimulálj kopást: Hívd meg pár autónál a degradeCondition-t (egyiknél direkt vidd 0 alá, hogy teszteld a try-catch blokkban az Exception elkapását!).

Hívj meg egy boxkiállást az egyik autónál (changeTires).

Nyomtasd ki a Stream API metódusok eredményeit (pl. kik vannak veszélyzónában, mennyi a végsebesség, stb.).

🧠 Miért zseniális ez a feladat számodra?
Nincs benne "varázslat", csak tiszta, üzleti logikára épülő backend architektúra. Meg kell tervezned, hogy a metódusok hogyan kommunikálnak egymással, és a Stream API segítségével komplex riportokat kell generálnod a rendszer aktuális állapotáról. Az, hogy magadnak kell legépelned a metódusok szignatúráit a leírás alapján, hihetetlenül erősíti az absztrakciós készségedet.