package airport;


import util.Vector2;

import java.util.HashMap;
import java.util.Map;

public class Airport {
    private final Vector2 runwayEndPosition, runwayStartPosition;
    private final boolean VACANT = false;
    private final boolean OCCUPIED = true;
    //TODO implement an airport with multiple runways
    private final Vector2 position;
    private final String name;
    private boolean runwayFree = true;
    //TODO aiport hangar as another class
    private HashMap<Vector2, Boolean> hangarSpaces;

    public Airport(final Vector2 position, final String name) {
        this.position = position;
        this.name = name;
        setUpHangarSpaces();
        Vector2 runway = new Vector2(position);
        runway.add(new Vector2(-10, 40));
        runwayStartPosition = runway;
        runway = new Vector2(position);
        runway.add(new Vector2(20, 40));
        runwayEndPosition = runway;
    }

    public Airport(final float x, final float y, final String name) {
        this(new Vector2(x, y), name);
    }

    public synchronized boolean takeRunway() {

        if (runwayFree) {
            runwayFree = false;
            return true;
        }
        return false;
    }

    public void freeRunway() {
        runwayFree = true;
    }

    private void setUpHangarSpaces() {
        hangarSpaces = new HashMap<>(12);
        final float x = getPosition().getX() + 41;
        final float y = getPosition().getY() - 6;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 4; ++j) {
                hangarSpaces.put(new Vector2(x + i * 20, y + j * 15), VACANT);
            }
        }
    }


    public synchronized Vector2 getHangarSpace() {
        for (final Map.Entry<Vector2, Boolean> space : hangarSpaces.entrySet()) {
            if (space.getValue() == VACANT) {
                space.setValue(OCCUPIED);
                return space.getKey();
            }
        }
        return Vector2.EMPTY;
    }

    public synchronized void freeHangarSpace(final Vector2 space) {
        if (hangarSpaces.containsKey(space)) {
            hangarSpaces.put(space, VACANT);
        }

    }

    public String getName() {
        return name;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getRunwayEndPosition() {
        return runwayEndPosition;
    }

    public Vector2 getRunwayStartPosition() {
        return runwayStartPosition;
    }

}
