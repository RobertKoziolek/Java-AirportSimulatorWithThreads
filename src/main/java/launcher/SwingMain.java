package launcher;

import gui.swing.Window;
import plane.PlaneManager;

public class SwingMain {
    public static final float PLANESPEED = 9f;
    public static final float FUELBASE = 99;
    //TODO changing these values in program
    private static final int PLANES = 33;

    public static void main(final String[] args) {
        final PlaneManager planeManager = new PlaneManager(PLANES);
        new Window(planeManager);
    }
}
