package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeListController {

    @FXML
    private ListView<String> recipeListView;

    private List<Recipe> allRecipes = new ArrayList<>();

    @FXML
    public void initialize() {
        // Dodaj przykładowe przepisy
        allRecipes.add(new Recipe("Ciasto czekoladowe", List.of("Mąka", "Cukier", "Jajka"), "Wymieszaj składniki i piecz przez 30 minut."));
        allRecipes.add(new Recipe("Omlet", List.of("Jajka", "Masło"), "Roztrzep jajka, usmaż na patelni."));

        // Wyświetl nazwy przepisów na liście
        for (Recipe recipe : allRecipes) {
            recipeListView.getItems().add(recipe.getName());
        }
    }
}
