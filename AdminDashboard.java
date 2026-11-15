
package movieticketbookingsystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminDashboard {

    private final BookingSystem bookingSystem;

    public AdminDashboard(BookingSystem bookingSystem) {
        this.bookingSystem = bookingSystem;
    }

    public void show(Stage stage) {

        Label title = new Label("ADMIN DASHBOARD");
        title.setStyle(
            "-fx-font-size: 24px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;"
        );

        Button addMovie = createButton("9. Add Movie");
        Button addShow = createButton("10. Add Show");
        Button deleteMovie = createButton("18. Delete Movie");
        Button totalMovies = createButton("11. Total Movies");
        Button totalBookings = createButton("12. Total Bookings");
        Button saveBookings = createButton("13. Save Bookings");
        Button readBookings = createButton("14. Read Saved Bookings");
        Button saveCustomers = createButton("15. Save Customers");
        Button readCustomers = createButton("16. Read Saved Customers");
        Button backButton = createButton("17. Back");

        addMovie.setOnAction(e -> showAddMovie());
        addShow.setOnAction(e -> showAddShow());

        totalMovies.setOnAction(e -> showInfo(
            "Total Movies",
            "Total Movies: " + bookingSystem.getMovies().size()
        ));

        totalBookings.setOnAction(e -> showInfo(
            "Total Bookings",
            "Total Bookings: " + bookingSystem.getAllTickets().size()
        ));
        deleteMovie.setOnAction(e -> showDeleteMovie());
        saveBookings.setOnAction(e -> saveAllBookings());
        readBookings.setOnAction(e -> showSavedBookings());
        saveCustomers.setOnAction(e -> saveAllCustomers());
        readCustomers.setOnAction(e -> showSavedCustomers());

        backButton.setOnAction(e -> new MovieBookingGUI().start(stage));

        VBox layout = new VBox(
            12, title,
            addMovie, addShow,deleteMovie,
            totalMovies, totalBookings,
            saveBookings, readBookings,
            saveCustomers, readCustomers,
            backButton
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        layout.setStyle("-fx-background-color: #172033;");

        Scene scene = new Scene(layout, 550, 650);

        stage.setTitle("Admin Dashboard");
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
        showInfo(feature, feature + " feature will be connected next.");
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

private void showAddMovie() {

    Stage movieStage = new Stage();
    movieStage.setTitle("Add New Movie");

    Label title = new Label("ADD NEW MOVIE");
    title.setStyle(
        "-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;"
    );

    javafx.scene.control.TextField idField =
        new javafx.scene.control.TextField();
    idField.setPromptText("Movie ID (Example: M11)");

    javafx.scene.control.TextField nameField =
        new javafx.scene.control.TextField();
    nameField.setPromptText("Movie Name");

    javafx.scene.control.TextField genreField =
        new javafx.scene.control.TextField();
    genreField.setPromptText("Genre");

    javafx.scene.control.TextField languageField =
        new javafx.scene.control.TextField();
    languageField.setPromptText("Language");

    javafx.scene.control.TextField durationField =
        new javafx.scene.control.TextField();
    durationField.setPromptText("Duration in minutes");

    javafx.scene.control.ComboBox<String> typeBox =
        new javafx.scene.control.ComboBox<>();
    typeBox.getItems().addAll("Regular", "Premium");
    typeBox.setPromptText("Select Movie Type");

    javafx.scene.control.ComboBox<String> optionBox =
        new javafx.scene.control.ComboBox<>();
    optionBox.setPromptText("Select Rating or Screen Type");

    typeBox.setOnAction(e -> {
        optionBox.getItems().clear();

        if ("Regular".equals(typeBox.getValue())) {
            for (RegularMovie.AgeRating rating :
                    RegularMovie.AgeRating.values()) {
                optionBox.getItems().add(rating.name());
            }
        } else if ("Premium".equals(typeBox.getValue())) {
            for (PremiumMovie.ScreenType screen :
                    PremiumMovie.ScreenType.values()) {
                optionBox.getItems().add(screen.name());
            }
        }

        optionBox.setValue(null);
    });

    Button addButton = new Button("Add Movie");
    addButton.setPrefWidth(220);

    Button closeButton = new Button("Close");
    closeButton.setPrefWidth(220);

    addButton.setOnAction(e -> {

        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String genre = genreField.getText().trim();
        String language = languageField.getText().trim();
        String durationText = durationField.getText().trim();
        String type = typeBox.getValue();
        String option = optionBox.getValue();

        if (id.isEmpty() || name.isEmpty() || genre.isEmpty()
                || language.isEmpty() || durationText.isEmpty()
                || type == null || option == null) {

            showInfo("Missing Information",
                "Please fill in all fields.");
            return;
        }

        try {
            double duration = Double.parseDouble(durationText);

            if (!Double.isFinite(duration) || duration <= 0) {
                showInfo("Invalid Duration",
                    "Duration must be a positive number.");
                return;
            }

            if (bookingSystem.findMovieById(id) != null) {
                showInfo("Add Movie Failed",
                    "Movie ID already exists.");
                return;
            }

            Movie movie;

            if ("Regular".equals(type)) {
                movie = new RegularMovie(
                    id, name, genre, language, duration,
                    RegularMovie.AgeRating.valueOf(option)
                );
            } else {
                movie = new PremiumMovie(
                    id, name, genre, language, duration,
                    PremiumMovie.ScreenType.valueOf(option)
                );
            }

            boolean added = bookingSystem.addMovie(movie);

            if (added) {
                showInfo("Movie Added Successfully",
                    "Movie ID: " + id +
                    "\nMovie Name: " + name +
                    "\nGenre: " + genre +
                    "\nLanguage: " + language +
                    "\nDuration: " + duration + " minutes" +
                    "\nType: " + type);

                movieStage.close();
            } else {
                showInfo("Add Movie Failed",
                    "Movie ID already exists.");
            }

        } catch (NumberFormatException ex) {
            showInfo("Invalid Duration",
                "Please enter a valid number.");
        } catch (IllegalArgumentException ex) {
            showInfo("Add Movie Failed", ex.getMessage());
        }
    });

    closeButton.setOnAction(e -> movieStage.close());

    VBox layout = new VBox(
        12, title,
        idField, nameField, genreField,
        languageField, durationField,
        typeBox, optionBox,
        addButton, closeButton
    );

    layout.setAlignment(Pos.CENTER);
    layout.setPadding(new Insets(25));
    layout.setStyle("-fx-background-color: #172033;");

    idField.setMaxWidth(300);
    nameField.setMaxWidth(300);
    genreField.setMaxWidth(300);
    languageField.setMaxWidth(300);
    durationField.setMaxWidth(300);

    Scene scene = new Scene(layout, 550, 650);
    movieStage.setScene(scene);
    movieStage.show();
}

private void showAddShow() {

    Stage showStage = new Stage();
    showStage.setTitle("Add New Show");

    Label title = new Label("ADD NEW MOVIE SHOW");
    title.setStyle(
        "-fx-font-size: 24px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: white;"
    );

    javafx.scene.control.TextField showIdField =
        new javafx.scene.control.TextField();
    showIdField.setPromptText("Show ID (Example: S12)");

    javafx.scene.control.TextField movieIdField =
        new javafx.scene.control.TextField();
    movieIdField.setPromptText("Movie ID (Example: M11)");

    javafx.scene.control.TextField timeField =
        new javafx.scene.control.TextField();
    timeField.setPromptText("Show Time (Example: 7:00 PM)");

    javafx.scene.control.TextField hallField =
        new javafx.scene.control.TextField();
    hallField.setPromptText("Hall (Example: Hall A)");

    javafx.scene.control.TextField seatsField =
        new javafx.scene.control.TextField();
    seatsField.setPromptText("Number of Seats (Example: 20)");

    Button addButton = new Button("Add Show");
    addButton.setPrefWidth(220);

    Button closeButton = new Button("Close");
    closeButton.setPrefWidth(220);

    addButton.setOnAction(e -> {

        String showId = showIdField.getText().trim();
        String movieId = movieIdField.getText().trim();
        String time = timeField.getText().trim();
        String hall = hallField.getText().trim();
        String seatsText = seatsField.getText().trim();

        if (showId.isEmpty() || movieId.isEmpty()
                || time.isEmpty() || hall.isEmpty()
                || seatsText.isEmpty()) {

            showInfo("Missing Information",
                "Please fill in all fields.");
            return;
        }

        try {
            int seats = Integer.parseInt(seatsText);

            if (seats <= 0) {
                showInfo("Invalid Seats",
                    "Number of seats must be greater than zero.");
                return;
            }

            Movie movie = bookingSystem.findMovieById(movieId);

            if (movie == null) {
                showInfo("Movie Not Found",
                    "Please enter a valid Movie ID.");
                return;
            }

            if (bookingSystem.findShowById(showId) != null) {
                showInfo("Add Show Failed",
                    "Show ID already exists.");
                return;
            }

            Show show = new Show(
                showId, movie, time, hall, seats
            );

            boolean added = bookingSystem.addShow(show);

            if (added) {
                showInfo("Show Added Successfully",
                    "Show ID: " + showId +
                    "\nMovie: " + movie.getMovieName() +
                    "\nTime: " + time +
                    "\nHall: " + hall +
                    "\nTotal Seats: " + seats);

                showStage.close();
            } else {
                showInfo("Add Show Failed",
                    "Show ID already exists.");
            }

        } catch (NumberFormatException ex) {
            showInfo("Invalid Seats",
                "Please enter a whole number.");
        } catch (IllegalArgumentException ex) {
            showInfo("Add Show Failed",
                ex.getMessage());
        }
    });

    closeButton.setOnAction(e -> showStage.close());

    VBox layout = new VBox(
        15,
        title,
        showIdField,
        movieIdField,
        timeField,
        hallField,
        seatsField,
        addButton,
        closeButton
    );

    layout.setAlignment(Pos.CENTER);
    layout.setPadding(new Insets(30));
    layout.setStyle("-fx-background-color: #172033;");

    showIdField.setMaxWidth(300);
    movieIdField.setMaxWidth(300);
    timeField.setMaxWidth(300);
    hallField.setMaxWidth(300);
    seatsField.setMaxWidth(300);

    Scene scene = new Scene(layout, 550, 550);

    showStage.setScene(scene);
    showStage.show();
}

private void saveAllBookings() {

    try {
        BookingFileManager fileManager =
            new BookingFileManager("bookings.txt", "customers.txt");

        fileManager.saveBookings(bookingSystem.getAllTickets());

        showInfo(
            "Save Bookings",
            "Bookings saved successfully!\n\n" +
            "Total Bookings: " +
            bookingSystem.getAllTickets().size() +
            "\nFile: bookings.txt"
        );

    } catch (Exception ex) {
        showInfo(
            "Save Failed",
            "Could not save bookings.\n" + ex.getMessage()
        );
    }
}


private void showSavedBookings() {

    Stage savedStage = new Stage();
    savedStage.setTitle("Read Saved Bookings");

    Label title = new Label("SAVED MOVIE BOOKINGS");
    title.setStyle(
        "-fx-font-size: 22px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: white;"
    );

    javafx.scene.control.TextArea resultArea =
        new javafx.scene.control.TextArea();

    resultArea.setEditable(false);
    resultArea.setWrapText(true);
    resultArea.setPrefHeight(350);

    try {
        java.nio.file.Path path =
            java.nio.file.Paths.get("bookings.txt");

        if (!java.nio.file.Files.exists(path)) {
            resultArea.setText(
                "No saved bookings file found.\n" +
                "Please use 13. Save Bookings first."
            );
        } else {
            String content = java.nio.file.Files.readString(path);

            if (content.trim().isEmpty()) {
                resultArea.setText("No saved bookings found.");
            } else {
                resultArea.setText(content);
            }
        }

    } catch (java.io.IOException ex) {
        resultArea.setText(
            "Error reading bookings: " + ex.getMessage()
        );
    }

    Button closeButton = new Button("Close");
    closeButton.setPrefWidth(200);
    closeButton.setOnAction(e -> savedStage.close());

    VBox layout = new VBox(
        20, title, resultArea, closeButton
    );

    layout.setAlignment(Pos.CENTER);
    layout.setPadding(new Insets(25));
    layout.setStyle("-fx-background-color: #172033;");

    Scene scene = new Scene(layout, 600, 500);

    savedStage.setScene(scene);
    savedStage.show();
}

private void saveAllCustomers() {

    try {
        java.nio.file.Path path =
            java.nio.file.Paths.get("customers.txt");

        java.util.List<String> customerDetails =
            new java.util.ArrayList<>();

        for (Customer customer : bookingSystem.getCustomers()) {
            customerDetails.add(customer.toString());
        }

        java.nio.file.Files.write(path, customerDetails);

        showInfo(
            "Save Customers",
            "Customers saved successfully!\n\n" +
            "Total Customers: " +
            bookingSystem.getCustomers().size() +
            "\nFile: customers.txt"
        );

    } catch (java.io.IOException ex) {
        showInfo(
            "Save Failed",
            "Could not save customers.\n" +
            ex.getMessage()
        );
    }
}

private void showSavedCustomers() {

    Stage savedStage = new Stage();
    savedStage.setTitle("Read Saved Customers");

    Label title = new Label("SAVED CUSTOMER RECORDS");
    title.setStyle(
        "-fx-font-size: 22px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: white;"
    );

    javafx.scene.control.TextArea resultArea =
        new javafx.scene.control.TextArea();

    resultArea.setEditable(false);
    resultArea.setWrapText(true);
    resultArea.setPrefHeight(350);

    try {
        java.nio.file.Path path =
            java.nio.file.Paths.get("customers.txt");

        if (!java.nio.file.Files.exists(path)) {
            resultArea.setText(
                "No saved customers file found.\n" +
                "Please use 15. Save Customers first."
            );
        } else {
            String content =
                java.nio.file.Files.readString(path);

            if (content.trim().isEmpty()) {
                resultArea.setText(
                    "No saved customers found."
                );
            } else {
                resultArea.setText(content);
            }
        }

    } catch (java.io.IOException ex) {
        resultArea.setText(
            "Error reading customers: " +
            ex.getMessage()
        );
    }

    Button closeButton = new Button("Close");
    closeButton.setPrefWidth(200);

    closeButton.setOnAction(e -> savedStage.close());

    VBox layout = new VBox(
        20,
        title,
        resultArea,
        closeButton
    );

    layout.setAlignment(Pos.CENTER);
    layout.setPadding(new Insets(25));
    layout.setStyle(
        "-fx-background-color: #172033;"
    );

    Scene scene = new Scene(layout, 600, 500);

    savedStage.setScene(scene);
    savedStage.show();
}

private void showDeleteMovie() {

    Stage deleteStage = new Stage();
    deleteStage.setTitle("Delete Movie");

    Label title = new Label("DELETE MOVIE");
    title.setStyle(
        "-fx-font-size: 24px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: white;"
    );

    javafx.scene.control.TextField movieIdField =
        new javafx.scene.control.TextField();

    movieIdField.setPromptText("Enter Movie ID (Example: M13)");
    movieIdField.setMaxWidth(300);

    Button deleteButton = new Button("Delete Movie");
    deleteButton.setPrefWidth(220);

    Button closeButton = new Button("Close");
    closeButton.setPrefWidth(220);

    deleteButton.setOnAction(e -> {

        String movieId = movieIdField.getText().trim();

        if (movieId.isEmpty()) {
            showInfo("Missing Information",
                "Please enter a Movie ID.");
            return;
        }

        Movie movie = bookingSystem.findMovieById(movieId);

        if (movie == null) {
            showInfo("Movie Not Found",
                "No movie found with ID: " + movieId);
            return;
        }

        javafx.scene.control.Alert confirm =
            new javafx.scene.control.Alert(
                javafx.scene.control.Alert.AlertType.CONFIRMATION
            );

        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText("Delete " + movie.getMovieName() + "?");
        confirm.setContentText(
            "Are you sure you want to delete this movie?"
        );

        java.util.Optional<javafx.scene.control.ButtonType> result =
            confirm.showAndWait();

        if (result.isPresent() &&
            result.get() == javafx.scene.control.ButtonType.OK) {

            try {
                boolean deleted = bookingSystem.deleteMovie(movieId);

                if (deleted) {
                    showInfo("Movie Deleted",
                        "Movie deleted successfully!");
                    deleteStage.close();
                } else {
                    showInfo("Delete Failed",
                        "Movie could not be deleted.");
                }
            } catch (IllegalStateException ex) {
                showInfo("Cannot Delete Movie", ex.getMessage());
            }
        }
    });

    closeButton.setOnAction(e -> deleteStage.close());

    VBox layout = new VBox(
        20,
        title,
        movieIdField,
        deleteButton,
        closeButton
    );

    layout.setAlignment(Pos.CENTER);
    layout.setPadding(new Insets(30));
    layout.setStyle("-fx-background-color: #172033;");

    Scene scene = new Scene(layout, 500, 350);

    deleteStage.setScene(scene);
    deleteStage.show();
}


}
