package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static int seconds = 0;

    public static void main(String[] args) {
        EnvironmentMonitor monitor = new EnvironmentMonitor();
        SensorDisplay display = new SensorDisplay(monitor);

        JFrame frame = new JFrame("Children's Wing Environment Monitor");
        frame.add(display);
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                monitor.updateAll();          // read sensors and control devices
                display.refresh();            // update the grid
                seconds++;
                if (seconds % 30 == 0) {      // every 30 seconds
                    monitor.printLog();
                }
                frame.repaint();
            }
        });
        timer.start();
    }
}