package app.service;

import app.model.House;
import app.model.Room;
import java.util.LinkedHashMap;
import java.util.Map;

public class EnergyCalculator {

    public Map<String, Double> getEnergyBreakdown(House house) {
        Map<String, Double> breakdown = new LinkedHashMap<>();
        for (Room room : house.getAllRooms()) {
            breakdown.put(room.getName(), room.getTotalPowerConsumption());
        }
        return breakdown;
    }

    public double calculateTotalEnergy(House house) {
        double total = 0;
        for (Room room : house.getAllRooms()) {
            total += room.getTotalPowerConsumption();
        }
        return total;
    }
}