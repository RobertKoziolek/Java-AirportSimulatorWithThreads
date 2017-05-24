package pckg.plane;

import pckg.Airport;

public class LandingState implements PlaneState {
    private Airport airport;

    public LandingState(Airport airport){
        this.airport=airport;
    }


    @Override
    public void setup(Plane plane) {
        Vector2 runwayBeginning = new Vector2(airport.getX(),airport.getY());
        runwayBeginning.add(new Vector2(-10,40));
        plane.moveTo(runwayBeginning);
    }
    int progress = 0;
    @Override
    public void doAction(Plane plane) {
        Vector2 position = plane.getPosition();
        position.add(new Vector2(5,0));
        if (progress++ > 6) {
            plane.setState(new LandedState(airport));
            plane.refuel();
            takePlaceInHangar();
            plane.moveTo(new Vector2(x,y));
            progress = 0;
            airport.freeRunway();
        }
    }
    int hangarSpaceTaken;
    float x,y;
    private void takePlaceInHangar() {
        hangarSpaceTaken = airport.getHangarSpace();

        x = airport.getX() + 38 + hangarSpaceTaken % 2 * 20;
        y = airport.getY() - 6 + hangarSpaceTaken / 2 * 15;
    }
}
