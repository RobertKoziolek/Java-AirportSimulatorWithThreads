package pckg.view;

import pckg.Airport;
import pckg.AirportManager;
import pckg.PlaneInterface;
import pckg.PlaneManager;
import pckg.plane.Plane;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

public class Window {
    private JLayeredPane pane;

    public Window(int planesNumber) {
        InitializeFrame();
        VisualizeAirports();
        PlaneManager planes = new PlaneManager(planesNumber);
        List<PlaneLabel> planeLabels = new LinkedList<>();
        for(PlaneInterface plane : planes.getPlanes()){
            PlaneLabel label = new PlaneLabel(plane);
            planeLabels.add(label);
            pane.add(label,0);
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Thread() {
                    public void run() {
                        while (true) {
                            for (PlaneLabel label : planeLabels){
                                if (label.update()==false){
                                    addCrashSite(label.getX(),label.getY());
                                    label.setPlane(planes.createNew());
                                }
                            }
                        }
                    }
                }.start();
            }
        });
    }

    private void addCrashSite(int x, int y) {
        JLabel label = new JLabel("*");
        label.setForeground(Color.RED);
        label.setBounds(x, y, 16, 16);
        pane.add(label, 2);
    }

    private void VisualizeAirports() {
        for (Airport airport : AirportManager.getList()) {
            JLabel portLabel = new JLabel(new ImageIcon("src//img//airport.png"));
            portLabel.setText(airport.getName());
            portLabel.setForeground(Color.BLUE);
            portLabel.setHorizontalTextPosition(JLabel.CENTER);
            portLabel.setVerticalTextPosition(JLabel.BOTTOM);
            portLabel.setBounds((int) airport.getPosition().getX(), (int) airport.getPosition().getY(), 100, 86);
            pane.add(portLabel, 1);
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
