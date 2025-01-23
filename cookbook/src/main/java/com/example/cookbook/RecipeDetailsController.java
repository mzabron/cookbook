package com.example.cookbook;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RecipeDetailsController {

    @FXML
    private TextField titleTextField;

    @FXML
    private TextArea ingredientsTextArea;

    @FXML
    private TextArea instructionsTextArea;

    public void setRecipeDetails(String title, String ingredients, String instructions) {
        titleTextField.setText(title);
        ingredientsTextArea.setText(ingredients);
        instructionsTextArea.setText(instructions);
    }
}

