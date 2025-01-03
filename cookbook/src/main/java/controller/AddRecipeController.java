package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeController {

    @FXML
    private TextField recipeNameField;

    @FXML
    private TextArea instructionsArea;

    @FXML
    private ListView<String> ingredientListView;

    @FXML
    private Button addRecipeButton;

    private List<String> selectedIngredients = new ArrayList<>();

    @FXML
    public void initialize() {
        // Dodaj przykładowe składniki do wyboru
        ingredientListView.getItems().addAll("Mąka", "Cukier", "Jajka", "Masło");
    }

    @FXML
    public void addRecipe() {
        String name = recipeNameField.getText();
        String instructions = instructionsArea.getText();
        selectedIngredients.addAll(ingredientListView.getSelectionModel().getSelectedItems());

        Recipe newRecipe = new Recipe(name, selectedIngredients, instructions);
        System.out.println("Dodano przepis: " + newRecipe.getName());
    }
}
