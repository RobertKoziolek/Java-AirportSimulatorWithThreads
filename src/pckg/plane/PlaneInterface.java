package pckg.plane;

import pckg.Vector2;

public interface PlaneInterface {

    public Vector2 getPosition();
    public String getName();
    public int getFuelPercentage();
    public boolean isAlive();
}
