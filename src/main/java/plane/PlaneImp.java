package plane;

import airport.Airport;
import airport.AirportManager;
import state.LandedState;
import state.PlaneState;
import util.Vector2;

import static gui.Constants.BASE_FUEL;

public class PlaneImp extends Thread implements Plane {
    static private final float fuelBase = BASE_FUEL;
    private static int planeId = 0;
    private final float maxFuel;
    private final Airport airport;
    private float fuel;
    private PlaneState state;
    private Vector2 position;
    private boolean alive = true;

    public PlaneImp() {
        super(Integer.toString(planeId++));
        this.airport = AirportManager.getRandomOtherThan(null);
        setState(new LandedState(airport));
        start();
        maxFuel = (float) ((Math.random() + 0.5f) * fuelBase);
        fuel = maxFuel;
    }

    @Override
    public void run() {
        while (alive) {
            state.doAction(this);
            try {
                sleep(100);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void burnFuel() {
        if (fuel-- < 0) {
            alive = false;
        }
    }

    public void setState(final PlaneState state) {
        this.state = state;
        state.setup(this);
    }

    public void move(final Vector2 moveVec) {
        position.add(moveVec);
    }

    public void moveTo(final Vector2 target) {
        position = new Vector2(target);
    }

    public void refuel() {
        fuel = maxFuel;
    }

    @Override
    public int getFuelPercentage() {
        final float f = (float) fuel / (float) maxFuel;
        if (f <= 0) {
            return 0;
        }
        return (int) (100.f * f);
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }
}
