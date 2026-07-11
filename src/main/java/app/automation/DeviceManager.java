package app.automation;

import java.util.ArrayList;
import java.util.List;

public class DeviceManager {

    private List<AutomationRule> rules;
    private List<Schedule> schedules;

    public DeviceManager() {
        this.rules = new ArrayList<>();
        this.schedules = new ArrayList<>();
    }

    public void addRule(AutomationRule rule) {
        rules.add(rule);
    }

    public void removeRule(String ruleId) {
        rules.removeIf(r -> r.getId().equals(ruleId));
    }

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
    }

    public void removeSchedule(String scheduleId) {
        schedules.removeIf(s -> s.getId().equals(scheduleId));
    }

    public void checkAutomationRules(int currentHour) {
        for (AutomationRule rule : rules) {
            if (rule.evaluate(currentHour)) {
                rule.execute();
                System.out.println("Automation triggered: " + rule.getDescription());
            }
        }
    }

    public void runSchedules(int currentHour) {
        for (Schedule schedule : schedules) {
            if (schedule.isDue(currentHour)) {
                schedule.execute();
                System.out.println("Schedule executed: " + schedule.getId());
            }
        }
    }

    public List<AutomationRule> getAllRules() { return rules; }
    public List<Schedule> getAllSchedules() { return schedules; }
}