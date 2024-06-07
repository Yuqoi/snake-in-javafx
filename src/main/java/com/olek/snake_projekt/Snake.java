package com.olek.snake_projekt;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.Objects;

public class Snake {


    private int row;
    private int column;
    private GridPane gridPane;
    private Scene scene;

    private ImageView snakeHead;

    public Keys currentDirection;

    LinkedList<int[]> bodyList;
    static int score = 1;

    String snakeHeadDirectionUrl;
    GameLoop gameLoop;

    public Snake(GridPane gridPane, int row, int column, Scene scene, GameLoop gameLoop) throws URISyntaxException {
        this.gridPane = gridPane;
        this.row = row;
        this.column = column;
        this.scene = scene;
        this.gameLoop = gameLoop;

        bodyList = new LinkedList<>();
        snakeHeadDirectionUrl = "assets/snake_parts/snake_head_right.png";
        setSnakeImage(snakeHeadDirectionUrl);

        this.gridPane.add(snakeHead,  column, row);
    }



    public void moveSnake(javafx.scene.input.KeyEvent event) {
        if (event.getCode() == KeyCode.W){
            currentDirection = Keys.UP;
            snakeHeadDirectionUrl = "assets/snake_parts/snake_head_up.png";
        }else if (event.getCode() == KeyCode.S){
            currentDirection = Keys.DOWN;
            snakeHeadDirectionUrl = "assets/snake_parts/snake_head_down.png";
        } else if (event.getCode() == KeyCode.A) {
            currentDirection = Keys.LEFT;
            snakeHeadDirectionUrl =  "assets/snake_parts/snake_head_left.png";
        } else if (event.getCode() == KeyCode.D) {
            currentDirection = Keys.RIGHT;
            snakeHeadDirectionUrl = "assets/snake_parts/snake_head_right.png";
        }
    }


    private void setSnakeImage(String url) throws URISyntaxException {
        ImageView imageView = new ImageView();

        URI uri = new URI(Objects.requireNonNull(getClass().getResource(url)).toString());
        Image image = new Image(String.valueOf(uri));

        imageView.setImage(image);
        snakeHead = imageView;
    }


    public void update(Banana banana){
        scene.setOnKeyPressed(this::moveSnake);
        accelerateSnake(currentDirection);
        detectIfTouched();
        detectIfTouchedBanana(banana);
    }

    public void render() throws URISyntaxException {
        destorySnake();

        for (int i = 1; i < bodyList.size(); i++){
            int[] part = bodyList.get(i);
            ImageView bodyPart = new ImageView(new Image(String.valueOf(getClass().getResource("assets/snake_parts/snake_body.png"))));
            gridPane.add(bodyPart, part[1], part[0]);
        }
        setSnakeImage(snakeHeadDirectionUrl);
        gridPane.add(snakeHead,  column, row);
    }

    private void addBody(){
        bodyList.add(new int[]{row,column});
    }

    public void accelerateSnake(Keys key){
        if (key != null){
            if (key == Keys.UP){
                row--;
            }else if (key == Keys.DOWN){
                row++;
            }else if (key == Keys.LEFT){
                column--;
            } else if (key == Keys.RIGHT) {
                column++;
            }
            if (row == 20){
                row = 19;
                collisionDetection();
            } else if (row == -1) {
                row = 0;
                collisionDetection();
            }else if (column == -1){
                column = 0;
                collisionDetection();
            }else if (column == 20){
                column =19;
                collisionDetection();
            }
            bodyList.addFirst(new int[]{row, column});
            if (bodyList.size() > score){
                bodyList.removeLast();
            }
        }
    }

    private void detectIfTouchedBanana(Banana banana){
        if (row == banana.row && column == banana.column){
            score++;
            banana.isEaten = true;
            addBody();
        }
    }

    private void detectIfTouched(){
        for (int i = 0; i < bodyList.size(); i++) {
            if (i != 0){
                if (row == bodyList.get(i)[0] && column == bodyList.get(i)[1]){
                    collisionDetection();
                }
            }
        }
    }

    private void collisionDetection(){
        destorySnake();
        gameLoop.stop();
    }

    public void destorySnake(){
        gridPane.getChildren().remove(snakeHead);
        for (int[] _ : bodyList) {
            ImageView bodyPart = new ImageView(new Image(String.valueOf(getClass().getResource("assets/snake_parts/snake_body.png"))));
            gridPane.getChildren().remove(bodyPart);
        }
    }

}
