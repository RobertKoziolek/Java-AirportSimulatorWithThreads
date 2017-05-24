package pckg.plane;

import pckg.Airport;
import pckg.AirportManager;

public class TakingOffState implements PlaneState {
    private Airport airport;

    public TakingOffState(Airport airport){
        this.airport=airport;
    }


    @Override
    public void setup(Plane plane) {

    }
    int progress = 0;
    @Override
    public void doAction(Plane plane) {
        plane.getPosition().add(new Vector2(-4,0));
        if (progress++ > 6) {
            airport.freeRunway();
           // airport.freeHangarSpace(hangarSpaceTaken);
            Airport newAirport = AirportManager.getRandom(airport);
            plane.setState(new FlyState(newAirport));
            progress = 0;
        }
    }
}
