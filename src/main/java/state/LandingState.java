package state;


import airport.Airport;
import plane.PlaneImp;
import util.Vector2;

public class LandingState implements PlaneState {
    private final Airport airport;
    private int progress = 0;

    public LandingState(final Airport airport) {
        this.airport = airport;
    }

    @Override
    public void setup(final PlaneImp plane) {
        plane.moveTo(airport.getRunwayStartPosition());
    }

    @Override
    public void doAction(final PlaneImp plane) {
        plane.move(new Vector2(5, 0));
        if (progress++ > 6) {
            plane.setState(new LandedState(airport));
            plane.refuel();
            progress = 0;
            airport.freeRunway();
        }
    }
}
