package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class MainController {

    @FXML
    private Button ingredientsButton;

    @FXML
    private Button recipesButton;

    @FXML
    private Button addRecipeButton;

    @FXML
    public void openIngredientSelection() throws Exception {
        loadNewScene("/fxml/ingredient-selection-view.fxml", "Wybierz składniki");
    }

    @FXML
    public void openRecipeList() throws Exception {
        loadNewScene("/fxml/recipe-list-view.fxml", "Lista przepisów");
    }

    @FXML
    public void openAddRecipe() throws Exception {
        loadNewScene("/fxml/add-recipe-view.fxml", "Dodaj przepis");
    }

    private void loadNewScene(String fxmlPath, String title) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
