package org.example;

import HES.Systems.EnvironmentalSystem;

public class Main {
    public static void main(String[] args) {
        EnvironmentalSystem env = new EnvironmentalSystem();

        for (int i = 0; i < 10; i++) {
            float north = env.getTempFeed().getTemperature(0);
            float central = env.getTempFeed().getTemperature(1);
            float south = env.getTempFeed().getTemperature(2);
            int inside = env.getHumidityFeed().getInsideHumidity();
            int outside = env.getHumidityFeed().getOutsideHumidity();
            float pollution = env.getPollutionFeed().getPollution();

            System.out.println("Second " + i
                    + " | N=" + north + " C=" + central + " S=" + south
                    + " | humidity in=" + inside + " out=" + outside
                    + " | PM2.5=" + pollution);

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }

        System.out.println(env.getCTScannerStatus());
        System.out.println(env.getUltrasoundScannerStatus());
    }
}