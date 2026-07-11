package app.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NotificationService {

    private List<String> notifications;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public NotificationService() {
        this.notifications = new ArrayList<>();
    }

    public void notify(String message) {
        String timeStamped = "[" + LocalDateTime.now().format(FORMATTER) + "] " + message;
        notifications.add(timeStamped);
        System.out.println(timeStamped);
    }

    public List<String> getAllNotifications() {
        return notifications;
    }

    public void clearNotifications() {
        notifications.clear();
    }
}