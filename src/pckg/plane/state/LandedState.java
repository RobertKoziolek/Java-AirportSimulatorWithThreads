package pckg.plane.state;

import pckg.Airport;
import pckg.Vector2;
import pckg.plane.PlaneImp;

import static java.lang.Thread.yield;

public class LandedState implements PlaneState {
    private final Airport airport;
    private Vector2 hangarSpace;

    public LandedState(final Airport airport) {
        this.airport = airport;
    }

    @Override
    public void setup(final PlaneImp plane) {
        //TODO if hangar is full plane should wait for another plane to take off
        hangarSpace = airport.getHangarSpace();
        plane.moveTo(hangarSpace);
    }

    @Override
    public void doAction(final PlaneImp plane) {
        yield();
        if (airport.takeRunway()) {
            plane.setState(new TakingOffState(airport));
            airport.freeHangarSpace(hangarSpace);
        }
    }
}
