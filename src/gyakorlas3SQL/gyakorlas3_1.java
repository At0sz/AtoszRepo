package gyakorlas3SQL;

import java.sql.*;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class gyakorlas3_1 {
    // CSERÉLD KI A SAJÁT ÚTVONALADRA!
    private static final String DB_URL = "jdbc:sqlite:E:\\CODING\\sqlbasetraining\\gyakorlas.db";

    public static void main(String[] args) {
        List<NetworkDevice> devices = new ArrayList<>();
        devices.add(new Router("Core-Router-01"));
        devices.add(new NetworkSwitch("Floor-1-Switch"));

        System.out.println("--- Hálózati eszközök regisztrálása ---");

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            System.out.println("[DB] Kapcsolat él.");

            // --- EZ AZ ÚJ RÉSZ: TÁBLA LÉTREHOZÁSA, HA NINCS ---
            Statement setupStmt = conn.createStatement();
            String createTableSql = "CREATE TABLE IF NOT EXISTS tickets (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "device_name TEXT," +
                    "issue_type TEXT," +
                    "priority INTEGER," +
                    "status TEXT)";
            setupStmt.execute(createTableSql);
            System.out.println("[DB] Tábla ellenőrizve/létrehozva.");
            // ------------------------------------------------

            for (NetworkDevice dev : devices) {
                saveDeviceToDb(conn, dev);
            }

            printInventory(conn);

        } catch (SQLException e) {
            System.err.println("Hiba: " + e.getMessage());
        }
    }

    private static void saveDeviceToDb(Connection conn, NetworkDevice dev) throws SQLException {
        String sql = "INSERT INTO tickets (device_name, issue_type, priority, status) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dev.getName());
            pstmt.setString(2, dev.getStatus());
            pstmt.setInt(3, dev.getPriority());
            pstmt.setString(4, "ACTIVE");
            pstmt.executeUpdate();
            System.out.println(">> " + dev.getName() + " mentve az adatbázisba.");
        }
    }

    private static void printInventory(Connection conn) throws SQLException {
        System.out.println("\n--- AKTUÁLIS ADATBÁZIS INVENTORY ---");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tickets");

        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + " | Név: " + rs.getString("device_name"));
        }
    }
}


class Router implements NetworkDevice {
    private String name;

    public Router(String name) {
        this.name = name;
    }

    @Override
    public String getStatus() {
        return "Online - Routing active";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPriority() {
        return 5;
    } // Kritikus
}

class NetworkSwitch implements NetworkDevice {
    private String name;

    public NetworkSwitch(String name) {
        this.name = name;
    }

    @Override
    public String getStatus() {
        return "Online - All ports up";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPriority() {
        return 3;
    }
}

