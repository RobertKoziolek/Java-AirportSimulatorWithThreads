package pckg.plane.state;

import pckg.Airport;
import pckg.AirportManager;
import pckg.Vector2;
import pckg.plane.PlaneImp;

public class TakingOffState implements PlaneState {
    private final Airport airport;
    private int progress = 0;

    public TakingOffState(final Airport airport) {
        this.airport = airport;
    }


    @Override
    public void setup(final PlaneImp plane) {
        plane.moveTo(airport.getRunwayEndPosition());
    }

    @Override
    public void doAction(final PlaneImp plane) {
        plane.move(new Vector2(-4, 0));
        if (progress++ > 6) {
            airport.freeRunway();
            final Airport newAirport = AirportManager.getRandomOtherThan(airport);
            plane.setState(new FlyState(newAirport));
            progress = 0;
        }
    }
}
