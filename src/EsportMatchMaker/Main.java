package EsportMatchMaker;

public class Main {
    public static void main(String[] args) {
        // Singleton példány lekérése
        MatchMaker mm = MatchMaker.getInstance();

        // Új lobby-k létrehozása és beszúrása (direkt összevissza ID-kkal)
        mm.createAndInsertLobby(new GameLobby(50));
        mm.createAndInsertLobby(new GameLobby(10));
        mm.createAndInsertLobby(new GameLobby(30));

        // Legjobb lobby keresése üresen
        GameLobby best = mm.findBestAvailableLobby();
        System.out.println("Legjobb üres lobby: " + best);

        // Keressük meg a 30-ast binárisan és adjunk hozzá játékosokat
        GameLobby lobby30 = mm.findLobbyById(30);
        if (lobby30 != null) {
            lobby30.addPlayer();
            lobby30.addPlayer();
            System.out.println("30-as lobby frissítve: " + lobby30);
        }

        // Legjobb lobby keresése újra (most a 30-asnak kell lennie, mert abban vannak a legtöbben)
        System.out.println("Legjobb lobby most: " + mm.findBestAvailableLobby());
    }
}