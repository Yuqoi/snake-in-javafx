package com.olek.snake_projekt;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class Banana {

    GridPane gridPane;

    int row;
    int column;

    ImageView bananaImage;

    boolean isEaten = false;

    public Banana(GridPane gridPane, int row, int column) throws URISyntaxException {
        this.gridPane = gridPane;
        this.row = row;
        this.column = column;

        setBananaImage();

        this.gridPane.add(bananaImage, column, row);

    }

    private void setBananaImage() throws URISyntaxException {
        ImageView banana = new ImageView();

        URI uri = new URI(Objects.requireNonNull(getClass().getResource("assets/food/banana.png")).toString());
        Image image = new Image(String.valueOf(uri));

        banana.setImage(image);
        bananaImage = banana;
    }

    public void update() throws URISyntaxException {
        checkIfEaten();
    }

    public void render(){
        destroyBanana();

        gridPane.add(bananaImage, column, row);
    }

    private void checkIfEaten() {
        if (isEaten){
            row = (int) (Math.random() * 20);
            column = (int) (Math.random() * 20);
            isEaten = false;
        }
    }

    public void destroyBanana(){
        this.gridPane.getChildren().remove(bananaImage);
    }

}
