package org.example;

import HES.Systems.EnvironmentalSystem;

// Inheritance: a HumiditySensor is a Sensor
public class HumiditySensor extends Sensor {
    public static final int MAX_DIFFERENCE = 10;   // inside may be at most 10% above outside
    public static final int SWITCH_ON_AT = 8;      // start dehumidifying before the limit
    public static final int SWITCH_OFF_AT = 4;

    private int inside;
    private int outside;
    private boolean dehumidifierOn;

    public HumiditySensor() {
        super("Humidity");
    }

    @Override
    public void update(EnvironmentalSystem env) {
        inside = env.getHumidityFeed().getInsideHumidity();
        outside = env.getHumidityFeed().getOutsideHumidity();
        dehumidifierOn = env.isDehumidifierOn();

        int difference = inside - outside;
        if (difference > SWITCH_ON_AT && !dehumidifierOn) {
            env.turnDehumidifierOn(true);          // too humid: dehumidifier on
        } else if (difference < SWITCH_OFF_AT && dehumidifierOn) {
            env.turnDehumidifierOn(false);         // dry enough: dehumidifier off
        }
        dehumidifierOn = env.isDehumidifierOn();
    }

    @Override
    public String getDisplayText() {
        return "<html><b>" + getName() + "</b>"
                + "<br>Inside: " + inside + "%"
                + "<br>Outside: " + outside + "%"
                + "<br>Difference: " + (inside - outside) + "%"
                + "<br>Dehumidifier: " + (dehumidifierOn ? "ON" : "OFF")
                + "</html>";
    }

    @Override
    public boolean isOutOfRange() {
        return inside - outside > MAX_DIFFERENCE;
    }
}