package pckg.plane;

import pckg.Airport;
import pckg.Vector2;

public class LandingState implements PlaneState {
    private Airport airport;
    private int progress = 0;

    public LandingState(Airport airport) {
        this.airport = airport;
    }

    @Override
    public void setup(Plane plane) {
        plane.moveTo(airport.getRunwayStartPosition());
    }

    @Override
    public void doAction(Plane plane) {
        plane.move(new Vector2(5, 0));
        if (progress++ > 6) {
            plane.setState(new LandedState(airport));
            plane.refuel();
            progress = 0;
            airport.freeRunway();
        }
    }
}
