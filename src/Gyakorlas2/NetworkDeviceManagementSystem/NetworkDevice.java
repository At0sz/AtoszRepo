package Gyakorlas2.NetworkDeviceManagementSystem;

public class NetworkDevice {
    private String ipAddress;
    private String macAddress;
    private String deviceType; // pl. Router, Switch, AP
    private boolean isOnline;

    // TODO: Generálj konstruktort (Alt+Insert az IntelliJ-ben!)
    // TODO: Generálj Gettereket és Settereket
    // TODO: Írj egy szép toString() metódust a kiíratáshoz

    public NetworkDevice(String ipAddress, String macAddress, String deviceType) {
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.deviceType = deviceType;
        this.isOnline = false;
    }
    public NetworkDevice(String ipAddress, String macAddress, String deviceType, boolean isOnline) {
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.deviceType = deviceType;
        this.isOnline = isOnline;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    @Override
    public String toString() {
        return
                "NetworkDevice" +
                " ipAddress=" + ipAddress + '\'' +
                " macAddress=" + macAddress + '\'' +
                " deviceType=" + deviceType + '\'' +
                " status=" + (isOnline ? "ONLINE" : "OFFLINE");
    }
}
