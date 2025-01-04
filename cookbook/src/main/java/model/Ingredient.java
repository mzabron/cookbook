package model;

public class Ingredient {
    private String name;
    private boolean selected;

    public Ingredient(String name) {
        this.name = name;
        this.selected = false;
    }

    // Gettery i settery
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected; }
}