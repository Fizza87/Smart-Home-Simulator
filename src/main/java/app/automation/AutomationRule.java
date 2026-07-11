package app.automation;

import app.devices.Device;
import app.devices.MotionSensor;
import app.devices.SmokeSensor;

public class AutomationRule {

    private String id;
    private String description;
    private Device triggerDevice;     // device whose state is checked (e.g. MotionSensor)
    private String conditionType;     // "MOTION_DETECTED" or "SMOKE_DETECTED"
    private int timeConditionHour;    // -1 = no time condition, else hour (0-23)
    private Device targetDevice;      // device the action applies to
    private String actionType;        // "TURN_ON" or "TURN_OFF"
    private boolean enabled;

    public AutomationRule(String id, String description, Device triggerDevice, String conditionType,
                           int timeConditionHour, Device targetDevice, String actionType) {
        this.id = id;
        this.description = description;
        this.triggerDevice = triggerDevice;
        this.conditionType = conditionType;
        this.timeConditionHour = timeConditionHour;
        this.targetDevice = targetDevice;
        this.actionType = actionType;
        this.enabled = true;
    }

    public boolean evaluate(int currentHour) {
        if (!enabled) return false;

        boolean triggerConditionMet = checkTriggerCondition();
        boolean timeConditionMet = (timeConditionHour == -1) || (currentHour >= timeConditionHour);

        return triggerConditionMet && timeConditionMet;
    }

    private boolean checkTriggerCondition() {
        if (triggerDevice == null || conditionType == null) return true;

        if (triggerDevice instanceof MotionSensor sensor && conditionType.equals("MOTION_DETECTED")) {
            return sensor.isMotionDetected();
        }
        if (triggerDevice instanceof SmokeSensor sensor && conditionType.equals("SMOKE_DETECTED")) {
            return sensor.isSmokeDetected();
        }
        return false;
    }

    public void execute() {
        if (targetDevice == null) return;

        if (actionType.equalsIgnoreCase("TURN_ON")) {
            targetDevice.turnOn();
        } else if (actionType.equalsIgnoreCase("TURN_OFF")) {
            targetDevice.turnOff();
        }
    }

    public String getId() { return id; }
    public String getDescription() { return description; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}