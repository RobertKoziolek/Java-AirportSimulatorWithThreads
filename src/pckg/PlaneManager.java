package pckg;

import pckg.plane.Plane;

import java.util.LinkedList;
import java.util.List;

public class PlaneManager {
    private List<PlaneInterface> planes;

    public PlaneManager(int numberOfPlanes){
        planes = new LinkedList<>();
        for (int i=0;i<numberOfPlanes;++i){
            planes.add(new Plane());
        }
    }

    public List<PlaneInterface> getPlanes(){return planes;}

    public PlaneInterface createNew(){
        PlaneInterface plane = new Plane();
        planes.add(plane);
        return plane;
    }
    public void remove(PlaneInterface plane){
        planes.remove(plane);
    }
}
