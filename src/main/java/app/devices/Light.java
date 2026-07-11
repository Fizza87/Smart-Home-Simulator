package app.devices;
public class Light extends Device {

    private int brightness; // 0 - 100 (%)

    public Light(String id, String name, String location, double powerConsumption) {
        super(id, name, location, powerConsumption);
        this.brightness = 0;
    }

    public void setBrightness(int brightness) {
        if (!isOn) {
            System.out.println("Cannot set brightness. " + name + " is OFF.");
            return;
        }
        if (brightness < 0 || brightness > 100) {
            System.out.println("Invalid brightness value. Must be between 0 and 100.");
            return;
        }
        this.brightness = brightness;
    }

    public int getBrightness() {
        return brightness;
    }

    @Override
    public void turnOn() {
        super.turnOn();
        if (brightness == 0) brightness = 100; // default brightness
    }

    @Override
    public void turnOff() {
        super.turnOff();
        brightness = 0;
    }

    @Override
    public String displayStatus() {
        return "[Light] " + name + " (" + location + ") - Status: " + (isOn ? "ON" : "OFF")
                + (isOn ? ", Brightness: " + brightness + "%" : "");
    }
}