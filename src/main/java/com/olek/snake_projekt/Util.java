package com.olek.snake_projekt;


import javafx.scene.control.Alert;

public class Util{

    public static void createAlert(String title, String message, Alert.AlertType type){
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setGraphic(null);
        a.setHeaderText(null);
        a.setContentText(message);
        a.show();
    }

}
