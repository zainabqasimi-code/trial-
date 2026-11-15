
package movieticketbookingsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class JavaFXTest extends Application {

    @Override
    public void start(Stage stage) {
        Button button = new Button("JavaFX is Working!");

        Scene scene = new Scene(button, 400, 250);

        stage.setTitle("JavaFX Test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
