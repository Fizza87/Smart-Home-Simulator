package app.model;

import app.devices.Device;
import java.util.ArrayList;
import java.util.List;

public class Room {

    private String id;
    private String name;
    private List<Device> devices; // Composition: Room owns its Devices

    public Room(String id, String name) {
        this.id = id;
        this.name = name;
        this.devices = new ArrayList<>();
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public boolean removeDevice(String deviceId) {
        return devices.removeIf(d -> d.getId().equals(deviceId));
    }

    public Device getDevice(String deviceId) {
        for (Device d : devices) {
            if (d.getId().equals(deviceId)) return d;
        }
        return null;
    }

    public List<Device> getAllDevices() {
        return devices;
    }

    public int getOnlineDeviceCount() {
        int count = 0;
        for (Device d : devices) {
            if (d.isOn()) count++;
        }
        return count;
    }

    public int getOfflineDeviceCount() {
        return devices.size() - getOnlineDeviceCount();
    }

    public double getTotalPowerConsumption() {
        double total = 0;
        for (Device d : devices) {
            total += d.getCurrentPowerConsumption();
        }
        return total;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void displayRoomStatus() {
        System.out.println("Room: " + name + " (" + devices.size() + " devices)");
        for (Device d : devices) {
            System.out.println("   " + d.displayStatus());
        }
    }
}