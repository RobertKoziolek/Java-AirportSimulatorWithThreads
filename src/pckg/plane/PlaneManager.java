package pckg.plane;

import java.util.LinkedList;
import java.util.List;

public class PlaneManager {
    private final List<Plane> planes;

    public PlaneManager(final int numberOfPlanes) {
        planes = new LinkedList<>();
        for (int i = 0; i < numberOfPlanes; ++i) {
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
}
