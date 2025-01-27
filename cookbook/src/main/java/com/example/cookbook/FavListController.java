package com.example.cookbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.IOException;

public class FavListController {

    /*@FXML
    private ListView<String> recipesListView;

    @FXML
    private CheckBox c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20,
            c21, c22, c23, c24, c25, c26, c27, c28, c29, c30, c31, c32, c33, c34, c35, c36, c37, c38, c39, c40, c41, c42;

    @FXML
    private void initialize() {
        recipesListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Podwójne kliknięcie
                String selectedRecipe = recipesListView.getSelectionModel().getSelectedItem();
                if (selectedRecipe != null) {
                    String recipeTitle = selectedRecipe.split(" \\(")[0]; // Wyciągamy nazwę przepisu bez opisu wspólnych składników
                    showRecipeDetails(recipeTitle);
                }
            }
        });
    }


    private Map<String, Integer> findMatchingRecipes(List<String> selectedIngredients) {
        Map<String, Integer> recipeMatchCount = new HashMap<>();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:data/recipes.db")) {
            String query = """
                SELECT r.title, COUNT(ri.ingredient_id) as match_count
                FROM recipes r
                JOIN recipe_ingredients ri ON r.id = ri.recipe_id
                JOIN ingredients i ON ri.ingredient_id = i.id
                WHERE i.name IN (%s)
                GROUP BY r.id
                HAVING match_count >= 1
            """.formatted(String.join(",", selectedIngredients.stream().map(s -> "?").toArray(String[]::new)));

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                for (int i = 0; i < selectedIngredients.size(); i++) {
                    statement.setString(i + 1, selectedIngredients.get(i));
                }

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String recipeTitle = resultSet.getString("title");
                        int matchCount = resultSet.getInt("match_count");
                        recipeMatchCount.put(recipeTitle, matchCount);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recipeMatchCount;
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
    }*/

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

    @FXML
    private Button filtrujButton;

    public void filtruj(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ingredient-selection-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}