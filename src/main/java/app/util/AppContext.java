package app.util;

import app.model.House;
import app.model.Room;

public class AppContext {

    private static House house;
    private static Room selectedRoom;

    private AppContext() {}

    public static House getHouse() {
        if (house == null) {
            house = new House("H1", "My Smart Home");
        }
        return house;
    }

    public static Room getSelectedRoom() {
        return selectedRoom;
    }

    public static void setSelectedRoom(Room room) {
        selectedRoom = room;
    }
}
