package app.controller;

import app.devices.Device;
import app.model.House;
import app.model.Room;
import app.util.AppContext;
import app.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class AllDevicesController {

    @FXML private ListView<String> allDevicesListView;

    private House house;
    private List<Device> allDevices;
    private ObservableList<String> deviceLabels;
    @FXML private Pane allDevicesBackgroundPane;

    @FXML
    public void initialize() {
        house = AppContext.getHouse();
        allDevicesBackgroundPane.setStyle(
        "-fx-background-image: url('/images/all-devices-bg.jpg'); " +
        "-fx-background-size: cover; " +
        "-fx-background-position: center center;"
    );
        deviceLabels = FXCollections.observableArrayList();
        allDevicesListView.setItems(deviceLabels);
        refreshDeviceList();
    }

    private void refreshDeviceList() {
        deviceLabels.clear();
        allDevices = new ArrayList<>();

        for (Room room : house.getAllRooms()) {
            for (Device d : room.getAllDevices()) {
                allDevices.add(d);
                deviceLabels.add("[" + room.getName() + "]  " + d.displayStatus());
            }
        }

        if (allDevices.isEmpty()) {
            deviceLabels.add("No devices found. Add devices from Rooms screen first.");
        }
    }

    @FXML
    private void handleTurnOn() {
        Device selected = getSelectedDevice();
        if (selected == null) return;
        selected.turnOn();
        refreshDeviceList();
    }

    @FXML
    private void handleTurnOff() {
        Device selected = getSelectedDevice();
        if (selected == null) return;
        selected.turnOff();
        refreshDeviceList();
    }

    @FXML
    private void handleRefresh() {
        refreshDeviceList();
    }

    @FXML
    private void handleBack() {
        SceneManager.switchScene("/fxml/dashboard.fxml", "Smart Home Simulator - Dashboard");
    }

    private Device getSelectedDevice() {
        int index = allDevicesListView.getSelectionModel().getSelectedIndex();
        if (index < 0 || allDevices.isEmpty()) {
            showAlert("Please select a device first.");
            return null;
        }
        return allDevices.get(index);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.showAndWait();
    }
}