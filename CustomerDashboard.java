
package movieticketbookingsystem;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerDashboard {
	private final BookingSystem bookingSystem;

	public CustomerDashboard(BookingSystem bookingSystem) {
	    this.bookingSystem = bookingSystem;
	}

    public void show(Stage stage) {

        Label title = new Label("CUSTOMER DASHBOARD");
        title.setStyle(
            "-fx-font-size: 24px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;"
        );

        Button viewMovies = createButton("1. View Movies");
        Button viewShows = createButton("2. View Shows");
        Button bookTicket = createButton("3. Book Ticket");
        Button cancelTicket = createButton("4. Cancel Ticket");
        Button searchMovie = createButton("5. Search Movie");
        Button checkSeats = createButton("6. Check Seat Availability");
        Button bookingHistory = createButton("7. Booking History");
        Button registerCustomer = createButton("8. Register Customer");
        Button backButton = createButton("9. Back");

        viewMovies.setOnAction(e -> showDynamicMovies());
        viewShows.setOnAction(e -> showDynamicShows());
        bookTicket.setOnAction(e -> showBookTicket());
        cancelTicket.setOnAction(e -> showCancelTicket());
        searchMovie.setOnAction(e -> showSearchMovie());
        checkSeats.setOnAction(e -> showCheckSeats());
        bookingHistory.setOnAction(e -> showBookingHistory());
        registerCustomer.setOnAction(e -> showRegisterCustomer());
        backButton.setOnAction(e -> {
            new MovieBookingGUI().start(stage);
        });

        VBox layout = new VBox(
            12,
            title,
            viewMovies,
            viewShows,
            bookTicket,
            cancelTicket,
            searchMovie,
            checkSeats,
            bookingHistory,
            registerCustomer,
            backButton
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        layout.setStyle("-fx-background-color: #172033;");

        Scene scene = new Scene(layout, 550, 650);

        stage.setTitle("Customer Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(280);
        button.setPrefHeight(38);
        return button;
    }

    private void showMessage(String feature) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(feature);
        alert.setHeaderText(null);
        alert.setContentText(
            feature + " feature will be connected next."
        );
        alert.showAndWait();
    }

    private void showMovies() {

        Stage movieStage = new Stage();
        movieStage.setTitle("Available Movies");

        Label title = new Label("AVAILABLE MOVIES");
        title.setStyle(
            "-fx-font-size: 22px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;"
        );

        String movies =
            "M01 - The Silent Horizon | Sci-Fi | 450 BDT\n\n" +
            "M02 - Ironclad: New Dawn | Action | 850 BDT\n\n" +
            "M03 - Autumn Letters | Drama | 450 BDT\n\n" +
            "M04 - Midnight Circuit | Thriller | 850 BDT\n\n" +
            "M05 - Comet's Edge | Sci-Fi | 450 BDT\n\n" +
            "M06 - The Wanderer's Voyage | Adventure | 850 BDT\n\n" +
            "M07 - Crimson Alley | Crime | 450 BDT\n\n" +
            "M08 - Skyweb Guardian | Superhero | 850 BDT\n\n" +
            "M09 - Paper Moons | Animation | 450 BDT\n\n" +
            "M10 - The Last Ember | Fantasy | 450 BDT";

        Label movieList = new Label(movies);
        movieList.setStyle(
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;"
        );

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> movieStage.close());

        VBox layout = new VBox(20, title, movieList, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        layout.setStyle("-fx-background-color: #172033;");

        Scene scene = new Scene(layout, 600, 650);

        movieStage.setScene(scene);
        movieStage.show();
    }

    private void showShows() {

        Stage showStage = new Stage();
        showStage.setTitle("Available Movie Shows");

        Label title = new Label("AVAILABLE MOVIE SHOWS");
        title.setStyle(
            "-fx-font-size: 22px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;"
        );

        String shows =
            "S01 | The Silent Horizon | 6:00 PM | Hall A | 5 seats\n\n" +
            "S02 | Ironclad: New Dawn | 9:00 PM | Hall B | 3 seats\n\n" +
            "S03 | Autumn Letters | 5:30 PM | Hall A | 4 seats\n\n" +
            "S04 | Midnight Circuit | 8:00 PM | Hall C | 3 seats\n\n" +
            "S05 | Comet's Edge | 7:15 PM | Hall B | 5 seats\n\n" +
            "S06 | The Wanderer's Voyage | 6:45 PM | Hall D | 6 seats\n\n" +
            "S07 | Crimson Alley | 10:00 PM | Hall C | 4 seats\n\n" +
            "S08 | Skyweb Guardian | 4:30 PM | Hall D | 6 seats\n\n" +
            "S09 | Skyweb Guardian | 8:30 PM | Hall D | 6 seats\n\n" +
            "S10 | Paper Moons | 3:00 PM | Hall A | 5 seats\n\n" +
            "S11 | The Last Ember | 7:00 PM | Hall B | 4 seats";

        Label showList = new Label(shows);
        showList.setStyle(
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;"
        );

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> showStage.close());

        VBox layout = new VBox(
            20,
            title,
            showList,
            closeButton
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        layout.setStyle("-fx-background-color: #172033;");

        Scene scene = new Scene(layout, 750, 700);

        showStage.setScene(scene);
        showStage.show();
    }

private void showBookTicket() {

    Stage bookingStage = new Stage();
    bookingStage.setTitle("Book Movie Ticket");

    Label title = new Label("BOOK MOVIE TICKET");
    title.setStyle(
        "-fx-font-size: 24px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: white;"
    );

    Label instruction = new Label(
        "Enter your booking information"
    );
    instruction.setStyle("-fx-text-fill: lightgray;");

    TextField showIdField = new TextField();
    showIdField.setPromptText("Show ID (Example: S01)");
    showIdField.setMaxWidth(300);

    TextField customerIdField = new TextField();
    customerIdField.setPromptText("Customer ID (Example: C101)");
    customerIdField.setMaxWidth(300);

    TextField seatField = new TextField();
    seatField.setPromptText("Seat Number (Example: 1)");
    seatField.setMaxWidth(300);

    Button confirmButton = new Button("Confirm Booking");
    confirmButton.setPrefWidth(220);

    Button closeButton = new Button("Close");
    closeButton.setPrefWidth(220);

    confirmButton.setOnAction(e -> {//Existing booking form code

        String showId = showIdField.getText().trim();
        String customerId = customerIdField.getText().trim();
        String seatText = seatField.getText().trim();

        if (showId.isEmpty() ||
            customerId.isEmpty() ||
            seatText.isEmpty()) {

            showBookingAlert(
                Alert.AlertType.ERROR,
                "Missing Information",
                "Please fill in all fields."
            );
            return;
        }

        int seatNumber;

        try {
            seatNumber = Integer.parseInt(seatText);
        } catch (NumberFormatException ex) {
            showBookingAlert(
                Alert.AlertType.ERROR,
                "Invalid Seat Number",
                "Please enter a whole number."
            );
            return;
        }

        if (seatNumber <= 0) {
            showBookingAlert(
                Alert.AlertType.ERROR,
                "Invalid Seat Number",
                "Seat number must be greater than zero."
            );
            return;
        }


try {
    Ticket ticket = bookingSystem.bookTicket(
        showId,
        customerId,
        String.valueOf(seatNumber)
    );

    if (ticket != null) {
        showBookingAlert(
            Alert.AlertType.INFORMATION,
            "Booking Successful",
            ticket.toString()
        );
    } else {
        showBookingAlert(
            Alert.AlertType.ERROR,
            "Booking Failed",
            "Show ID or Customer ID not found."
        );
    }

} catch (SeatUnavailableException | ShowFullException ex) {
    showBookingAlert(
        Alert.AlertType.ERROR,
        "Booking Failed",
        ex.getMessage()
    );
}

    });

    closeButton.setOnAction(e -> bookingStage.close());

    VBox layout = new VBox(
        18,
        title,
        instruction,
        showIdField,
        customerIdField,
        seatField,
        confirmButton,
        closeButton
    );

    layout.setAlignment(Pos.CENTER);
    layout.setPadding(new Insets(30));
    layout.setStyle("-fx-background-color: #172033;");

    Scene scene = new Scene(layout, 500, 500);

    bookingStage.setScene(scene);
    bookingStage.show();
}

private void showBookingAlert(
        Alert.AlertType type,
        String title,
        String message) {

    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

private void showCancelTicket() {

    Stage cancelStage = new Stage();
    cancelStage.setTitle("Cancel Movie Ticket");

    Label title = new Label("CANCEL MOVIE TICKET");
    title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: white;");

    TextField showIdField = new TextField();
    showIdField.setPromptText("Show ID (Example: S01)");

    TextField seatField = new TextField();
    seatField.setPromptText("Seat Number (Example: 1)");

    Button confirmButton = new Button("Confirm Cancellation");
    Button closeButton = new Button("Close");

    confirmButton.setOnAction(e -> {

        String showId = showIdField.getText().trim();
        String seat = seatField.getText().trim();

        if (showId.isEmpty() || seat.isEmpty()) {
            showBookingAlert(
                Alert.AlertType.ERROR,
                "Missing Information",
                "Please enter Show ID and Seat Number."
            );
            return;
        }

        try {
            Ticket ticket = bookingSystem.cancelTicket(showId, seat);

            if (ticket != null) {
                showBookingAlert(
                    Alert.AlertType.INFORMATION,
                    "Cancellation Successful",
                    "Ticket cancelled successfully!\n\n" + ticket
                );
                cancelStage.close();
            } else {
                showBookingAlert(
                    Alert.AlertType.ERROR,
                    "Cancellation Failed",
                    "Show ID or booking not found."
                );
            }

        } catch (SeatNotBookedException ex) {
            showBookingAlert(
                Alert.AlertType.ERROR,
                "Cancellation Failed",
                ex.getMessage()
            );
        }
    });

    closeButton.setOnAction(e -> cancelStage.close());

    VBox layout = new VBox(
        18, title, showIdField, seatField,
        confirmButton, closeButton
    );

    layout.setAlignment(Pos.CENTER);
    layout.setPadding(new Insets(30));
    layout.setStyle("-fx-background-color: #172033;");

    showIdField.setMaxWidth(300);
    seatField.setMaxWidth(300);

    Scene scene = new Scene(layout, 500, 400);
    cancelStage.setScene(scene);
    cancelStage.show();
}



private void showSearchMovie() {

    Stage searchStage = new Stage();
    searchStage.setTitle("Search Movie");

    Label title = new Label("SEARCH MOVIE");
    title.setStyle(
        "-fx-font-size: 24px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: white;"
    );

    TextField searchField = new TextField();
    searchField.setPromptText("Enter movie name");
    searchField.setMaxWidth(300);

    Button searchButton = new Button("Search");
    searchButton.setPrefWidth(200);

    Button closeButton = new Button("Close");
    closeButton.setPrefWidth(200);

    Label resultLabel = new Label("");
    resultLabel.setStyle(
        "-fx-text-fill: white;" +
        "-fx-font-size: 14px;"
    );
    resultLabel.setWrapText(true);

    searchButton.setOnAction(e -> {

        String movieName = searchField.getText().trim().toLowerCase();

        if (movieName.isEmpty()) {
            resultLabel.setText("Please enter a movie name.");
            return;
        }

        StringBuilder results = new StringBuilder();

        for (Movie movie : bookingSystem.getMovies()) {

            if (movie.getMovieName().toLowerCase().contains(movieName)) {

                results.append("Movie ID: ")
                       .append(movie.getMovieId())
                       .append("\nMovie Name: ")
                       .append(movie.getMovieName())
                       .append("\n\n");
            }
        }

        if (results.length() == 0) {
            resultLabel.setText("Movie not found.");
        } else {
            resultLabel.setText(results.toString());
        }
    });

    closeButton.setOnAction(e -> searchStage.close());

    VBox layout = new VBox(
        18,
        title,
        searchField,
        searchButton,
        resultLabel,
        closeButton
    );

    layout.setAlignment(Pos.CENTER);
    layout.setPadding(new Insets(30));
    layout.setStyle("-fx-background-color: #172033;");

    Scene scene = new Scene(layout, 500, 450);

    searchStage.setScene(scene);
    searchStage.show();
}

private void showCheckSeats() {

    Stage seatStage = new Stage();
    seatStage.setTitle("Check Seat Availability");

    Label title = new Label("CHECK SEAT AVAILABILITY");
    title.setStyle(
        "-fx-font-size: 22px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: white;"
    );

    TextField showIdField = new TextField();
    showIdField.setPromptText("Enter Show ID (Example: S01)");
    showIdField.setMaxWidth(300);

    Button checkButton = new Button("Check Seats");
    checkButton.setPrefWidth(200);

    Button closeButton = new Button("Close");
    closeButton.setPrefWidth(200);

    Label resultLabel = new Label("");
    resultLabel.setStyle(
        "-fx-text-fill: white;" +
        "-fx-font-size: 15px;"
    );
    resultLabel.setWrapText(true);

    checkButton.setOnAction(e -> {

        String showId = showIdField.getText().trim();

        if (showId.isEmpty()) {
            resultLabel.setText("Please enter a Show ID.");
            return;
        }

        Show show = bookingSystem.findShowById(showId);

        if (show == null) {
            resultLabel.setText("Show ID not found.");
            return;
        }

        


StringBuilder seatDetails = new StringBuilder();

seatDetails.append("Show ID: ").append(show.getShowID())
           .append("\nMovie: ").append(show.getMovie().getMovieName())
           .append("\nTime: ").append(show.getShowTime())
           .append("\nHall: ").append(show.getHall())
           .append("\n\nTotal Seats: ").append(show.getTotalSeats())
           .append("\nAvailable Seats: ").append(show.getAvailableSeats())
           .append("\nBooked Seats: ")
           .append(show.getTotalSeats() - show.getAvailableSeats())
           .append("\n\nSEAT STATUS:\n");

for (int i = 1; i <= show.getTotalSeats(); i++) {

    String seatNumber = String.valueOf(i);

    if (show.getBookedSeats().containsKey(seatNumber)) {
        seatDetails.append("Seat ").append(i).append(" - BOOKED\n");
    } else {
        seatDetails.append("Seat ").append(i).append(" - AVAILABLE\n");
    }
}

resultLabel.setText(seatDetails.toString());

    });

    closeButton.setOnAction(e -> seatStage.close());

    VBox layout = new VBox(
        18,
        title,
        showIdField,
        checkButton,
        resultLabel,
        closeButton
    );

    layout.setAlignment(Pos.CENTER);
    layout.setPadding(new Insets(30));
    layout.setStyle("-fx-background-color: #172033;");

    Scene scene = new Scene(layout, 550, 600);
    seatStage.setScene(scene);
    seatStage.show();
}


private void showBookingHistory() {

    Stage historyStage = new Stage();
    historyStage.setTitle("Booking History");

    Label title = new Label("CUSTOMER BOOKING HISTORY");
    title.setStyle(
        "-fx-font-size: 22px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: white;"
    );

    TextField customerIdField = new TextField();
    customerIdField.setPromptText("Customer ID (Example: C101)");
    customerIdField.setMaxWidth(300);

    Button searchButton = new Button("View Booking History");
    searchButton.setPrefWidth(220);

    Button closeButton = new Button("Close");
    closeButton.setPrefWidth(220);

    Label resultLabel = new Label("");
    resultLabel.setStyle(
        "-fx-text-fill: white;" +
        "-fx-font-size: 14px;"
    );
    resultLabel.setWrapText(true);
    resultLabel.setMaxWidth(480);

    searchButton.setOnAction(e -> {

        String customerId = customerIdField.getText().trim();

        if (customerId.isEmpty()) {
            resultLabel.setText("Please enter a Customer ID.");
            return;
        }

        Customer customer = bookingSystem.findCustomerById(customerId);

        if (customer == null) {
            resultLabel.setText("Customer ID not found.");
            return;
        }

        if (customer.getBookingHistory().isEmpty()) {
            resultLabel.setText("No bookings found for this customer.");
            return;
        }

        StringBuilder history = new StringBuilder();

        history.append("Customer ID: ").append(customerId)
               .append("\n\nBOOKED TICKETS:\n\n");

        for (Ticket ticket : customer.getBookingHistory()) {
            history.append(ticket.toString()).append("\n\n");
        }

        resultLabel.setText(history.toString());
    });

    closeButton.setOnAction(e -> historyStage.close());

    VBox layout = new VBox(
        18,
        title,
        customerIdField,
        searchButton,
        resultLabel,
        closeButton
    );

    layout.setAlignment(Pos.CENTER);
    layout.setPadding(new Insets(30));
    layout.setStyle("-fx-background-color: #172033;");

    javafx.scene.control.ScrollPane scrollPane =
        new javafx.scene.control.ScrollPane(layout);

    scrollPane.setFitToWidth(true);
    scrollPane.setStyle("-fx-background: #172033;");

    Scene scene = new Scene(scrollPane, 550, 550);

    historyStage.setScene(scene);
    historyStage.show();
}

private void showRegisterCustomer() {

    Stage registerStage = new Stage();
    registerStage.setTitle("Register Customer");

    Label title = new Label("REGISTER NEW CUSTOMER");
    title.setStyle(
        "-fx-font-size: 22px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: white;"
    );

    TextField idField = new TextField();
    idField.setPromptText("Customer ID (Example: C107)");

    TextField nameField = new TextField();
    nameField.setPromptText("Full Name");

    TextField emailField = new TextField();
    emailField.setPromptText("Email Address");

    idField.setMaxWidth(300);
    nameField.setMaxWidth(300);
    emailField.setMaxWidth(300);

    Button registerButton = new Button("Register Customer");
    registerButton.setPrefWidth(220);

    Button closeButton = new Button("Close");
    closeButton.setPrefWidth(220);

    registerButton.setOnAction(e -> {

        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || email.isEmpty()) {
            showBookingAlert(
                Alert.AlertType.ERROR,
                "Missing Information",
                "Please fill in all fields."
            );
            return;
        }

        if (bookingSystem.findCustomerById(id) != null) {
            showBookingAlert(
                Alert.AlertType.ERROR,
                "Registration Failed",
                "Customer ID already exists."
            );
            return;
        }

        try {
            Customer customer = new Customer(id, name, email);

            boolean added = bookingSystem.addCustomer(customer);

            if (added) {
                showBookingAlert(
                    Alert.AlertType.INFORMATION,
                    "Registration Successful",
                    "Customer registered successfully!\n\n" +
                    "Customer ID: " + id +
                    "\nName: " + name +
                    "\nEmail: " + email
                );

                registerStage.close();
            } else {
                showBookingAlert(
                    Alert.AlertType.ERROR,
                    "Registration Failed",
                    "Customer ID already exists."
                );
            }

        } catch (IllegalArgumentException ex) {
            showBookingAlert(
                Alert.AlertType.ERROR,
                "Registration Failed",
                ex.getMessage()
            );
        }
    });

    closeButton.setOnAction(e -> registerStage.close());

    VBox layout = new VBox(
        18,
        title,
        idField,
        nameField,
        emailField,
        registerButton,
        closeButton
    );

    layout.setAlignment(Pos.CENTER);
    layout.setPadding(new Insets(30));
    layout.setStyle("-fx-background-color: #172033;");

    Scene scene = new Scene(layout, 500, 500);

    registerStage.setScene(scene);
    registerStage.show();
}

private void showDynamicShows() {

    Stage showStage = new Stage();
    showStage.setTitle("Available Shows");

    Label title = new Label("AVAILABLE MOVIE SHOWS");
    title.setStyle(
        "-fx-font-size: 24px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: white;"
    );

    javafx.scene.control.TextArea showArea =
        new javafx.scene.control.TextArea();

    showArea.setEditable(false);
    showArea.setWrapText(true);

    StringBuilder showList = new StringBuilder();

    for (Show show : bookingSystem.getShows()) {

        showList.append("Show ID: ")
                .append(show.getShowID())
                .append("\n");

        showList.append("Movie: ")
                .append(show.getMovie().getMovieName())
                .append("\n");

        showList.append("Time: ")
                .append(show.getShowTime())
                .append("\n");

        showList.append("Hall: ")
                .append(show.getHall())
                .append("\n");

        showList.append("Available Seats: ")
                .append(show.getAvailableSeats())
                .append("/")
                .append(show.getTotalSeats())
                .append("\n");

        showList.append("----------------------------\n");
    }

    if (showList.length() == 0) {
        showArea.setText("No shows available.");
    } else {
        showArea.setText(showList.toString());
    }

    Button closeButton = new Button("Close");
    closeButton.setOnAction(e -> showStage.close());

    VBox layout = new VBox(
        20,
        title,
        showArea,
        closeButton
    );

    layout.setAlignment(Pos.CENTER);
    layout.setPadding(new Insets(25));
    layout.setStyle("-fx-background-color: #172033;");

    Scene scene = new Scene(layout, 600, 550);

    showStage.setScene(scene);
    showStage.show();
}


private void showDynamicMovies() {

    try {
        Stage movieStage = new Stage();
        movieStage.setTitle("Available Movies");

        Label title = new Label("AVAILABLE MOVIES");
        title.setStyle(
            "-fx-font-size: 24px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;"
        );

        javafx.scene.control.TextArea movieArea =
            new javafx.scene.control.TextArea();

        movieArea.setEditable(false);
        movieArea.setWrapText(true);

        StringBuilder movieList = new StringBuilder();

        for (Movie movie : bookingSystem.getMovies()) {

            movieList.append(movie.toString())
                     .append("\n\n");
        }

        if (movieList.length() == 0) {
            movieArea.setText("No movies available.");
        } else {
            movieArea.setText(movieList.toString());
        }

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> movieStage.close());

        VBox layout = new VBox(
            20, title, movieArea, closeButton
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        layout.setStyle("-fx-background-color: #172033;");

        Scene scene = new Scene(layout, 600, 550);

        movieStage.setScene(scene);
        movieStage.show();

    } catch (Exception ex) {

        ex.printStackTrace();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("View Movies Error");
        alert.setHeaderText("Unable to display movies");
        alert.setContentText(ex.toString());
        alert.showAndWait();
    }
}



}
