package app.devices;
import app.interfaces.Monitorable;

public class MotionSensor extends Device implements Monitorable {

    private boolean motionDetected;

    public MotionSensor(String id, String name, String location, double powerConsumption) {
        super(id, name, location, powerConsumption);
        this.motionDetected = false;
    }

    public void detectMotion(boolean detected) {
        if (!isOn) {
            System.out.println(name + " is OFF, cannot detect motion.");
            return;
        }
        this.motionDetected = detected;
    }

    public boolean isMotionDetected() { return motionDetected; }

    @Override
    public String getStatusReport() {
        return name + ": " + (motionDetected ? "Motion Detected" : "No Motion");
    }

    @Override
    public String displayStatus() {
        return "[MotionSensor] " + name + " (" + location + ") - Status: " + (isOn ? "ON" : "OFF")
                + (isOn ? ", Motion: " + (motionDetected ? "YES" : "NO") : "");
    }
}