package org.example;

import HES.Systems.EnvironmentalSystem;

import java.util.ArrayList;

// Manages the environmental system and all sensors
public class EnvironmentMonitor {
    // Encapsulation: the system and the sensor list are private
    private final EnvironmentalSystem env = new EnvironmentalSystem();
    // Polymorphism: one list holds every kind of Sensor
    private final ArrayList<Sensor> sensors = new ArrayList<>();

    public EnvironmentMonitor() {
        // To add a new sensor later, just add one line here
        sensors.add(new TemperatureSensor("North Ward", 0, 23));
        sensors.add(new TemperatureSensor("Central Ward", 1, 22));
        sensors.add(new TemperatureSensor("South Ward", 2, 20));
        sensors.add(new HumiditySensor());
        sensors.add(new PollutionSensor());
    }

    // Polymorphism: each sensor runs its own update(); no change needed for new sensors
    public void updateAll() {
        for (Sensor s : sensors) {
            s.update(env);
        }
    }

    public int getSensorCount() {
        return sensors.size();
    }

    public String getDisplayText(int index) {
        return sensors.get(index).getDisplayText();
    }

    public boolean isOutOfRange(int index) {
        return sensors.get(index).isOutOfRange();
    }

    public void printLog() {
        System.out.println("===== Environment log =====");
        for (Sensor s : sensors) {
            System.out.println(toPlainText(s.getDisplayText()));
        }
        System.out.println(env.getCTScannerStatus());
        System.out.println(env.getUltrasoundScannerStatus());
    }

    // Turn the grid's HTML text into one plain line for the console
    private String toPlainText(String html) {
        return html.replace("<br>", " | ").replaceAll("<[^>]*>", "");
    }
}