package com.example.cookbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddRecipeController {

    @FXML
    public VBox ingredientContainer;
    @FXML
    private TextField titleTextField;
    @FXML
    private Button goBackButton;
    @FXML
    private TextArea instructionsTextArea;
    @FXML
    private TextField dodajSkladnikTextField;

    private final List<CheckBox> checkBoxList = new ArrayList<>();

    @FXML
    public void initialize() {
        loadIngredientsFromDatabase();
    }

    private void loadIngredientsFromDatabase() {
        ingredientContainer.getChildren().clear();
        checkBoxList.clear();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:data/recipes.db")) {
            String query = "SELECT name FROM ingredients ORDER BY name";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                while (resultSet.next()) {
                    String ingredientName = resultSet.getString("name");
                    CheckBox checkBox = new CheckBox(ingredientName);
                    checkBoxList.add(checkBox);
                    ingredientContainer.getChildren().add(checkBox);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Błąd", "Nie udało się załadować składników z bazy danych.");
        }
    }

    @FXML
    public void dodajPrzepis() {
        String title = titleTextField.getText().trim();
        String instructions = instructionsTextArea.getText().trim();

        if (title.isEmpty() || instructions.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Błąd", "Tytuł i instrukcje nie mogą być puste!");
            return;
        }

        List<String> selectedIngredients = new ArrayList<>();
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isSelected()) {
                selectedIngredients.add(checkBox.getText());
            }
        }

        if (selectedIngredients.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Błąd", "Nie wybrano żadnych składników!");
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:data/recipes.db")) {
            connection.setAutoCommit(false);

            String insertRecipeSQL = "INSERT INTO recipes (title, ingredients, instructions) VALUES (?, ?, ?)";
            try (PreparedStatement insertRecipeStmt = connection.prepareStatement(insertRecipeSQL)) {
                insertRecipeStmt.setString(1, title);
                insertRecipeStmt.setString(2, String.join(", ", selectedIngredients)); // Lista składników jako string
                insertRecipeStmt.setString(3, instructions);
                insertRecipeStmt.executeUpdate();
            }

            String getLastInsertIdSQL = "SELECT last_insert_rowid() AS id";
            int recipeId;
            try (Statement getLastInsertIdStmt = connection.createStatement();
                 ResultSet resultSet = getLastInsertIdStmt.executeQuery(getLastInsertIdSQL)) {
                if (resultSet.next()) {
                    recipeId = resultSet.getInt("id");
                } else {
                    throw new SQLException("Nie udało się pobrać ID nowo dodanego przepisu!");
                }
            }

            String selectIngredientSQL = "SELECT id FROM ingredients WHERE name = ?";
            String insertRecipeIngredientSQL = "INSERT INTO recipe_ingredients (recipe_id, ingredient_id) VALUES (?, ?)";
            try (PreparedStatement selectIngredientStmt = connection.prepareStatement(selectIngredientSQL);
                 PreparedStatement insertRecipeIngredientStmt = connection.prepareStatement(insertRecipeIngredientSQL)) {

                for (String ingredient : selectedIngredients) {
                    selectIngredientStmt.setString(1, ingredient);
                    ResultSet ingredientResult = selectIngredientStmt.executeQuery();

                    if (ingredientResult.next()) {
                        int ingredientId = ingredientResult.getInt("id");

                        insertRecipeIngredientStmt.setInt(1, recipeId);
                        insertRecipeIngredientStmt.setInt(2, ingredientId);
                        insertRecipeIngredientStmt.executeUpdate();
                    } else {
                        showAlert(Alert.AlertType.WARNING, "Ostrzeżenie", "Nie znaleziono składnika: " + ingredient);
                    }
                }
            }

            connection.commit();

            showAlert(Alert.AlertType.INFORMATION, "Sukces", "Przepis został dodany pomyślnie!");

            clearForm();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił problem podczas dodawania przepisu.");
        }
    }

    @FXML
    public void dodajSkladnik() {
        String newIngredient = dodajSkladnikTextField.getText().trim();

        if (newIngredient.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Błąd", "Nazwa składnika nie może być pusta!");
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:data/recipes.db")) {
            String insertIngredientSQL = "INSERT INTO ingredients (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(insertIngredientSQL)) {
                statement.setString(1, newIngredient);
                statement.executeUpdate();

                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Składnik został dodany pomyślnie!");
                dodajSkladnikTextField.clear();
                loadIngredientsFromDatabase(); // Odświeżanie listy składników
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił problem podczas dodawania składnika.");
        }
    }

    @FXML
    public void usunSkladnik() {
        String ingredientToRemove = dodajSkladnikTextField.getText().trim();

        if (ingredientToRemove.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Błąd", "Nazwa składnika nie może być pusta!");
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:data/recipes.db")) {
            String deleteIngredientSQL = "DELETE FROM ingredients WHERE name = ?";
            try (PreparedStatement statement = connection.prepareStatement(deleteIngredientSQL)) {
                statement.setString(1, ingredientToRemove);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    showAlert(Alert.AlertType.INFORMATION, "Sukces", "Składnik został usunięty pomyślnie!");
                    dodajSkladnikTextField.clear();
                    loadIngredientsFromDatabase(); // Odświeżanie listy składników
                } else {
                    showAlert(Alert.AlertType.WARNING, "Błąd", "Składnik o podanej nazwie nie istnieje!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił problem podczas usuwania składnika.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearForm() {
        titleTextField.clear();
        instructionsTextArea.clear();
        for (CheckBox checkBox : checkBoxList) {
            checkBox.setSelected(false);
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
