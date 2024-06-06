package com.olek.snake_projekt;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Timer;

public class Board{
    GridPane gridPane;


    public ArrayList<Pane> listOfItems;


    public Board(GridPane gridPane) {
        this.gridPane = gridPane;
        listOfItems = new ArrayList<>();
    }



    public void update(){
    }

    public void render(){
        clearGridLayout();
        setGridLayout();
    }

    public void setGridLayout(){
        for (int row = 0; row < gridPane.getRowCount() ; row++) {
            for (int column = 0; column < gridPane.getColumnCount(); column++) {
                Pane pane = new Pane();
                if (row % 2 == 0){
                    if (column % 2 == 0){
                        pane.setStyle("-fx-background-color: rgba(183, 183, 183, 0.51);");
                    }else{
                        pane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.30)");
                    }
                }else{
                    if (column % 2 == 0){
                        pane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.30)");
                    }else{
                        pane.setStyle("-fx-background-color: rgba(183, 183, 183, 0.51);");
                    }
                }

                gridPane.add(pane, column, row);
            }
        }
    }

    public void clearGridLayout(){
        gridPane.getChildren().clear();
    }


}
