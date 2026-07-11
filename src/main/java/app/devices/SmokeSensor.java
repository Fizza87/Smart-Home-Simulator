package app.devices;
import app.interfaces.Monitorable;

public class SmokeSensor extends Device implements Monitorable {

    private boolean smokeDetected;

    public SmokeSensor(String id, String name, String location, double powerConsumption) {
        super(id, name, location, powerConsumption);
        this.smokeDetected = false;
    }

    // Simulates sensor detecting smoke
    public void detectSmoke(boolean detected) {
        if (!isOn) {
            System.out.println(name + " is OFF, cannot detect smoke.");
            return;
        }
        this.smokeDetected = detected;
    }

    public boolean isSmokeDetected() { return smokeDetected; }

    @Override
    public String getStatusReport() {
        return name + ": " + (smokeDetected ? "SMOKE DETECTED!" : "No smoke detected");
    }

    @Override
    public String displayStatus() {
        return "[SmokeSensor] " + name + " (" + location + ") - Status: " + (isOn ? "ON" : "OFF")
                + (isOn ? ", Alert: " + (smokeDetected ? "SMOKE!" : "Clear") : "");
    }
}