package pckg.view;

import pckg.Airport;
import pckg.AirportManager;
import pckg.plane.PlaneInterface;
import pckg.plane.PlaneManager;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

public class Window {
    private static final int PLANE_LAYER = 0;
    private static final int CRASH_LAYER = 1;
    private static final int AIRPORT_LAYER = 2;
    private JLayeredPane pane;
    private final PlaneManager planeManager;
    private List<PlaneLabel> planeLabels;

    public Window(PlaneManager planeManager) {
        InitializeFrame();
        VisualizeAirports();
        this.planeManager = planeManager;
        createPlaneLabels();
        initializeWatcherThread();
    }

    private void initializeWatcherThread() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Thread() {
                    public void run() {
                        while (true) {
                            planeLabels.forEach(label -> updateLabel(label));
                        }
                    }

                    private void updateLabel(PlaneLabel label) {
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
        for (PlaneInterface plane : planeManager.getPlanes()) {
            PlaneLabel label = new PlaneLabel(plane);
            planeLabels.add(label);
            pane.add(label, PLANE_LAYER);
        }
    }

    private void addCrashSite(int x, int y) {
        JLabel label = new JLabel("*");
        label.setForeground(Color.RED);
        label.setBounds(x, y, 16, 16);
        pane.add(label, CRASH_LAYER);
    }

    private void VisualizeAirports() {
        for (Airport airport : AirportManager.getList()) {
            JLabel portLabel = new JLabel(new ImageIcon("src//img//airport.png"));
            portLabel.setText(airport.getName());
            portLabel.setForeground(Color.BLUE);
            portLabel.setHorizontalTextPosition(JLabel.CENTER);
            portLabel.setVerticalTextPosition(JLabel.BOTTOM);
            portLabel.setBounds((int) airport.getPosition().getX(), (int) airport.getPosition().getY(), 100, 86);
            pane.add(portLabel, AIRPORT_LAYER);
        }
    }

    private void InitializeFrame() {
        JFrame frame = new JFrame("Airplanes simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.white);
        frame.setSize(640, 480);
        frame.setLayout(null);
        frame.setVisible(true);
        pane = new JLayeredPane();
        frame.setContentPane(pane);
    }
}
