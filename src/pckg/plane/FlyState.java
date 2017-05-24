package pckg.plane;

import pckg.Airport;
import pckg.Vector2;

public class FlyState implements PlaneState {
    private Airport airport;
    private Vector2 moveVec;

    public FlyState(Airport airport){
        this.airport=airport;
    }

    @Override
    public void setup(Plane plane) {
        Vector2 position = plane.getPosition();
        double flyingAngle = Math.atan2(airport.getPosition().getX() - position.getX(), airport.getPosition().getY() - position.getY());
        moveVec = new Vector2(plane.getSpeed() * Math.sin(flyingAngle),plane.getSpeed() * Math.cos(flyingAngle));
    }

    @Override
    public void doAction(Plane plane) {
        Vector2 position = plane.getPosition();
        if (Vector2.distance(airport.getPosition(), position)>5f){
            plane.move(moveVec);
        } else {
            plane.setState(new CircleState(airport));
            return;
        }
        plane.burnFuel();
    }
}
