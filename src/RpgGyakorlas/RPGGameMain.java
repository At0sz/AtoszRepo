import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// --- 1. ADATBÁZIS RÉTEG ---
class DatabaseManager {
    private static final String URL = "jdbc:sqlite:rpg_game.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initialize() {
        String sql = "CREATE TABLE IF NOT EXISTS players (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT UNIQUE NOT NULL," + // UNIQUE: ne lehessen két ugyanolyan nevű hős
                "class TEXT," +
                "level INTEGER DEFAULT 1);";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("DB Inicializálási hiba: " + e.getMessage());
        }
    }
}

// --- 2. OBJEKTUM ORIENTÁLT RÉTEG (RPG MODELL) ---
abstract class RPGCharacter {
    protected int id; // Az adatbázisból jön
    protected String name;
    protected String job;
    protected int level;

    public RPGCharacter(int id, String name, String job, int level) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.level = level;
    }

    public void levelUp() {
        this.level++;
        System.out.println(name + " szintet lépett! Új szint: " + level);
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getJob() { return job; }
    public int getLevel() { return level; }
}

class Warrior extends RPGCharacter {
    public Warrior(int id, String name, int level) {
        super(id, name, "Warrior", level);
    }
}

// --- 3. LOGIKAI RÉTEG (REPOSITORY / MENTÉS-BETÖLTÉS) ---
class PlayerRepository {

    // Új karakter mentése (INSERT)
    public static void saveNewPlayer(RPGCharacter p) {
        String sql = "INSERT INTO players(name, class, level) VALUES(?,?,?)";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getName());
            pstmt.setString(2, p.getJob());
            pstmt.setInt(3, p.getLevel());
            pstmt.executeUpdate();
            System.out.println("[SQL] Új hős regisztrálva: " + p.getName());
        } catch (SQLException e) {
            System.out.println("[SQL] Mentési hiba (lehet, hogy már létezik a név?): " + e.getMessage());
        }
    }

    // Meglévő karakter szintjének frissítése (UPDATE)
    public static void updatePlayerLevel(RPGCharacter p) {
        String sql = "UPDATE players SET level = ? WHERE name = ?";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, p.getLevel());
            pstmt.setString(2, p.getName());
            pstmt.executeUpdate();
            System.out.println("[SQL] " + p.getName() + " szintje frissítve az adatbázisban.");
        } catch (SQLException e) {
            System.err.println("[SQL] Update hiba: " + e.getMessage());
        }
    }

    // Mindenki betöltése (SELECT)
    public static List<RPGCharacter> getAllPlayers() {
        List<RPGCharacter> list = new ArrayList<>();
        String sql = "SELECT * FROM players";
        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Warrior(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("level")
                ));
            }
        } catch (SQLException e) {
            System.err.println("[SQL] Betöltési hiba: " + e.getMessage());
        }
        return list;
    }
}

// --- 4. A JÁTÉKMENET (MAIN) ---
public class RPGGameMain {
    public static void main(String[] args) {
        // 1. Táblák létrehozása
        DatabaseManager.initialize();

        // 2. Készítünk egy hőst (0 ID-val, mert még nincs a DB-ben)
        Warrior kratos = new Warrior(0, "Kratos", 15);

        // 3. Elmentjük (ha még nincs bent)
        PlayerRepository.saveNewPlayer(kratos);

        // 4. Szintet lépünk a Java kódban
        kratos.levelUp(); // 15 -> 16

        // 5. Frissítjük az adatbázist is!
        PlayerRepository.updatePlayerLevel(kratos);

        // 6. Ellenőrizzük a végeredményt: töltsünk be mindenkit
        System.out.println("\n--- JELENLEGI HŐSÖK AZ ADATBÁZISBAN ---");
        PlayerRepository.getAllPlayers().forEach(p ->
                System.out.println("ID: " + p.getId() + " | Név: " + p.getName() + " | Szint: " + p.getLevel())
        );
    }
}