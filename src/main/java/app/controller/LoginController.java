package app.controller;

import app.model.User;
import app.util.AppContext;
import app.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    @FXML private Button loginButton;
    @FXML private Button exitButton;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Please enter both username and password.");
            return;
        }

        User matchedUser = findUser(username);

        if (matchedUser == null) {
            showError("User not found.");
            return;
        }

        if (!matchedUser.authenticate(password)) {
            showError("Incorrect password.");
            return;
        }

        errorLabel.setVisible(false);
        System.out.println("Login successful for: " + username);

        SceneManager.switchScene("/fxml/dashboard.fxml", "Smart Home Simulator - Dashboard");
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    @FXML
    private void handleSignUp() {
        SceneManager.switchScene("/fxml/signup.fxml", "Smart Home Simulator - Sign Up");
    }

    private User findUser(String username) {
        for (User u : AppContext.getHouse().getAllUsers()) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }
}