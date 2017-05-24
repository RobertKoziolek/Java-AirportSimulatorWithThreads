package pckg.plane;

import pckg.Airport;

public class FlyState implements PlaneState {
    private Airport airport;
    private Vector2 moveVec;

    public FlyState(Airport airport){
        this.airport=airport;
    }

    @Override
    public void setup(Plane plane) {
        Vector2 position = plane.getPosition();
        double flyingAngle = Math.atan2(airport.getX() - position.getX(), airport.getY() - position.getY());
        moveVec = new Vector2(plane.getSpeed() * Math.sin(flyingAngle),plane.getSpeed() * Math.cos(flyingAngle));
    }

    @Override
    public void doAction(Plane plane) {
        Vector2 position = plane.getPosition();
        if (airport.getDistance(position)>5f) {
            plane.move(moveVec);
        } else {
            plane.setState(new CircleState(airport));
            return;
        }
        plane.burnFuel();
    }
}
