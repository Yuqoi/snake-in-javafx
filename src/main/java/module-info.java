module com.olek.snake_projekt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.olek.snake_projekt to javafx.fxml;
    exports com.olek.snake_projekt;
}