package pckg.plane;

import pckg.Airport;

public class LandedState implements PlaneState {
    private Airport airport;

    public LandedState(Airport airport){
        this.airport=airport;
    }


    @Override
    public void setup(Plane plane) {

    }

    @Override
    public void doAction(Plane plane) {
        if (airport.takeRunway(true)) {
            plane.setState(new TakingOffState(airport));
            float x, y;
            x = airport.getX() + 20;
            y = airport.getY() + 40;
            plane.moveTo(new Vector2(x, y));
        }
    }
}
