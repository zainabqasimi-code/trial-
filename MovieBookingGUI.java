
package movieticketbookingsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MovieBookingGUI extends Application {
	private static final BookingSystem bookingSystem = main.getSystem();
	public static BookingSystem getBookingSystem() {
	    return bookingSystem;
	}

    @Override
    public void start(Stage stage) {
    	main.loadStarterData();

        Label title = new Label("MOVIE TICKET BOOKING SYSTEM");
        title.setStyle(
            "-fx-font-size: 22px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;"
        );

        Label subtitle = new Label("Welcome! Select your role.");
        subtitle.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-text-fill: lightgray;"
        );

        Button customerButton = new Button("Customer");
        Button adminButton = new Button("Admin");
        Button exitButton = new Button("Exit");

        customerButton.setPrefWidth(220);
        adminButton.setPrefWidth(220);
        exitButton.setPrefWidth(220);

        customerButton.setOnAction(e -> {
        	new CustomerDashboard(bookingSystem).show(stage);
        });

        adminButton.setOnAction(e -> showAdminLogin(stage));
        exitButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(
            20,
            title,
            subtitle,
            customerButton,
            adminButton,
            exitButton
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #172033;");

        Scene scene = new Scene(layout, 550, 400);

        stage.setTitle("Movie Ticket Booking System");
        stage.setScene(scene);
        stage.show();
    }

    private void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

private void showAdminLogin(Stage stage) {

    javafx.scene.control.Dialog<String> dialog =
        new javafx.scene.control.Dialog<>();

    dialog.setTitle("Admin Login");
    dialog.setHeaderText("Enter Admin Password");

    javafx.scene.control.PasswordField passwordField =
        new javafx.scene.control.PasswordField();

    passwordField.setPromptText("Enter password");

    javafx.scene.layout.VBox layout =
        new javafx.scene.layout.VBox(15);

    layout.setPadding(new javafx.geometry.Insets(20));

    layout.getChildren().addAll(
        new javafx.scene.control.Label("Admin Password:"),
        passwordField
    );

    dialog.getDialogPane().setContent(layout);

    javafx.scene.control.ButtonType loginButton =
        new javafx.scene.control.ButtonType(
            "Login",
            javafx.scene.control.ButtonBar.ButtonData.OK_DONE
        );

    dialog.getDialogPane().getButtonTypes().addAll(
        loginButton,
        javafx.scene.control.ButtonType.CANCEL
    );

    dialog.setResultConverter(button -> {
        if (button == loginButton) {
            return passwordField.getText();
        }
        return null;
    });

    java.util.Optional<String> result = dialog.showAndWait();

    if (result.isPresent()) {

        String password = result.get();

        if (password.equals("admin123")) {

            new AdminDashboard(bookingSystem).show(stage);

        } else {

            javafx.scene.control.Alert alert =
                new javafx.scene.control.Alert(
                    javafx.scene.control.Alert.AlertType.ERROR
                );

            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect admin password!");
            alert.showAndWait();
        }
    }
}

}
