package plane;

import gui.Constants;

import java.util.LinkedList;
import java.util.List;

public class PlaneManager {
    private final List<Plane> planes;

    public PlaneManager() {
        planes = new LinkedList<>();
        for (int i = 0; i < Constants.PLANES; ++i) {
            planes.add(new PlaneImp());
        }
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public Plane createNew() {
        final Plane plane = new PlaneImp();
        planes.add(plane);
        return plane;
    }

    public void remove(final Plane plane) {
        planes.remove(plane);
    }

    public boolean isEmpty() {
        return planes.isEmpty();
    }
}
