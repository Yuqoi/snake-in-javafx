package com.olek.snake_projekt;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


import java.net.URISyntaxException;


public class GameController {

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

    public void exit(MouseEvent mouseEvent) {
        Platform.exit();
    }
}
