package gui.swing;


import airport.Airport;
import airport.AirportManager;
import plane.Plane;
import plane.PlaneManager;
import util.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import static gui.Constants.*;

public class Window {
    private static final int PLANE_LAYER = 0;
    private static final int CRASH_LAYER = 1;
    private static final int AIRPORT_LAYER = 2;
    private final PlaneManager planeManager;
    private JLayeredPane pane;
    private List<PlaneLabel> planeLabels;

    public Window() {
        this.planeManager = new PlaneManager();
        initializeFrame();
        visualizeAirports();
        createPlaneLabels();
        initializeWatcherThread();
    }

    private void initializeWatcherThread() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Thread() {
                    @Override
                    public void run() {
                        while (!planeManager.isEmpty()) {
                            planeLabels.forEach(this::updateLabel);
                        }
                    }

                    private void updateLabel(final PlaneLabel label) {
                        if (!label.update()) {
                            addCrashSite(label.getX(), label.getY());
                            label.setPlane(planeManager.createNew());
                        }
                    }
                }.start();
            }
        });
    }

    private void createPlaneLabels() {
        planeLabels = new LinkedList<>();
        for (final Plane plane : planeManager.getPlanes()) {
            final PlaneLabel label = new PlaneLabel(plane);
            planeLabels.add(label);
            pane.add(label, PLANE_LAYER);
        }
    }

    private void addCrashSite(final int x, final int y) {
        final JLabel label = new JLabel("*");
        label.setForeground(Color.RED);
        label.setBounds(x, y, 16, 16);
        pane.add(label, CRASH_LAYER);
    }

    private void visualizeAirports() {
        for (final Airport airport : AirportManager.getList()) {
            final JLabel portLabel = new JLabel(new ImageIcon("src//img//airport.png"));
            portLabel.setText(airport.getName());
            portLabel.setForeground(Color.BLUE);
            portLabel.setHorizontalTextPosition(JLabel.CENTER);
            portLabel.setVerticalTextPosition(JLabel.BOTTOM);
            final Vector2 position = airport.getPosition();
            portLabel.setBounds((int) position.getX(), (int) position.getY(), AIRPORT_WIDTH, AIRPORT_HEIGHT);
            pane.add(portLabel, AIRPORT_LAYER);
        }
    }

    private void initializeFrame() {
        final JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.white);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLayout(null);
        frame.setVisible(true);
        pane = new JLayeredPane();
        frame.setContentPane(pane);
    }
}
