package app.devices;

public class AirConditioner extends Device {

    private int temperature; // Celsius
    private String mode;     // COOL, HEAT, FAN

    public AirConditioner(String id, String name, String location, double powerConsumption) {
        super(id, name, location, powerConsumption);
        this.temperature = 24;
        this.mode = "COOL";
    }

    public void setTemperature(int temperature) {
        if (!isOn) {
            System.out.println("Cannot set temperature. " + name + " is OFF.");
            return;
        }
        if (temperature < 16 || temperature > 30) {
            System.out.println("Invalid temperature. Must be between 16 and 30.");
            return;
        }
        this.temperature = temperature;
    }

    public void setMode(String mode) {
        if (!isOn) {
            System.out.println("Cannot set mode. " + name + " is OFF.");
            return;
        }
        this.mode = mode;
    }

    public int getTemperature() { return temperature; }
    public String getMode() { return mode; }

    @Override
    public String displayStatus() {
        return "[AirConditioner] " + name + " (" + location + ") - Status: " + (isOn ? "ON" : "OFF")
                + (isOn ? ", Temp: " + temperature + "C, Mode: " + mode : "");
    }
}