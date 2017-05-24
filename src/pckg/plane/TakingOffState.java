package pckg.plane;

import pckg.Airport;
import pckg.AirportManager;
import pckg.Vector2;

public class TakingOffState implements PlaneState {
    private Airport airport;
    private int progress = 0;

    public TakingOffState(Airport airport){
        this.airport=airport;
    }


    @Override
    public void setup(Plane plane) {
        plane.moveTo(airport.getRunwayEndPosition());
    }
    @Override
    public void doAction(Plane plane) {
        plane.move(new Vector2(-4,0));
        if (progress++ > 6) {
            airport.freeRunway();
            Airport newAirport = AirportManager.getRandomOtherThan(airport);
            plane.setState(new FlyState(newAirport));
            progress = 0;
        }
    }
}
