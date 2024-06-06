package com.olek.snake_projekt;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    double interpolation = 0;
    final int TICKS_PER_SECOND = 25;
    final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    final int MAX_FRAMESKIP = 5;


    @FXML
    private Button exitBtn;

    @FXML
    private GridPane gridLayout;

    @FXML
    private Label scoreLabel;

    @FXML
    public Label warningText;

    private GameLoop gameLoop;


    public void setScene(Scene scene) {
        try {
            gameLoop = new GameLoop(gridLayout, scene, scoreLabel, warningText);
            gameLoop.start();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        exitBtn.setOnAction(event -> {
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        });

    }

}
