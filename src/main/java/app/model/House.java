package app.model;

import java.util.ArrayList;
import java.util.List;

public class House {

    private String id;
    private String name;
    private List<Room> rooms;   // Composition: House owns Rooms
    private List<User> users;   // Aggregation: Users can exist independently of House

    public House(String id, String name) {
        this.id = id;
        this.name = name;
        this.rooms = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public boolean removeRoom(String roomId) {
        // Composition: removing Room removes all its Devices too, since they only lived inside it
        return rooms.removeIf(r -> r.getId().equals(roomId));
    }

    public Room findRoom(String roomId) {
        for (Room r : rooms) {
            if (r.getId().equals(roomId)) return r;
        }
        return null;
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    public void addUser(User user) {
        // Aggregation: House just references the User, doesn't own its lifecycle
        users.add(user);
    }

    public void removeUser(String username) {
        users.removeIf(u -> u.getUsername().equals(username));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public int getTotalRooms() {
        return rooms.size();
    }

    public int getTotalDevices() {
        int total = 0;
        for (Room r : rooms) total += r.getAllDevices().size();
        return total;
    }

    public int getOnlineDevices() {
        int total = 0;
        for (Room r : rooms) total += r.getOnlineDeviceCount();
        return total;
    }

    public int getOfflineDevices() {
        int total = 0;
        for (Room r : rooms) total += r.getOfflineDeviceCount();
        return total;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}