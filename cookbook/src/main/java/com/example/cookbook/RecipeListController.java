package com.example.cookbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.io.IOException;

public class RecipeListController {

    public Button usunPrzepisButton;

    @FXML
    private Button goBackButton;

    @FXML
    private ChoiceBox<String> recipesChoiceBox;

    @FXML
    private TextArea ingredientsTextArea;

    @FXML
    private TextArea instructionsTextArea;

    @FXML
    private void initialize() {
        loadRecipes(); // Ładowanie tytułów przepisów do ChoiceBoxa
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

        // Dodaj listener do zmiany wyboru
        recipesChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                loadRecipeDetails(newValue);
            }
        });
    }

    @FXML
    private void loadRecipeDetails(String recipeTitle) {
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
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Błąd", "Nie udało się załadować szczegółów przepisu.");
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
                    // Usuń powiązania z tabeli recipe_ingredients
                    String deleteIngredientsQuery = "DELETE FROM recipe_ingredients WHERE recipe_id = (SELECT id FROM recipes WHERE title = ?)";
                    try (PreparedStatement deleteIngredientsStmt = connection.prepareStatement(deleteIngredientsQuery)) {
                        deleteIngredientsStmt.setString(1, selectedRecipe);
                        deleteIngredientsStmt.executeUpdate();
                    }

                    // Usuń przepis z tabeli recipes
                    String deleteRecipeQuery = "DELETE FROM recipes WHERE title = ?";
                    try (PreparedStatement deleteRecipeStmt = connection.prepareStatement(deleteRecipeQuery)) {
                        deleteRecipeStmt.setString(1, selectedRecipe);
                        deleteRecipeStmt.executeUpdate();
                    }

                    showAlert(Alert.AlertType.INFORMATION, "Sukces", "Przepis został pomyślnie usunięty.");
                    clearDetails();
                    loadRecipes(); // Odśwież listę przepisów
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
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
