package com.example.cookbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RecipeListController {

    @FXML
    private Button goBackButton;

    @FXML
    private ListView<String> recipesListView;

    private ObservableList<String> recipeTitles;

    @FXML
    private void initialize() {
        loadRecipes(); // Ładowanie tytułów przepisów do ListView
    }

    @FXML
    private void loadRecipes() {
        recipeTitles = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:data/recipes.db")) {
            String query = "SELECT title FROM recipes";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                while (resultSet.next()) {
                    recipeTitles.add(resultSet.getString("title"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Błąd", "Nie udało się załadować przepisów.");
        }

        recipesListView.setItems(recipeTitles);
    }

    @FXML
    public void handleRecipeClick(MouseEvent event) {
        if (event.getClickCount() == 2) { // Podwójne kliknięcie na przepisie
            String selectedRecipe = recipesListView.getSelectionModel().getSelectedItem();
            if (selectedRecipe != null) {
                openRecipeDetailsView(selectedRecipe);
            }
        }
    }

    private void openRecipeDetailsView(String recipeTitle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("recipe-details-view.fxml"));
            Parent root = loader.load();

            // Przekazanie wybranego przepisu do kontrolera szczegółów
            RecipeDetailsController detailsController = loader.getController();
            detailsController.titleTextField(recipeTitle);

            Stage stage = (Stage) recipesListView.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Błąd", "Nie udało się otworzyć szczegółów przepisu.");
        }
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
