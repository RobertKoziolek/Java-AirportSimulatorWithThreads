package pckg.plane;

import pckg.*;

public class Plane extends  Thread implements PlaneInterface {
    private static int planeId=0;
    static private float fuelBase = Main.FUELBASE ;

    private float fuel, maxFuel;
    private Airport airport;
    private PlaneState state;
    private Vector2 position;
    private boolean alive=true;

    public Plane(){
        super(Integer.toString(planeId++));
        this.airport= AirportManager.getRandomOtherThan(null);
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

    public Vector2 getPosition(){return position;}
}
