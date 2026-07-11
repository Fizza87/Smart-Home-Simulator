package app.controller;

import app.automation.AutomationRule;
import app.automation.DeviceManager;
import app.devices.Device;
import app.devices.MotionSensor;
import app.devices.SmokeSensor;
import app.model.House;
import app.model.Room;
import app.util.AppContext;
import app.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AutomationController {

    @FXML private ListView<String> rulesListView;
    @FXML private ComboBox<String> triggerDeviceComboBox;
    @FXML private ComboBox<String> conditionComboBox;
    @FXML private ComboBox<String> targetDeviceComboBox;
    @FXML private ComboBox<String> actionComboBox;
    @FXML private Pane automationBackgroundPane;

    private House house;
    private DeviceManager deviceManager;
    private List<Device> allDevices;
    private ObservableList<String> ruleLabels;
    

    @FXML
    public void initialize() {
        house = AppContext.getHouse();
        deviceManager = new DeviceManager();
        allDevices = collectAllDevices();
        automationBackgroundPane.setStyle(
        "-fx-background-image: url('/images/automation-bg.jpg'); " +
        "-fx-background-size: cover; " +
        "-fx-background-position: center center;"
    );

        ObservableList<String> deviceNames = FXCollections.observableArrayList();
        for (Device d : allDevices) {
            deviceNames.add(d.getName() + " (" + d.getLocation() + ")");
        }

        triggerDeviceComboBox.setItems(deviceNames);
        targetDeviceComboBox.setItems(FXCollections.observableArrayList(deviceNames));

        conditionComboBox.setItems(FXCollections.observableArrayList(
                "MOTION_DETECTED", "SMOKE_DETECTED"
        ));

        actionComboBox.setItems(FXCollections.observableArrayList(
                "TURN_ON", "TURN_OFF"
        ));

        ruleLabels = FXCollections.observableArrayList();
        rulesListView.setItems(ruleLabels);
    }

    private List<Device> collectAllDevices() {
        List<Device> devices = new ArrayList<>();
        for (Room room : house.getAllRooms()) {
            devices.addAll(room.getAllDevices());
        }
        return devices;
    }

    @FXML
    private void handleAddRule() {
        int triggerIndex = triggerDeviceComboBox.getSelectionModel().getSelectedIndex();
        String condition = conditionComboBox.getValue();
        int targetIndex = targetDeviceComboBox.getSelectionModel().getSelectedIndex();
        String action = actionComboBox.getValue();

        if (triggerIndex < 0 || condition == null || targetIndex < 0 || action == null) {
            showAlert("Please fill all fields to create a rule.");
            return;
        }

        Device triggerDevice = allDevices.get(triggerIndex);
        Device targetDevice = allDevices.get(targetIndex);

        // Validate trigger device matches condition type
        if (condition.equals("MOTION_DETECTED") && !(triggerDevice instanceof MotionSensor)) {
            showAlert("MOTION_DETECTED condition requires a Motion Sensor as trigger.");
            return;
        }
        if (condition.equals("SMOKE_DETECTED") && !(triggerDevice instanceof SmokeSensor)) {
            showAlert("SMOKE_DETECTED condition requires a Smoke Sensor as trigger.");
            return;
        }

        String ruleId = "RULE" + (deviceManager.getAllRules().size() + 1);
        String description = "IF " + triggerDevice.getName() + " " + condition
                + " THEN " + action + " " + targetDevice.getName();

        AutomationRule rule = new AutomationRule(ruleId, description, triggerDevice, condition, -1, targetDevice, action);
        deviceManager.addRule(rule);

        refreshRuleList();
    }

    @FXML
    private void handleRunRules() {
        int currentHour = LocalTime.now().getHour();
        deviceManager.checkAutomationRules(currentHour);
        showAlert("Rules evaluated. Check console for triggered actions.");
    }

    @FXML
    private void handleRemoveRule() {
        int index = rulesListView.getSelectionModel().getSelectedIndex();
        if (index < 0) {
            showAlert("Please select a rule to remove.");
            return;
        }
        AutomationRule rule = deviceManager.getAllRules().get(index);
        deviceManager.removeRule(rule.getId());
        refreshRuleList();
    }

    private void refreshRuleList() {
        ruleLabels.clear();
        for (AutomationRule rule : deviceManager.getAllRules()) {
            ruleLabels.add(rule.getDescription());
        }
    }

    @FXML
    private void handleBack() {
        SceneManager.switchScene("/fxml/dashboard.fxml", "Smart Home Simulator - Dashboard");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.showAndWait();
    }


}