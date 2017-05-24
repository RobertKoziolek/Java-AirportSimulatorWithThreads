package pckg.plane;

import pckg.Airport;

public class CircleState implements  PlaneState {
    private Airport airport;
    boolean circlingLeft = false;

    public CircleState(Airport airport){
        this.airport=airport;
    }

    @Override
    public void setup(Plane plane) {

    }

    @Override
    public void doAction(Plane plane) {
        if (airport.takeRunway(false)) {
            plane.setState(new LandingState(airport));
            return;
        } else {
            Vector2 planePosition = plane.getPosition();
            if (circlingLeft){
                planePosition.add(new Vector2(-4,0));
                if (planePosition.getX()<airport.getX()-20) circlingLeft=false;
            }else{
                planePosition.add(new Vector2(4,0));
                if (planePosition.getX()>airport.getX()+20) circlingLeft=true;
            }
        }
        plane.burnFuel();
    }
}

