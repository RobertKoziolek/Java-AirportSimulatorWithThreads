package pckg;

public class Main {
	private static final int PLANES = 99;
	private static final int FUEL = 999;
	public static void main(String[] args) {
		Plane.setBaseFuel(FUEL);
		new Window(PLANES);

	}

}
