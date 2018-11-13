package gui.javafx;

import airport.Airport;
import airport.AirportManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import plane.PlaneManager;
import util.Vector2;

import java.util.LinkedList;
import java.util.List;

import static gui.Constants.*;

public class FxApplication extends Application {

    private PlaneManager planeManager;
    private List<PlaneLabel> planeLabels;
    private Group group;

    @Override
    public void start(final Stage primaryStage) {
        this.planeManager = new PlaneManager();
        initializeStage(primaryStage);
        visualizeAirports();
        createPlaneLabels();
        initializeWatcherThread();
    }

    private void initializeWatcherThread() {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!planeManager.isEmpty()) {
                    planeLabels.forEach(this::updateLabel);
                }
            }

            private void updateLabel(final PlaneLabel label) {
                if (!label.update()) {
                    Platform.runLater(() -> {
                        addCrashSite(label.getLayoutX(), label.getLayoutY());
                        label.setPlane(planeManager.createNew());
                    });
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void createPlaneLabels() {
        planeLabels = new LinkedList<>();
        planeManager.getPlanes()
                    .forEach(plane -> {
                        final PlaneLabel label = new PlaneLabel(plane);
                        planeLabels.add(label);
                        group.getChildren()
                             .add(label);
                    });
    }

    private void addCrashSite(final double x, final double y) {
        final Label label = new Label("*");
        label.setTextFill(Paint.valueOf("RED"));
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setPrefWidth(16);
        label.setPrefHeight(16);
        group.getChildren()
             .add(label);
    }

    private void visualizeAirports() {
        for (final Airport airport : AirportManager.getList()) {
            final Image image = new Image("file:src/img/airport.png");
            final ImageView imageView = new ImageView(image);
            imageView.setFitWidth(AIRPORT_WIDTH);
            imageView.setFitHeight(AIRPORT_HEIGHT);

            final Label label = new Label(airport.getName());
            label.setTextAlignment(TextAlignment.CENTER);
            label.setGraphic(imageView);
            final Vector2 position = airport.getPosition();
            label.setLayoutX(position.getX());
            label.setLayoutY(position.getY());
            group.getChildren()
                 .add(label);
        }
    }

    private void initializeStage(final Stage primaryStage) {
        primaryStage.setTitle(TITLE);
        final Pane root = new Pane();
        group = new Group();
        root.getChildren()
            .add(group);
        primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.show();
    }
}
