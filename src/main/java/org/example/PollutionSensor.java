package org.example;

import HES.Systems.EnvironmentalSystem;

// Inheritance: a PollutionSensor is a Sensor
public class PollutionSensor extends Sensor {
    public static final double MAX_PM25 = 5.0;     // limit in μg/m³
    public static final double SWITCH_ON_AT = 4.0; // start purifying before the limit
    public static final double SWITCH_OFF_AT = 1.0;

    private float pollution;
    private boolean purifierOn;

    public PollutionSensor() {
        super("Air Pollution (PM2.5)");
    }

    @Override
    public void update(EnvironmentalSystem env) {
        pollution = env.getPollutionFeed().getPollution();
        purifierOn = env.isAirPurifierOn();

        if (pollution > SWITCH_ON_AT && !purifierOn) {
            env.turnAirPurifierOn(true);           // polluted: purifier on
        } else if (pollution < SWITCH_OFF_AT && purifierOn) {
            env.turnAirPurifierOn(false);          // clean: purifier off
        }
        purifierOn = env.isAirPurifierOn();
    }

    @Override
    public String getDisplayText() {
        return "<html><b>" + getName() + "</b>"
                + "<br>Level: " + String.format("%.1f", pollution) + " μg/m³"
                + "<br>Air purifier: " + (purifierOn ? "ON" : "OFF")
                + "</html>";
    }

    @Override
    public boolean isOutOfRange() {
        return pollution > MAX_PM25;
    }
}
