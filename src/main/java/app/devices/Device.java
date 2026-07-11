package app.devices;
import app.interfaces.Switchable;

public abstract class Device implements Switchable {

    protected String id;
    protected String name;
    protected boolean isOn;
    protected double powerConsumption; // watts consumed while ON
    protected String location;         // room name this device belongs to

    public Device(String id, String name, String location, double powerConsumption) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.powerConsumption = powerConsumption;
        this.isOn = false;
    }

    @Override
    public void turnOn() {
        this.isOn = true;
    }

    @Override
    public void turnOff() {
        this.isOn = false;
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    public double getCurrentPowerConsumption() {
        return isOn ? powerConsumption : 0.0;
    }

    // Polymorphism - every device shows status differently
    public abstract String displayStatus();

    // Encapsulation
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPowerConsumption() { return powerConsumption; }
    public void setPowerConsumption(double powerConsumption) { this.powerConsumption = powerConsumption; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}