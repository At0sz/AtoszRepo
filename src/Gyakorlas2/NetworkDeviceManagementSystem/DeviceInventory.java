package Gyakorlas2.NetworkDeviceManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class DeviceInventory {
    private List<NetworkDevice> devices = new ArrayList<>();

    public void addDevice(NetworkDevice device) {
        // TODO: Add hozzá a listához

        devices.add(device);
    }

    public void printAllDevices() {
        // TODO: For-each ciklussal írasd ki az összes eszközt

        for (NetworkDevice device : devices) {
            System.out.println(device);
        }
    }

    public NetworkDevice findByIp(String ip) {
        // TODO: Keress meg egy eszközt IP alapján (Clean Code tipp: használj értelmes változónevet a ciklusban!)

        return devices.stream()
                .filter(i -> i.getIpAddress().equals(ip))
                .findFirst().orElse(null);
    }

    public long onlineCount() {
        return devices.stream()
                .filter(NetworkDevice::isOnline)
                .count();
    }
}
