package gui.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FxApplication extends Application {
    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Airplanes simulator!");

        final VBox box = new VBox();
        box.getChildren()
           .add(new Label("tibia"));

        final BorderPane root = new BorderPane();
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }
}
