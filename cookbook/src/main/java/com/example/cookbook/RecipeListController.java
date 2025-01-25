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
import java.sql.*;

public class RecipeListController {

    @FXML
    private ListView<String> recipesListView;

    private ObservableList<String> recipeTitles;

    @FXML
    private void initialize() {
        loadRecipes();
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
        if (event.getClickCount() == 2) {
            String selectedRecipe = recipesListView.getSelectionModel().getSelectedItem();
            if (selectedRecipe != null) {
                showRecipeDetails(selectedRecipe);
            }
        }
    }

    private void showRecipeDetails(String recipeTitle) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:data/recipes.db")) {
            String query = "SELECT ingredients, instructions FROM recipes WHERE title = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, recipeTitle);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String ingredients = resultSet.getString("ingredients");
                        String instructions = resultSet.getString("instructions");

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("recipe-details-view.fxml"));
                        Parent root = loader.load();

                        RecipeDetailsController controller = loader.getController();
                        controller.setRecipeDetails(recipeTitle, ingredients, instructions);

                        Stage stage = new Stage();
                        stage.setTitle("Szczegóły przepisu");
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button goBackButton;

    public void goBack(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
        }catch (IOException e){
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
