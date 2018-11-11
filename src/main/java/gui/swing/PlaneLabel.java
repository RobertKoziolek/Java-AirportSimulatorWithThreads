package gui.swing;


import plane.Plane;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PlaneLabel extends JLabel {
    static private final HashMap<Integer, Color> colors = new HashMap<>();

    static {
        int i;
        for (i = 0; i < 50; i++) {
            colors.put(i, new Color(255, i * 255 / 50, 0));
        }
        for (int j = 51; j > 0; j--) {
            colors.put(i, new Color(j * 255 / 51, 255, 0));
            ++i;
        }
    }

    private Plane plane;

    public PlaneLabel(final Plane plane) {
        super(plane.getName());
        this.plane = plane;
    }

    public void setPlane(final Plane plane) {
        this.plane = plane;
        setText(plane.getName());
    }

    public boolean update() {
        setBounds((int) plane.getPosition()
                             .getX(), (int) plane.getPosition()
                                                 .getY(), 32, 32);
        setForeground(colors.get(plane.getFuelPercentage()));
        return plane.isAlive();
    }
}
