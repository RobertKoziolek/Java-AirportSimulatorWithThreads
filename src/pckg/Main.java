package pckg;

import pckg.plane.PlaneManager;
import pckg.view.Window;

public class Main {
	//TODO changing these values in program
	private static final int PLANES = 33;
	public static final float PLANESPEED = 9f;
	public static final float FUELBASE = 99;

	public static void main(String[] args) {
		PlaneManager planeManager = new PlaneManager(PLANES);
		new Window(planeManager);
	}
}
