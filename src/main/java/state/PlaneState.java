package state;


import plane.PlaneImp;

public interface PlaneState {
    void setup(PlaneImp plane);

    void doAction(PlaneImp plane);

}
