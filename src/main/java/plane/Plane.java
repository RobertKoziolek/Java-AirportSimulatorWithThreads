package plane;


import util.Vector2;

public interface Plane {

    Vector2 getPosition();

    String getName();

    int getFuelPercentage();

    boolean isAlive();
}
