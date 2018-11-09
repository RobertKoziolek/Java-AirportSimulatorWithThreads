package pckg.view;

import pckg.Airport;
import pckg.AirportManager;
import pckg.plane.Plane;
import pckg.plane.PlaneManager;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Window {
    private static final int PLANE_LAYER = 0;
    private static final int CRASH_LAYER = 1;
    private static final int AIRPORT_LAYER = 2;
    private final PlaneManager planeManager;
    private JLayeredPane pane;
    private List<PlaneLabel> planeLabels;

    public Window(final PlaneManager planeManager) {
        InitializeFrame();
        VisualizeAirports();
        this.planeManager = planeManager;
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
                        while (true) {
                            planeLabels.forEach(label -> updateLabel(label));
                        }
                    }

                    private void updateLabel(final PlaneLabel label) {
                        if (label.update() == false) {
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

    private void VisualizeAirports() {
        for (final Airport airport : AirportManager.getList()) {
            final JLabel portLabel = new JLabel(new ImageIcon("src//img//airport.png"));
            portLabel.setText(airport.getName());
            portLabel.setForeground(Color.BLUE);
            portLabel.setHorizontalTextPosition(JLabel.CENTER);
            portLabel.setVerticalTextPosition(JLabel.BOTTOM);
            portLabel.setBounds((int) airport.getPosition()
                                             .getX(), (int) airport.getPosition()
                                                                   .getY(), 100, 86);
            pane.add(portLabel, AIRPORT_LAYER);
        }
    }

    private void InitializeFrame() {
        final JFrame frame = new JFrame("Airplanes simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.white);
        frame.setSize(640, 480);
        frame.setLayout(null);
        frame.setVisible(true);
        pane = new JLayeredPane();
        frame.setContentPane(pane);
    }
}
