package gui.javafx;


import javafx.scene.control.Label;
import plane.Plane;
import util.Vector2;

public class PlaneLabel extends Label {

    private Plane plane;

    public PlaneLabel(final Plane plane) {
        super(plane.getName());
        this.plane = plane;
    }

    public void setPlane(final Plane plane) {
        this.plane = plane;
        setText(plane.getName());
    }

    public boolean update() {
        final Vector2 position = plane.getPosition();
        setText(String.format("%s %d", plane.getName(), plane.getFuelPercentage()));
        setLayoutX(position.getX());
        setLayoutY(position.getY());
        setPrefWidth(32);
        setPrefHeight(32);
        return plane.isAlive();
    }
}
