package Gyakorlas2.NetworkDeviceManagementSystem;

import java.util.Scanner;

public class SystemMain {
    public static void main(String[] args) {


        DeviceInventory inventory = new DeviceInventory();
        Scanner scanner = new Scanner(System.in);

        // TODO: Készíts egy egyszerű while(true) menüt:
        // 1. Eszköz hozzáadása
        // 2. Összes eszköz listázása
        // 3. Ping szimuláció (IP alapján online-ra állítás)
        // 4. Kilépés


        System.out.println("Network Manager 1.0 elindult...");

        // Alap eszközök létrehozása (Konstruktor használatával)
        // Sorrend: IP, MAC, Típus, Online állapot
        NetworkDevice router = new NetworkDevice("192.168.1.1", "00:0A:95:9D:68:16", "Router", true);
        NetworkDevice switch1 = new NetworkDevice("192.168.1.2", "00:14:22:01:23:45", "Switch", true);
        NetworkDevice ap1 = new NetworkDevice("192.168.1.50", "00:25:90:FF:EE:11", "Access Point", false);
        NetworkDevice deadServer = new NetworkDevice("10.0.0.5", "BC:EE:7B:00:11:22", "Server", false);

        // Hozzáadás az inventory-hoz
        inventory.addDevice(router);
        inventory.addDevice(switch1);
        inventory.addDevice(ap1);
        inventory.addDevice(deadServer);

        System.out.println("\n--- Rendszer inicializálva: 4 eszköz betöltve ---");


        int choice;
        do {
            System.out.println("\n--- Menu ---\n");

            System.out.println("1. Add Device");
            System.out.println("2. List Devices");
            System.out.println("3. PING");
            System.out.println("4. Turn ONLINE");
            System.out.println("5. Exit");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {

                case 1:
                    System.out.println("Enter Device IP:");
                    String deviceIP = scanner.nextLine();
                    System.out.println("Enter Device MAC Address:");
                    String macAddress = scanner.nextLine();
                    System.out.println("Enter Device Type:");
                    String deviceType = scanner.nextLine();

                    NetworkDevice newDevice = new NetworkDevice(deviceIP, macAddress, deviceType);
                    inventory.addDevice(newDevice);
                    break;

                case 2:
                    inventory.printAllDevices();
                    System.out.println("\n-----------------");
                    System.out.println("ONLINE Eszközök száma: " + inventory.onlineCount());
                    break;

                case 3:
                    System.out.println("Enter Device IP to Ping:");
                    String pingIp = scanner.nextLine();
                    NetworkDevice target = inventory.findByIp(pingIp);

                    if (target != null) {
                        System.out.println("Ping feldolgozás folyamatban...");
                        System.out.println((target.isOnline() ? "Sikeres PING! Az eszköz állapota: ONLINE" : "Sikertelen PING! Az eszköz állapota: OFFLINE"));

                    } else {
                        System.out.println("HIBA: Az eszköz ezzel az IP-vel (" + pingIp + ") nem található a hálózatban!");
                    }
                    System.out.println("--- ENDED PING ---");
                    break;

                case 4:
                    System.out.println("Enter Device IP to set ONLINE:");
                    deviceIP = scanner.nextLine();
                    target = inventory.findByIp(deviceIP);
                    if (target != null) {
                        target.setOnline(true);
                        System.out.println("--- DEVICE IS ONLINE ---");
                    }
                    else{
                        System.out.println("Nem található ilyen eszköz!");

                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice...");
            }


        } while (choice != 5);


    }
}
