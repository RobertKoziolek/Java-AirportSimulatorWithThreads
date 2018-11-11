package state;


import airport.Airport;
import launcher.SwingMain;
import plane.PlaneImp;
import util.Vector2;

public class FlyState implements PlaneState {
    private final Airport airport;
    private Vector2 moveVec;

    public FlyState(final Airport airport) {
        this.airport = airport;
    }

    @Override
    public void setup(final PlaneImp plane) {
        final float flyingAngle = Vector2.angle(airport.getPosition(), plane.getPosition());
        moveVec = new Vector2(Math.sin(flyingAngle), Math.cos(flyingAngle)).mul(SwingMain.PLANESPEED);
    }

    @Override
    public void doAction(final PlaneImp plane) {
        final Vector2 position = plane.getPosition();
        if (Vector2.distance(airport.getPosition(), position) > 5f) {
            plane.move(moveVec);
        } else {
            plane.setState(new CircleState(airport));
            return;
        }
        plane.burnFuel();
    }
}
