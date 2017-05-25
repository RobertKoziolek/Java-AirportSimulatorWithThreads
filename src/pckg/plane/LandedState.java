package pckg.plane;

import com.sun.deploy.config.VerboseDefaultConfig;
import pckg.Airport;
import pckg.Vector2;

import static java.lang.Thread.yield;

public class LandedState implements PlaneState {
    private Airport airport;
    private Vector2 hangarSpace;

    public LandedState(Airport airport) {
        this.airport = airport;
    }

    @Override
    public void setup(Plane plane) {
        hangarSpace = airport.getHangarSpace();
        plane.moveTo(hangarSpace);
    }

    @Override
    public void doAction(Plane plane) {
        yield();
        if (airport.takeRunway()) {
            plane.setState(new TakingOffState(airport));
            airport.freeHangarSpace(hangarSpace);
        }
    }
}
