package org.example;

import HES.Systems.EnvironmentalSystem;

// Inheritance: a TemperatureSensor is a Sensor
public class TemperatureSensor extends Sensor {
    public static final double TOLERANCE = 0.5;       // allowed: target ± 0.5
    public static final double SWITCH_MARGIN = 0.25;  // switch heating inside that range

    private final int wardId;
    private final double target;
    private float temperature;
    private boolean heatingOn;

    public TemperatureSensor(String wardName, int wardId, double target) {
        super(wardName);
        this.wardId = wardId;
        this.target = target;
    }

    @Override
    public void update(EnvironmentalSystem env) {
        temperature = env.getTempFeed().getTemperature(wardId);
        heatingOn = env.isHeatingOn(wardId);

        if (temperature < target - SWITCH_MARGIN && !heatingOn) {
            env.turnHeatingOn(wardId, true);       // too cold: heating on
        } else if (temperature > target + SWITCH_MARGIN && heatingOn) {
            env.turnHeatingOn(wardId, false);      // warm enough: heating off
        }
        heatingOn = env.isHeatingOn(wardId);
    }

    @Override
    public String getDisplayText() {
        return "<html><b>" + getName() + "</b>"
                + "<br>Current: " + String.format("%.1f", temperature) + " °C"
                + "<br>Ideal: " + String.format("%.1f", target) + " °C"
                + "<br>Heating: " + (heatingOn ? "ON" : "OFF")
                + "</html>";
    }

    @Override
    public boolean isOutOfRange() {
        return Math.abs(temperature - target) > TOLERANCE;
    }
}