package com.example.cookbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class RecipeListController {

    @FXML
    private Button goBackButton;

    @FXML
    private ChoiceBox<String> recipesChoiceBox;

    @FXML
    private TextArea ingredientsTextArea;

    @FXML
    private TextArea instructionsTextArea;

    @FXML
    public VBox ingredientsContainer;

    @FXML
    private Button saveButton;

    private Set<String> selectedIngredients = new HashSet<>();
    private String currentRecipeTitle;

    @FXML
    private void initialize() {
        loadRecipes();
    }

    @FXML
    private void loadRecipes() {
        ObservableList<String> recipeTitles = FXCollections.observableArrayList();

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

        recipesChoiceBox.setItems(recipeTitles);

        recipesChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                loadRecipeDetails(newValue);
            }
        });
    }

    @FXML
    private void loadRecipeDetails(String recipeTitle) {
        currentRecipeTitle = recipeTitle;

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:data/recipes.db")) {
            String query = "SELECT ingredients, instructions FROM recipes WHERE title = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, recipeTitle);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        ingredientsTextArea.setText(resultSet.getString("ingredients"));
                        instructionsTextArea.setText(resultSet.getString("instructions"));
                    }
                }
            }

            loadIngredientsForRecipe(recipeTitle);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Błąd", "Nie udało się załadować szczegółów przepisu.");
        }
    }

    private void loadIngredientsForRecipe(String recipeTitle) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:data/recipes.db")) {
            String query = "SELECT i.name FROM ingredients i " +
                    "JOIN recipe_ingredients ri ON i.id = ri.ingredient_id " +
                    "JOIN recipes r ON ri.recipe_id = r.id WHERE r.title = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, recipeTitle);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (ingredientsContainer != null) {
                        ingredientsContainer.getChildren().clear();

                        selectedIngredients.clear();
                        while (resultSet.next()) {
                            String ingredientName = resultSet.getString("name");
                            selectedIngredients.add(ingredientName);
                        }
                    }
                }
            }

            String allIngredientsQuery = "SELECT name FROM ingredients";
            try (PreparedStatement stmt = connection.prepareStatement(allIngredientsQuery);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String ingredientName = rs.getString("name");
                    CheckBox checkBox = new CheckBox(ingredientName);

                    if (selectedIngredients.contains(ingredientName)) {
                        checkBox.setSelected(true);
                    }

                    checkBox.setDisable(true);
                    checkBox.setOnAction(e -> handleIngredientSelection(checkBox));

                    ingredientsContainer.getChildren().add(checkBox);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Błąd", "Nie udało się załadować składników przepisu.");
        }
    }

    private void handleIngredientSelection(CheckBox checkBox) {
        String ingredientName = checkBox.getText();
        String currentText = ingredientsTextArea.getText().trim();

        if (checkBox.isSelected()) {
            if (!currentText.isEmpty() && !currentText.endsWith(",") && !currentText.endsWith(" ")) {
                ingredientsTextArea.appendText(", " + ingredientName);
            } else if (currentText.isEmpty()) {
                ingredientsTextArea.appendText(ingredientName);
            }

            addIngredientToRecipe(ingredientName);
        } else {
            String newText = currentText.replace(ingredientName + ", ", "")
                    .replace(", " + ingredientName, "")
                    .replace(ingredientName, "");

            ingredientsTextArea.setText(newText.trim());

            removeIngredientFromRecipe(ingredientName);
        }
    }


    private void addIngredientToRecipe(String ingredientName) {
        String selectedRecipe = recipesChoiceBox.getValue();

        if (selectedRecipe == null) return;

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:data/recipes.db")) {
            String insertIngredientQuery = "INSERT INTO recipe_ingredients (recipe_id, ingredient_id) " +
                    "SELECT (SELECT id FROM recipes WHERE title = ?), id FROM ingredients WHERE name = ?";
            try (PreparedStatement stmt = connection.prepareStatement(insertIngredientQuery)) {
                stmt.setString(1, selectedRecipe);
                stmt.setString(2, ingredientName);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Błąd", "Nie udało się dodać składnika do przepisu.");
        }
    }

    private void removeIngredientFromRecipe(String ingredientName) {
        String selectedRecipe = recipesChoiceBox.getValue();

        if (selectedRecipe == null) return;

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:data/recipes.db")) {
            String deleteIngredientQuery = "DELETE FROM recipe_ingredients WHERE recipe_id = (SELECT id FROM recipes WHERE title = ?) " +
                    "AND ingredient_id = (SELECT id FROM ingredients WHERE name = ?)";
            try (PreparedStatement stmt = connection.prepareStatement(deleteIngredientQuery)) {
                stmt.setString(1, selectedRecipe);
                stmt.setString(2, ingredientName);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Błąd", "Nie udało się usunąć składnika z przepisu.");
        }
    }

    @FXML
    private void edytuj() {
        for (var node : ingredientsContainer.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                checkBox.setDisable(false);
            }
        }

        instructionsTextArea.setEditable(true);
        ingredientsTextArea.setEditable(true);
        saveButton.setVisible(true);
    }

    @FXML
    private void zapisz() {
        String selectedRecipe = recipesChoiceBox.getValue();
        if (selectedRecipe == null) {
            showAlert(Alert.AlertType.WARNING, "Błąd", "Nie wybrano przepisu do zapisania.");
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:data/recipes.db")) {
            String updateRecipeQuery = "UPDATE recipes SET ingredients = ?, instructions = ? WHERE title = ?";
            try (PreparedStatement stmt = connection.prepareStatement(updateRecipeQuery)) {
                stmt.setString(1, ingredientsTextArea.getText());
                stmt.setString(2, instructionsTextArea.getText());
                stmt.setString(3, selectedRecipe);
                stmt.executeUpdate();
            }

            String deleteIngredientsQuery = "DELETE FROM recipe_ingredients WHERE recipe_id = (SELECT id FROM recipes WHERE title = ?)";
            try (PreparedStatement deleteStmt = connection.prepareStatement(deleteIngredientsQuery)) {
                deleteStmt.setString(1, selectedRecipe);
                deleteStmt.executeUpdate();
            }
            for (var node : ingredientsContainer.getChildren()) {
                if (node instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) node;
                    if (checkBox.isSelected()) {
                        String ingredientName = checkBox.getText();

                        String insertIngredientQuery = "INSERT INTO recipe_ingredients (recipe_id, ingredient_id) " +
                                "SELECT (SELECT id FROM recipes WHERE title = ?), id FROM ingredients WHERE name = ?";
                        try (PreparedStatement insertStmt = connection.prepareStatement(insertIngredientQuery)) {
                            insertStmt.setString(1, selectedRecipe);
                            insertStmt.setString(2, ingredientName);
                            insertStmt.executeUpdate();
                        }
                    }
                }
            }

            showAlert(Alert.AlertType.INFORMATION, "Sukces", "Przepis został zaktualizowany.");
            clearDetails();
            loadRecipes();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Błąd", "Nie udało się zapisać zmian.");
        }
    }

    @FXML
    private void usunPrzepis() {
        String selectedRecipe = recipesChoiceBox.getValue();

        if (selectedRecipe == null) {
            showAlert(Alert.AlertType.WARNING, "Błąd", "Nie wybrano przepisu do usunięcia.");
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Czy na pewno chcesz usunąć ten przepis?", ButtonType.YES, ButtonType.NO);
        confirmationAlert.setTitle("Potwierdzenie");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                try (Connection connection = DriverManager.getConnection("jdbc:sqlite:data/recipes.db")) {
                    String deleteIngredientsQuery = "DELETE FROM recipe_ingredients WHERE recipe_id = (SELECT id FROM recipes WHERE title = ?)";
                    try (PreparedStatement deleteIngredientsStmt = connection.prepareStatement(deleteIngredientsQuery)) {
                        deleteIngredientsStmt.setString(1, selectedRecipe);
                        deleteIngredientsStmt.executeUpdate();
                    }

                    String deleteRecipeQuery = "DELETE FROM recipes WHERE title = ?";
                    try (PreparedStatement deleteRecipeStmt = connection.prepareStatement(deleteRecipeQuery)) {
                        deleteRecipeStmt.setString(1, selectedRecipe);
                        deleteRecipeStmt.executeUpdate();
                    }

                    showAlert(Alert.AlertType.INFORMATION, "Sukces", "Przepis został pomyślnie usunięty.");
                    clearDetails();
                    loadRecipes();
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Nie udało się usunąć przepisu.");
                }
            }
        });
    }

    private void clearDetails() {
        recipesChoiceBox.getSelectionModel().clearSelection();
        ingredientsTextArea.clear();
        instructionsTextArea.clear();
        ingredientsContainer.getChildren().clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
