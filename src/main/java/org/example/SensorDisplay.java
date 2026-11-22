package org.example;

import javax.swing.*;
import java.awt.*;

// Abstraction: the display only asks the monitor for text and warnings; it stores no data
public class SensorDisplay extends JPanel {
    private final EnvironmentMonitor monitor;
    private final JPanel[] cells;
    private final JLabel[] labels;

    public SensorDisplay(EnvironmentMonitor monitor) {
        this.monitor = monitor;
        int count = monitor.getSensorCount();
        cells = new JPanel[count];
        labels = new JLabel[count];

        setLayout(new GridLayout(2, 3, 5, 5));

        for (int i = 0; i < count; i++) {
            cells[i] = new JPanel(new BorderLayout());
            cells[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
            labels[i] = new JLabel();
            labels[i].setVerticalAlignment(SwingConstants.TOP);
            cells[i].add(labels[i], BorderLayout.CENTER);
            add(cells[i]);
        }
    }

    public void refresh() {
        for (int i = 0; i < cells.length; i++) {
            labels[i].setText(monitor.getDisplayText(i));
            if (monitor.isOutOfRange(i)) {
                cells[i].setBackground(Color.RED);      // warning
            } else {
                cells[i].setBackground(Color.WHITE);
            }
        }
    }
}
