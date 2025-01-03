package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientSelectionController {

    @FXML
    private ListView<String> ingredientListView;

    @FXML
    private Button findRecipeButton;

    private List<Ingredient> allIngredients = new ArrayList<>();

    @FXML
    public void initialize() {
        // Dodaj przykładowe składniki
        allIngredients.add(new Ingredient("Mąka"));
        allIngredients.add(new Ingredient("Cukier"));
        allIngredients.add(new Ingredient("Jajka"));
        allIngredients.add(new Ingredient("Masło"));

        // Wyświetl składniki na liście
        for (Ingredient ingredient : allIngredients) {
            ingredientListView.getItems().add(ingredient.getName());
        }
    }

    @FXML
    public void findRecipes() {
        // Tutaj zaimplementuj logikę wyszukiwania przepisów na podstawie wybranych składników
        System.out.println("Wyszukiwanie przepisów na podstawie wybranych składników...");
    }
}
