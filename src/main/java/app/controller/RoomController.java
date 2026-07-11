package app.controller;

import app.model.House;
import app.model.Room;
import app.util.AppContext;
import app.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class RoomController {

    @FXML private Pane roomsBackgroundPane;
    @FXML private ListView<String> roomsListView;
    @FXML private TextField newRoomNameField;

    private House house;
    private ObservableList<String> roomNames;

    @FXML
    public void initialize() {
        house = AppContext.getHouse();

        roomsBackgroundPane.setStyle(
            "-fx-background-image: url('/images/rooms-bg.jpg'); " +
            "-fx-background-size: cover; " +
            "-fx-background-position: center center;"
        );

        roomNames = FXCollections.observableArrayList();
        refreshRoomList();
        roomsListView.setItems(roomNames);
    }

    private void refreshRoomList() {
        roomNames.clear();
        for (Room r : house.getAllRooms()) {
            roomNames.add(r.getName() + "  (" + r.getAllDevices().size() + " devices)");
        }
    }

    @FXML
    private void handleAddRoom() {
        String name = newRoomNameField.getText().trim();
        if (name.isEmpty()) {
            showAlert("Please enter a room name.");
            return;
        }

        String id = "R" + (house.getAllRooms().size() + 1);
        house.addRoom(new Room(id, name));
        newRoomNameField.clear();
        refreshRoomList();
    }

    @FXML
    private void handleRemoveRoom() {
        int selectedIndex = roomsListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            showAlert("Please select a room to remove.");
            return;
        }

        Room roomToRemove = house.getAllRooms().get(selectedIndex);
        house.removeRoom(roomToRemove.getId());
        refreshRoomList();
    }

    @FXML
    private void handleViewDevices() {
        int selectedIndex = roomsListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            showAlert("Please select a room first.");
            return;
        }

        Room selectedRoom = house.getAllRooms().get(selectedIndex);
        AppContext.setSelectedRoom(selectedRoom);

        SceneManager.switchScene("/fxml/devices.fxml", "Smart Home Simulator - " + selectedRoom.getName());
    }

    @FXML
    private void handleBack() {
        SceneManager.switchScene("/fxml/dashboard.fxml", "Smart Home Simulator - Dashboard");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message);
        alert.showAndWait();
    }
}