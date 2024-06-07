package com.olek.snake_projekt;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GameLoop extends KeyAdapter {

    private static final double FRAME_RATE = 5.0;
    private long lastUpdateTime;

    private GridPane gridPane;
    private AnimationTimer timer;


    private List<int[]> snakeList;
    private Snake snake;

    private int[] bananaLocations;
    private Banana banana;


    private Board board;
    private Scene scene;




    Label scoreLabel;
    Label warningLabel;

    public GameLoop(GridPane gridPane, Scene scene, Label scoreLabel, Label warningLabel) throws URISyntaxException {
        this.gridPane = gridPane;
        this.board = new Board(gridPane);
        this.scene = scene;
        this.scoreLabel = scoreLabel;
        this.warningLabel = warningLabel;

        snakeList = new ArrayList<>();
        snakeList.add(new int[]{9,9});

        banana = new Banana(gridPane, (int) (Math.random() * 20), (int) (Math.random() * 20));
        snake = new Snake(gridPane, snakeList.get(0)[0], snakeList.get(0)[1], scene, GameLoop.this);


        lastUpdateTime = System.nanoTime();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double elapsedTime = (now - lastUpdateTime) / 1_000_000_000.0;
                if (elapsedTime >= 1.0 / FRAME_RATE) {
                    try {
                        update();
                        render();
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                    lastUpdateTime = now;
                }
            }
        };
    }


    public void start() {
        timer.start();
    }

    public void stop() {
        warningLabel.setText("Przegrałes rozpocznij od nowa");
        timer.stop();
        Util.createAlert("Przegrales", "Nie udało ci się wygrać", Alert.AlertType.CONFIRMATION);
    }

    private void update() throws URISyntaxException {
        banana.update(timer);
        snake.update(timer, banana);
        scoreLabel.setText("Punkty : " + (Snake.score - 1));
    }

    private void render() throws URISyntaxException {
        board.render();
        banana.render();
        snake.render();
    }







}
