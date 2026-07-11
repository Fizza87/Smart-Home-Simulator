package app.automation;

import app.devices.Device;

public class Schedule {

    private String id;
    private Device targetDevice;
    private String action;      // "TURN_ON" or "TURN_OFF"
    private int scheduledHour;  // 0-23
    private boolean executed;

    public Schedule(String id, Device targetDevice, String action, int scheduledHour) {
        this.id = id;
        this.targetDevice = targetDevice;
        this.action = action;
        this.scheduledHour = scheduledHour;
        this.executed = false;
    }

    public boolean isDue(int currentHour) {
        return !executed && currentHour >= scheduledHour;
    }

    public void execute() {
        if (action.equalsIgnoreCase("TURN_ON")) {
            targetDevice.turnOn();
        } else if (action.equalsIgnoreCase("TURN_OFF")) {
            targetDevice.turnOff();
        }
        executed = true;
    }

    public void reset() {
        executed = false;
    }

    public String getId() { return id; }
    public boolean isExecuted() { return executed; }
}