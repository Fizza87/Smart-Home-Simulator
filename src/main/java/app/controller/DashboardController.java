package app.controller;

import app.model.House;
import app.util.AppContext;
import app.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class DashboardController {

    @FXML private Pane dashboardBackgroundPane;
    @FXML private Label welcomeLabel;
    @FXML private Label totalRoomsLabel;
    @FXML private Label totalDevicesLabel;
    @FXML private Label onlineDevicesLabel;
    @FXML private Label offlineDevicesLabel;

    private House house;

    @FXML
    public void initialize() {
        house = AppContext.getHouse();

        dashboardBackgroundPane.setStyle(
            "-fx-background-image: url('/images/dashboard-bg.jpg'); " +
            "-fx-background-size: cover; " +
            "-fx-background-position: center center;"
        );

        refreshStats();
    }

    private void refreshStats() {
        totalRoomsLabel.setText(String.valueOf(house.getTotalRooms()));
        totalDevicesLabel.setText(String.valueOf(house.getTotalDevices()));
        onlineDevicesLabel.setText(String.valueOf(house.getOnlineDevices()));
        offlineDevicesLabel.setText(String.valueOf(house.getOfflineDevices()));
    }

    @FXML
    private void goToRooms() {
        SceneManager.switchScene("/fxml/rooms.fxml", "Smart Home Simulator - Rooms");
    }

    @FXML
    private void goToDevices() {
        SceneManager.switchScene("/fxml/all-devices.fxml", "Smart Home Simulator - All Devices");
    }

    @FXML
    private void goToAutomation() {
        SceneManager.switchScene("/fxml/automation.fxml", "Smart Home Simulator - Automation");
    }

    @FXML
    private void goToEnergy() {
        SceneManager.switchScene("/fxml/energy.fxml", "Smart Home Simulator - Energy Monitor");
    }

    @FXML
    private void handleLogout() {
        SceneManager.switchScene("/fxml/login.fxml", "Smart Home Simulator - Login");
    }
}