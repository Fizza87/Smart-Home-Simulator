package app.devices;
import app.interfaces.Lockable;

public class Door extends Device implements Lockable {

    private boolean locked;

    public Door(String id, String name, String location, double powerConsumption) {
        super(id, name, location, powerConsumption);
        this.locked = true; // locked by default
    }

    @Override
    public void turnOn() {
        // For Door: ON = OPEN
        if (locked) {
            System.out.println(name + " is locked. Unlock it before opening.");
            return;
        }
        super.turnOn();
    }

    @Override
    public void turnOff() {
        // For Door: OFF = CLOSED
        super.turnOff();
    }

    @Override
    public void lock() {
        if (isOn) {
            System.out.println("Cannot lock " + name + " while it is open.");
            return;
        }
        locked = true;
    }

    @Override
    public void unlock() {
        locked = false;
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public String displayStatus() {
        return "[Door] " + name + " (" + location + ") - Status: " + (isOn ? "OPEN" : "CLOSED")
                + ", Lock: " + (locked ? "LOCKED" : "UNLOCKED");
    }
}