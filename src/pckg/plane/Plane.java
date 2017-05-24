package pckg.plane;

import pckg.Airport;
import pckg.AirportManager;
import pckg.Vector2;

public class Plane extends  Thread{
    private float fuel, maxFuel;
    static private float fuelBase = 126, speed = 9f;
    private Airport airport;
    private PlaneState state;
    private Vector2 position;
    private boolean alive=true;

    public Plane(){
        this.airport= AirportManager.getRandom(null);
        setState(new LandedState(airport));
        start();
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

    void burnFuel() {
        if (fuel-- < 0) {
            alive = false;
        }
    }

    void setState(PlaneState state) {
        this.state = state;
        state.setup(this);
    }

    void move(Vector2 moveVec) {
        position.add(moveVec);
    }

    void moveTo(Vector2 target){
        position = new Vector2(target);
    }

    void refuel() {
        fuel=maxFuel;
    }
    public int getFuelPercentage() {
        float f = (float) fuel / (float) maxFuel;
        if (f<=0) return 0;
        return (int) (100.f * f);
    }
    float getSpeed() {return speed;}

    public Vector2 getPosition(){return position;}
}
