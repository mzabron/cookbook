package com.example.cookbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.IOException;

public class IngredientSelectionController {

    @FXML
    private VBox ingredientContainer;

    @FXML
    private ListView<String> recipesListView;

    private final List<CheckBox> dynamicCheckBoxes = new ArrayList<>();

    @FXML
    private Button goBackButton;

    @FXML
    private void initialize() {
        loadIngredients();

        recipesListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedRecipe = recipesListView.getSelectionModel().getSelectedItem();
                if (selectedRecipe != null) {
                    String recipeTitle = selectedRecipe.split(" \\(")[0];
                    showRecipeDetails(recipeTitle);
                }
            }
        });
    }

    private void loadIngredients() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:data/recipes.db")) {
            String query = "SELECT name FROM ingredients ORDER BY name";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    String ingredientName = resultSet.getString("name");
                    CheckBox checkBox = new CheckBox(ingredientName);
                    dynamicCheckBoxes.add(checkBox);
                    ingredientContainer.getChildren().add(checkBox);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void szukajPrzepisow() {
        List<String> selectedIngredients = getSelectedIngredients();

        if (selectedIngredients.isEmpty()) {
            recipesListView.setItems(FXCollections.observableArrayList("Brak zaznaczonych składników."));
            return;
        }

        Map<String, Integer> matchingRecipes = findMatchingRecipes(selectedIngredients);

        ObservableList<String> sortedRecipes = matchingRecipes.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(entry -> entry.getKey() + " (wspólnych składników: " + entry.getValue() + ")")
                .collect(FXCollections::observableArrayList, ObservableList::add, ObservableList::addAll);

        recipesListView.setItems(sortedRecipes);
    }

    private List<String> getSelectedIngredients() {
        List<String> selectedIngredients = new ArrayList<>();

        for (CheckBox checkBox : dynamicCheckBoxes) {
            if (checkBox.isSelected()) {
                selectedIngredients.add(checkBox.getText());
            }
        }

        return selectedIngredients;
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
    }

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
}
