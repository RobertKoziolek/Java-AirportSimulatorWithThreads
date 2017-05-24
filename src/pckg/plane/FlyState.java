package pckg.plane;

import pckg.Airport;

public class FlyState implements PlaneState {
    private Airport airport;
    private double flyingAngle;

    public FlyState(Airport airport){
        this.airport=airport;
    }

    @Override
    public void setup(Plane plane) {
        Vector2 position = plane.getPosition();
        flyingAngle = Math.atan2(airport.getX() - position.getX(), airport.getY() - position.getY());
    }

    @Override
    public void doAction(Plane plane) {
        Vector2 position = plane.getPosition();
        if (airport.getDistance(position)>5f) {
            Vector2 moveVec = new Vector2(plane.getSpeed() * Math.sin(flyingAngle),plane.getSpeed() * Math.cos(flyingAngle));
            plane.move(moveVec);
        } else {
            plane.setState(new CircleState(airport));
            return;
        }
        plane.burnFuel();
    }
}
