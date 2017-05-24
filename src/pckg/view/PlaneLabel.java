package pckg.view;

import pckg.PlaneInterface;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PlaneLabel extends JLabel {
    private PlaneInterface plane;

    public PlaneLabel(PlaneInterface plane) {
        super(plane.getName());
        this.plane = plane;
    }

    public void setPlane(PlaneInterface plane){
        this.plane=plane;
        setText(plane.getName());
    }

    public boolean update() {
        setBounds((int) plane.getPosition().getX(), (int) plane.getPosition().getY(), 32, 32);
        setForeground(colors.get(plane.getFuelPercentage()));
        return plane.isAlive();
    }

    static private HashMap<Integer, Color> colors = new HashMap<Integer, Color>();

    static{
        int i;
        for (i = 0; i < 50; i++) {
            colors.put(i, new Color(255, i * 255 / 50, 0));
        }
        for (int j = 51; j > 0; j--) {
            colors.put(i, new Color(j * 255 / 51, 255, 0));
            ++i;
        }
    }
}
