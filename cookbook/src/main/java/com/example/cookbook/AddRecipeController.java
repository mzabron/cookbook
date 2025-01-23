package com.example.cookbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddRecipeController {
    @FXML
    private TextField titleTextField;

    @FXML
    private Button goBackButton;

    @FXML
    private TextArea instructionsTextArea;

    @FXML
    private CheckBox c1, c2, c3, c4, c5, c6, c7, c8, c9, c10,
            c11, c12, c13, c14, c15, c16, c17, c18, c19, c20,
            c21, c22, c23, c24, c25, c26, c27, c28, c29, c30,
            c31, c32, c33, c34, c35, c36, c37, c38, c39, c40,
            c41, c42;

    private final List<CheckBox> checkBoxList = new ArrayList<>();

    @FXML
    public void initialize() {
        checkBoxList.addAll(List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10,
                c11, c12, c13, c14, c15, c16, c17, c18, c19, c20,
                c21, c22, c23, c24, c25, c26, c27, c28, c29, c30,
                c31, c32, c33, c34, c35, c36, c37, c38, c39, c40,
                c41, c42));
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

            // 1. Dodanie przepisu do tabeli `recipes`
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
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
