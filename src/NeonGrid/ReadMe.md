Feladat: NeonGrid – A Kibertér Menedéke
A NeonGrid egy zárt, digitális hálózat, ahol különböző entitások (felhasználók és programok) bérelnek szerverparkokat. Minden entitás fix infrastruktúrával rendelkezik, de az erőforrás-fogyasztásuk és a bevételük eltérő.

Az Entitások (Lakók)
Solo Runner (Egyedülálló hacker):

1 db Standard Szerver

1 db Terminál

Alap hálózati fenntartási költség (rezsi): 15

Dev Syndicate (Fejlesztő páros):

2 db Standard Szerver

2 db Terminál

1 db Kávéfőző (digitális szimuláció)

Alap hálózati fenntartási költség (rezsi): 25

Rogue AI (Szabadúszó MI):

1 db Felhő Példány (Cloud Instance)

Alap hálózati fenntartási költség (rezsi): 5

OPCIONÁLIS: Corp Exec (Céges fejes drónokkal):

1 db Luxus Szerver

1 db Terminál

N darab Védelmi Drón (a drónok karbantartási költsége drónonként eltérő)

Alap hálózati fenntartási költség (rezsi): 40

Költségek és Bevételek
Minden eszköznek van egy fix energiafogyasztása (Te döntöd el az értékeket, pl. Kávéfőző = 0.5, Terminál = 1.0, Szerver = 2.0, Felhő = 3.5, Luxus Szerver = 5.0).

Minden entitásnak van bevétele (Kredit):

A Runner "Bounty"-t (jutalmat) kap.

A Dev Syndicate tagjai fizetést (salary) kapnak.

Az AI kriptót bányász (crypto yield).

Idő múlása:
Minden 3. bemeneti sor (parancs) jelent egy "Ciklust" (Tick). Amikor lejár egy ciklus, a hálózat minden lakója megkapja a bevételét a közös budgetjébe.

Bemeneti parancsok
A parancsok soronként érkeznek a standard inputról.

status: Írd ki a NeonGrid aktuális teljes energiafogyasztását (eszközök + alap rezsik összege).

reboot: Vonj le minden költséget (rezsi + eszközök fogyasztása, opcionálisnál + drónok ára) az entitások budgetjéből. Ha egy entitás budgetje negatívba menne, a hálózat törli (Delete), és a továbbiakban nem képezi a NeonGrid részét.

quit: A program leáll. Írd ki a hálózatban maradt aktív entitások (és opcionálisan a drónok) számát.

Regisztrációs parancsok:

SoloRunner(bounty)

DevSyndicate(salary1, salary2)

RogueAI(cryptoYield)

(Opcionális) CorpExec(salary) Drone(cost1) Drone(cost2) ... Drone(costN)

Példa a működésre
Bemenet:

Plaintext
DevSyndicate(30, 35)
SoloRunner(40)
RogueAI(15) -> Itt telik le a 3. sor, mindenki megkapja a bevételét!
status
reboot -> Költségek levonása az eddig gyűjtött budgetből
quit
Alap struktúra vázlat (Outline)
Ahogy ígértem, itt egy gyors, nyelvfüggetlen OOP tervezési mankó az induláshoz, amit a kedvenc IDE-dben kidolgozhatsz:

1. Interfészek és Alaposztályok:

IDevice (Eszközök interfésze): getEnergyCost() metódussal.

Entity (Absztrakt alaposztály):

Mezők: budget, baseNetworkCost, List<IDevice> devices.

Metódusok: addIncome(), calculateTotalCost(), payBills() (visszatérési értéke egy boolean: túlélte-e vagy törölni kell).

2. Leszármazottak (Entitások):

SoloRunner, DevSyndicate, RogueAI az Entity-ből származnak le. Konstruktorban példányosítják és adják hozzá a saját fix eszközeiket a listájukhoz.

3. Eszköz osztályok:

Server, Terminal, CloudInstance, stb. implementálják az IDevice-t.

4. A Fő Rendszer (Manager/Engine):

Egy NeonGridManager osztály, ami tárol egy List<Entity> kollekciót.

Tartalmaz egy cycleCounter változót, ami minden beolvasott parancsnál nő. Ha cycleCounter % 3 == 0, akkor iterál az entitásokon és meghívja az addIncome()-ot.