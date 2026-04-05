package EsportMatchMaker;

class MatchMaker {

    // --- 1. SINGLETON MINTA ---
    private static MatchMaker instance;

    private MatchMaker() {
        // Privát konstruktor, kívülről nem hívható
    }

    public static MatchMaker getInstance() {
        if (instance == null) {
            instance = new MatchMaker();
        }
        return instance;
    }

    // A meccsek tárolója (kezdetben üres)
    private GameLobby[] lobbies = new GameLobby[0];

    // --- 2. RENDEZETT BESZÚRÁS (Dinamikus tömbnövelés) ---
    public void createAndInsertLobby(GameLobby newLobby) {
        GameLobby[] newArray = new GameLobby[lobbies.length + 1];
        int i = 0;

        // 1. lépés: Régi elemek másolása, amíg a meglévő ID kisebb az újnál
        while (i < lobbies.length && lobbies[i].getMatchId() < newLobby.getMatchId()) {
            newArray[i] = lobbies[i];
            i++;
        }

        // 2. lépés: Az új lobby beszúrása a felszabadult/megtalált helyre
        newArray[i] = newLobby;

        // 3. lépés: A maradék (nagyobb ID-jú) elemek átmásolása eggyel eltolva
        while (i < lobbies.length) {
            newArray[i + 1] = lobbies[i];
            i++;
        }

        // A régi tömb hivatkozását lecseréljük az újra
        lobbies = newArray;
    }

    // --- 3. BINÁRIS KERESÉS (Objektumokon) ---
    public GameLobby findLobbyById(int matchId) {
        int low = 0;
        int high = lobbies.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (lobbies[mid].getMatchId() == matchId) {
                return lobbies[mid];
            } else if (lobbies[mid].getMatchId() < matchId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null; // Ha nem találtuk meg a while ciklusban
    }

    // --- 4. LEGJOBB LOBBY KERESÉSE (Lineáris maximumkeresés) ---
    public GameLobby findBestAvailableLobby() {
        GameLobby bestLobby = null;

        for (int i = 0; i < lobbies.length; i++) {
            GameLobby current = lobbies[i];

            // Ha a lobby már elindult, VAGY tele van (10 fő), akkor ugorjuk át (continue)
            if (current.isStarted() || current.getPlayerCount() >= 10) {
                continue;
            }

            // Ha még egyáltalán nincs 'bestLobby' beállítva,
            // VAGY a 'current' lobbyban többen vannak, mint a 'bestLobby'-ban,
            // akkor lecseréljük a 'bestLobby'-t a 'current'-re.
            if (bestLobby == null || current.getPlayerCount() > bestLobby.getPlayerCount()) {
                bestLobby = current;
            }
        }

        return bestLobby; // Visszaadja a legjobbat, vagy null-t, ha nincs elérhető
    }
}