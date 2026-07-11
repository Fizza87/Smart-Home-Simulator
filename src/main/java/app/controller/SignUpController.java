package app.controller;

import app.model.User;
import app.util.AppContext;
import app.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML private TextField nameField;
    @FXML private TextField usernameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label errorLabel;
    @FXML private Button signUpButton;

    @FXML
    private void handleSignUp() {
        String name = nameField.getText().trim();
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();

        if (name.isEmpty() || username.isEmpty() || email.isEmpty()
                || password.isEmpty() || confirmPassword.isEmpty()) {
            showError("Please fill in all fields.");
            return;
        }

        if (username.length() < 3) {
            showError("Username must be at least 3 characters.");
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            showError("Please enter a valid email address.");
            return;
        }

        if (password.length() < 4) {
            showError("Password must be at least 4 characters.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match.");
            return;
        }

        if (usernameExists(username)) {
            showError("Username already exists.");
            return;
        }

        User newUser = new User(name, username, email, password, "MEMBER");
        AppContext.getHouse().addUser(newUser);

        System.out.println("New user registered: " + username);

        SceneManager.switchScene("/fxml/login.fxml", "Smart Home Simulator - Login");
    }

    @FXML
    private void handleBackToLogin() {
        SceneManager.switchScene("/fxml/login.fxml", "Smart Home Simulator - Login");
    }

    private boolean usernameExists(String username) {
        return AppContext.getHouse().getAllUsers()
                .stream()
                .anyMatch(u -> u.getUsername().equals(username));
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }
}