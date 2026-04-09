package ESportsTournamentManagerProEdition;
import ESportsTournamentManagerProEdition.core.Rank;
import ESportsTournamentManagerProEdition.manager.Tournament;
import ESportsTournamentManagerProEdition.person.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Tournament tournament = new Tournament();

        // Teszt adatok: jók, rosszak, és vegyesek
        List<String> rawData = Arrays.asList(
                // 1. Csapatok létrehozása
                "ADD_TEAM:TeamLiquid,MOBA",
                "ADD_TEAM:G2_Esports,FPS",
                "ADD_TEAM:Felsultek,FIGHTING",

                // 2. Pro Játékosok (Team, Tag, RealName, Rank, Skill, Sponsor)
                "ADD_PRO:TeamLiquid,Faker,Lee Sang-hyeok,DIAMOND,98,RedBull",
                "ADD_PRO:TeamLiquid,Caps,Rasmus Winther,PLATINUM,85,Logitech",
                "ADD_PRO:G2_Esports,NiKo,Nikola Kovac,DIAMOND,99,Gfuel",

                // 3. Hibás GamerTagek (Regex teszt - ezeket el kell kapnia a catch-nek!)
                "ADD_PRO:TeamLiquid,F,Rovid Nev,GOLD,50,NoSponsor",          // Túl rövid
                "ADD_PRO:TeamLiquid,123Gamer,Szam Nev,SILVER,40,NoSponsor",  // Számmal kezdődik
                "ADD_PRO:TeamLiquid,NagyonHosszuGamerTagIde,Hosszu,GOLD,50,NoSponsor", // Túl hosszú

                // 4. Streamerek (Team, Tag, RealName, Rank, Skill, Followers)
                "ADD_STREAMER:TeamLiquid,Shix,Kiss Bela,GOLD,70,500000",
                "ADD_STREAMER:G2_Esports,Shroud,Michael Grzesiek,DIAMOND,95,10000000",

                // 5. Csapat betelt teszt (Már van 3 ember a Liquidben, adjunk hozzá még sokat)
                "ADD_PRO:TeamLiquid,Player4,Teszt Elek,SILVER,30,Acer",
                "ADD_PRO:TeamLiquid,Player5,Low Skill,BRONZE,10,Asus",
                "ADD_PRO:TeamLiquid,Player6,EzMarNemFerBe,GOLD,50,Razer", // TeamFullException!

                // 6. Formátum hibák (Ezeket a sima Exception catch-eli)
                "ADD_TEAM:HibasCsapat", // Nincs elég paraméter
                "ADD_PRO:G2_Esports,Troll,Troll,DIAMOND,nemszam,Sponsor" // NumberFormatException
        );

        System.out.println("--- ADATOK FELDOLGOZÁSA ---");
        tournament.processInput(rawData);

        System.out.println("\n--- TOP 3 JÁTÉKOS (POWER ALAPJÁN) ---");
        List<Player> topPlayers = tournament.getTopPlayers(3);
        topPlayers.forEach(p -> System.out.printf("%s (%s) - Power: %.2f\n",
                p.getGamerTag(), p.getClass().getSimpleName(), p.calculatePower()));

        System.out.println("\n--- JÁTÉKOSOK RANG SZERINT CSOPORTOSÍTVA ---");
        Map<Rank, List<Player>> grouped = tournament.groupPlayersByRank();
        grouped.forEach((rank, players) -> {
            System.out.println(rank + ": " + players.stream()
                    .map(Player::getGamerTag)
                    .toList());
        });

        System.out.println("\n--- MECCSEK INDÍTÁSA ---");
        // Csak hogy lássuk a polimorfizmust működés közben
        topPlayers.forEach(Player::playMatch);
    }
}