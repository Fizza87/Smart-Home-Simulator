package app.devices;

public class Fan extends Device {

    private int speed; // 0 - 5

    public Fan(String id, String name, String location, double powerConsumption) {
        super(id, name, location, powerConsumption);
        this.speed = 0;
    }

    public void setSpeed(int speed) {
        if (!isOn) {
            System.out.println("Cannot set speed. " + name + " is OFF.");
            return;
        }
        if (speed < 1 || speed > 5) {
            System.out.println("Invalid speed. Must be between 1 and 5.");
            return;
        }
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void turnOn() {
        super.turnOn();
        if (speed == 0) speed = 1; // default speed
    }

    @Override
    public void turnOff() {
        super.turnOff();
        speed = 0;
    }

    @Override
    public String displayStatus() {
        return "[Fan] " + name + " (" + location + ") - Status: " + (isOn ? "ON" : "OFF")
                + (isOn ? ", Speed: " + speed : "");
    }
}