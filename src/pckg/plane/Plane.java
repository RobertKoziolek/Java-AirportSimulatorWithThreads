package pckg.plane;

import pckg.Airport;
import pckg.AirportManager;

public class Plane extends  Thread{
    private float fuel, maxFuel;
    static private float fuelBase = 50, speed = 7f;
    private Airport airport;
    private PlaneState state;
    private Vector2 position;
    private boolean alive=true;


    public Plane(){
        this.airport= AirportManager.getRandom(null);
        this.position = new Vector2(airport.getX(), airport.getY());
        setState(new LandedState(airport));
        start();
    }

    public float getSpeed() {
        return speed;
    }

    public Vector2 getPosition(){return position;}

    {
        maxFuel = (float) ((Math.random()+0.5f)*fuelBase);
        fuel = maxFuel;

    }
    @Override
    public void run(){
        while(alive){
            state.doAction(this);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void burnFuel() {
        if (fuel-- < 0) {
            alive = false;
        }
    }

    public void setState(PlaneState state) {
        this.state = state;
        state.setup(this);
    }

    public void move(Vector2 moveVec) {
        position.add(moveVec);
    }

    public void moveTo(Vector2 target){
        position = new Vector2(target);
    }

    public void refuel() {
        fuel=maxFuel;
    }
    public int getFuelPercentage() {
        float f = (float) fuel / (float) maxFuel;
        if (f<=0) return 0;
        return (int) (100.f * f);
    }
}
