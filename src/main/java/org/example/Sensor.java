package org.example;

import HES.Systems.EnvironmentalSystem;

// Inheritance + Abstraction: every sensor reads, controls a device,
// shows text and checks its range, but each kind does it differently.
public abstract class Sensor {
    // Encapsulation: private field, read through getName()
    private final String name;

    public Sensor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Read the sensor and switch its device on or off
    public abstract void update(EnvironmentalSystem env);

    // Text shown in this sensor's grid cell
    public abstract String getDisplayText();

    // True when the reading is outside its allowed range (cell turns red)
    public abstract boolean isOutOfRange();
}