package com.example.cookbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class RecipeListController {
    @FXML
    private Button goBackButton;

    public void goBack(ActionEvent actionEvent) {
        try {
            // Ładowanie nowego widoku
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();
            // Pobranie aktualnej sceny i ustawienie nowego widoku
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
