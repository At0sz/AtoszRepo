🚦 A Feladat: SmartCity Traffic Manager
A cél egy olyan rendszer, ami kezeli a városba behajtó járműveket, kiszámolja az útdíjat a környezetvédelmi besorolás alapján, és kezeli a "bérleteket".

1. Jármű Hierarchia (Abstract Class & Inheritance)
   Hozz létre egy Vehicle absztrakt osztályt:

Mezők: String licensePlate, int emissionLevel (1-5), boolean hasEPass.

Absztrakt metódus: double calculateToll().

Készíts belőle három leszármazottat:

Car: Fix alapdíj (pl. 1000 Ft).

Truck: Alapdíj * súlyszorzó (legyen egy int weight mezője).

Bus: Alapdíj, de ha több mint 20 férőhelyes, 50% kedvezményt kap (legyen int capacity mezője).

2. Útdíj Logika (A "Cifrázás")
   Az útdíj számításánál a Vehicle szintjén legyen egy közös logika:

Ha emissionLevel == 5 (elektromos), az útdíj 0 Ft.

Ha hasEPass == true, 20% kedvezmény.

Minden más esetben az alapdíj érvényes.

3. CityGate Osztály (A "Manager")
   Ez az osztály tárolja a városban tartózkodó járműveket egy List<Vehicle>-ben.

enterCity(Vehicle v): Ha a jármű rendszáma már bent van, dobjon egy egyedi VehicleAlreadyInCityException-t!

exitCity(String licensePlate): Távolítsa el a járművet. Ha nincs bent, dobjon VehicleNotFoundException-t!

getTotalRevenue(): Stream API-val számold ki az összes bent lévő jármű aktuális útdíjának összegét!

4. Statisztika (Stream API gyakorlás)
   Írj egy printTrafficReport() metódust, ami:

Kilistázza a rendszámokat ABC sorrendben.

Megmutatja a legmagasabb útdíjat fizető járművet.

Kiszűri azokat a járműveket, amik "szennyezőek" (emissionLevel < 3).

🛠️ Kezdő vázlat az IntelliJ-hez
Hogy ne a nulláról kelljen gépelned az unalmas részt:

Java
public abstract class Vehicle {
private String licensePlate;
private int emissionLevel;
private boolean hasEPass;

    // Konstruktor, Getterek...

    public abstract double getBasePrice();

    public double calculateToll() {
        if (emissionLevel == 5) return 0;
        double price = getBasePrice();
        if (hasEPass) price *= 0.8;
        return price;
    }
}