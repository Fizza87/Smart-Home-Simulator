package app.controller;
import app.devices.*;
import app.model.Room;
import app.util.AppContext;
import app.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import app.interfaces.Lockable;

public class DeviceController {

    @FXML private ImageView backgroundImageView;
    @FXML private Label roomNameLabel;
    @FXML private ListView<String> devicesListView;
    @FXML private TextField newDeviceNameField;
    @FXML private ComboBox<String> deviceTypeComboBox;

    private Room room;
    private ObservableList<String> deviceLabels;

    @FXML
    public void initialize() {
        room = AppContext.getSelectedRoom();

        if (room == null) {
            showAlert("No room selected.");
            SceneManager.switchScene("/fxml/rooms.fxml", "Smart Home Simulator - Rooms");
            return;
        }

        roomNameLabel.setText(room.getName() + " - Devices");
        setBackgroundForRoom(room.getName());

        deviceTypeComboBox.setItems(FXCollections.observableArrayList(
                "Light", "Fan", "Door", "Camera", "AirConditioner", "SmokeSensor", "MotionSensor"
        ));

        deviceLabels = FXCollections.observableArrayList();
        refreshDeviceList();
        devicesListView.setItems(deviceLabels);
    }

    @FXML private Pane backgroundPane;   // ImageView ki jagah Pane

private void setBackgroundForRoom(String roomName) {
    String key = roomName.toLowerCase();
    String imagePath;

    if (key.contains("kitchen")) {
        imagePath = "/images/kitchen-bg.jpg";
    } else if (key.contains("garage")) {
        imagePath = "/images/garage-bg.jpg";
    } else if (key.contains("living")) {
        imagePath = "/images/living-room-bg.jpg";
    } else if (key.contains("bedroom")) {
        imagePath = "/images/bedroom-bg.jpg";
    } else if (key.contains("drawing room")) {
        imagePath = "/images/drawing-room.jpg";
    } else {
        imagePath = "/images/default-room-bg.jpg";
    }

    backgroundPane.setStyle(
        "-fx-background-image: url('" + imagePath + "'); " +
        "-fx-background-size: cover; " +
        "-fx-background-position: center center;"
    );
}

    private void refreshDeviceList() {
        deviceLabels.clear();
        for (Device d : room.getAllDevices()) {
            deviceLabels.add(d.displayStatus());
        }
    }

    @FXML
private void handleTurnOn() {
    Device selected = getSelectedDevice();
    if (selected == null) return;

    boolean wasOn = selected.isOn();
    selected.turnOn();

    if (!selected.isOn() && !wasOn) {
        showAlert(selected.getName() + " could not be turned ON. If it's a door, unlock it first.");
    }

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
    private void handleViewDetails() {
        Device selected = getSelectedDevice();
        if (selected == null) return;
        showAlert(selected.displayStatus());
    }

    @FXML
    private void handleRemoveDevice() {
        int index = devicesListView.getSelectionModel().getSelectedIndex();
        if (index < 0) {
            showAlert("Please select a device first.");
            return;
        }
        Device selected = room.getAllDevices().get(index);
        room.removeDevice(selected.getId());
        refreshDeviceList();
    }
    @FXML
private void handleUnlock() {
    Device selected = getSelectedDevice();
    if (selected == null) return;

    if (selected instanceof Lockable lockable) {
        lockable.unlock();
        refreshDeviceList();
    } else {
        showAlert("This device does not support locking.");
    }
}

@FXML
private void handleLock() {
    Device selected = getSelectedDevice();
    if (selected == null) return;

    if (selected instanceof Lockable lockable) {
        lockable.lock();
        refreshDeviceList();
    } else {
        showAlert("This device does not support locking.");
    }
}
@FXML
private void handleSimulateDetection() {
    Device selected = getSelectedDevice();
    if (selected == null) return;

    if (selected instanceof MotionSensor motionSensor) {
        if (!motionSensor.isOn()) {
            showAlert("Turn ON the sensor first before simulating detection.");
            return;
        }
        motionSensor.detectMotion(true);
        showAlert(motionSensor.getName() + " now detects motion.");
        refreshDeviceList();

    } else if (selected instanceof SmokeSensor smokeSensor) {
        if (!smokeSensor.isOn()) {
            showAlert("Turn ON the sensor first before simulating detection.");
            return;
        }
        smokeSensor.detectSmoke(true);
        showAlert(smokeSensor.getName() + " now detects smoke.");
        refreshDeviceList();

    } else {
        showAlert("This device does not support detection simulation.");
    }
}

    @FXML
    private void handleAddDevice() {
        String name = newDeviceNameField.getText().trim();
        String type = deviceTypeComboBox.getValue();

        if (name.isEmpty()) {
            showAlert("Please enter a device name.");
            return;
        }
        if (type == null) {
            showAlert("Please select a device type.");
            return;
        }

        String id = "D" + (room.getAllDevices().size() + 1) + "-" + room.getId();
        Device newDevice = createDevice(type, id, name);

        if (newDevice == null) {
            showAlert("Unknown device type.");
            return;
        }

        room.addDevice(newDevice);
        newDeviceNameField.clear();
        deviceTypeComboBox.setValue(null);
        refreshDeviceList();
    }

    private Device createDevice(String type, String id, String name) {
        String location = room.getName();

        switch (type) {
            case "Light":
                return new Light(id, name, location, 60);
            case "Fan":
                return new Fan(id, name, location, 75);
            case "Door":
                return new Door(id, name, location, 5);
            case "Camera":
                return new Camera(id, name, location, 15, "1080p");
            case "AirConditioner":
                return new AirConditioner(id, name, location, 1500);
            case "SmokeSensor":
                return new SmokeSensor(id, name, location, 5);
            case "MotionSensor":
                return new MotionSensor(id, name, location, 5);
            default:
                return null;
        }
    }

    @FXML
    private void handleBack() {
        SceneManager.switchScene("/fxml/rooms.fxml", "Smart Home Simulator - Rooms");
    }

    private Device getSelectedDevice() {
        int index = devicesListView.getSelectionModel().getSelectedIndex();
        if (index < 0) {
            showAlert("Please select a device first.");
            return null;
        }
        return room.getAllDevices().get(index);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.showAndWait();
    }
}