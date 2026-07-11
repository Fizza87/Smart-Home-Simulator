package app.devices;
import app.interfaces.Monitorable;

public class Camera extends Device implements Monitorable {

    private boolean recording;
    private String resolution;

    public Camera(String id, String name, String location, double powerConsumption, String resolution) {
        super(id, name, location, powerConsumption);
        this.resolution = resolution;
        this.recording = false;
    }

    @Override
    public void turnOn() {
        super.turnOn();
        recording = true;
    }

    @Override
    public void turnOff() {
        super.turnOff();
        recording = false;
    }

    public boolean isRecording() { return recording; }
    public String getResolution() { return resolution; }

    @Override
    public String getStatusReport() {
        return name + " is " + (recording ? "recording at " + resolution : "not recording");
    }

    @Override
    public String displayStatus() {
        return "[Camera] " + name + " (" + location + ") - Status: " + (isOn ? "ON" : "OFF")
                + (isOn ? ", Recording: " + resolution : "");
    }
}