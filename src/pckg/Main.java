package pckg;

public class Main {
	private static final int PLANES = 33;
	private static final int FUEL = 100;
	public static void main(String[] args) {
		Plane.setBaseFuel(FUEL);
		new Window(PLANES);
	}
}
