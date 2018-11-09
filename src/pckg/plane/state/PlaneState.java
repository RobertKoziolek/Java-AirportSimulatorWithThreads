package pckg.plane.state;

import pckg.plane.PlaneImp;

public interface PlaneState {
    void setup(PlaneImp plane);

    void doAction(PlaneImp plane);

}
