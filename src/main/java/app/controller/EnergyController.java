package app.controller;

import app.model.House;
import app.service.EnergyCalculator;
import app.util.AppContext;
import app.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.Map;

public class EnergyController {

    @FXML private ListView<String> energyListView;
    @FXML private Label totalPowerLabel;
    @FXML private Pane energyBackgroundPane;

    private House house;
    private EnergyCalculator energyCalculator;
    private ObservableList<String> energyLabels;

    @FXML
    public void initialize() {
        house = AppContext.getHouse();
        energyCalculator = new EnergyCalculator();
        energyBackgroundPane.setStyle(
        "-fx-background-image: url('/images/energy-bg.jpg'); " +
        "-fx-background-size: cover; " +
        "-fx-background-position: center center;"
    );
        energyLabels = FXCollections.observableArrayList();
        energyListView.setItems(energyLabels);
        refreshEnergyData();
    }

    private void refreshEnergyData() {
        energyLabels.clear();

        Map<String, Double> breakdown = energyCalculator.getEnergyBreakdown(house);

        for (Map.Entry<String, Double> entry : breakdown.entrySet()) {
            energyLabels.add(entry.getKey() + "  -  " + entry.getValue() + " W");
        }

        double total = energyCalculator.calculateTotalEnergy(house);
        totalPowerLabel.setText(total + " W");
    }

    @FXML
    private void handleRefresh() {
        refreshEnergyData();
    }

    @FXML
    private void handleBack() {
        SceneManager.switchScene("/fxml/dashboard.fxml", "Smart Home Simulator - Dashboard");
    }
}