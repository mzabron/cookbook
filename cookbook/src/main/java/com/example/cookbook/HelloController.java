package com.example.cookbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;


public class HelloController {
    @FXML
    private Button goToIngredientSelectionButton;
    @FXML
    private Button goToRecipeListButton;
    @FXML
    private Button goToAddRecipeButton;

    @FXML
    public void goToIngredientSelection(ActionEvent actionEvent) {
        try {
            // Ładowanie nowego widoku
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ingredient-selection-view.fxml"));
            Parent root = loader.load();
            // Pobranie aktualnej sceny i ustawienie nowego widoku
            Stage stage = (Stage) goToIngredientSelectionButton.getScene().getWindow();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void goToRecipeList(ActionEvent actionEvent) {
        try {
            // Ładowanie nowego widoku
            FXMLLoader loader = new FXMLLoader(getClass().getResource("recipe-list-view.fxml"));
            Parent root = loader.load();
            // Pobranie aktualnej sceny i ustawienie nowego widoku
            Stage stage = (Stage) goToRecipeListButton.getScene().getWindow();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void goToAddRecipe(ActionEvent actionEvent) {
        try {
            // Ładowanie nowego widoku
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add-recipe-view.fxml"));
            Parent root = loader.load();
            // Pobranie aktualnej sceny i ustawienie nowego widoku
            Stage stage = (Stage) goToAddRecipeButton.getScene().getWindow();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}