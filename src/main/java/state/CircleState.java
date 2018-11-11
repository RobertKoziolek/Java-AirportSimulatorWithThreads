package state;

import airport.Airport;
import plane.PlaneImp;
import util.Vector2;

public class CircleState implements PlaneState {
    private final Airport airport;
    boolean circlingLeft = false;

    public CircleState(final Airport airport) {
        this.airport = airport;
    }

    @Override
    public void setup(final PlaneImp plane) {

    }

    @Override
    public void doAction(final PlaneImp plane) {
        if (airport.takeRunway()) {
            plane.setState(new LandingState(airport));
            return;
        } else {
            final Vector2 planePosition = plane.getPosition();
            if (circlingLeft) {
                planePosition.add(new Vector2(-4, 0));
                if (planePosition.getX() < airport.getPosition()
                                                  .getX() - 20) {
                    circlingLeft = false;
                }
            } else {
                planePosition.add(new Vector2(4, 0));
                if (planePosition.getX() > airport.getPosition()
                                                  .getX() + 20) {
                    circlingLeft = true;
                }
            }
        }
        plane.burnFuel();
    }
}

