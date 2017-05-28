package pckg.plane;

import pckg.Airport;
import pckg.Main;
import pckg.Vector2;

public class FlyState implements PlaneState {
    private Airport airport;
    private Vector2 moveVec;

    public FlyState(Airport airport){
        this.airport=airport;
    }

    @Override
    public void setup(Plane plane) {
        float flyingAngle = Vector2.angle(airport.getPosition(), plane.getPosition());
        moveVec = new Vector2(Math.sin(flyingAngle),  Math.cos(flyingAngle)).mul(Main.PLANESPEED);
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
