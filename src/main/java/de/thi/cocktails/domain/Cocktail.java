package de.thi.cocktails.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Functional data model of a cocktail.
 */
public class Cocktail implements Serializable {

    private String name;
    private String description;
    private List<String> ingredients = new ArrayList<>();

    public Cocktail() {
    }

    public Cocktail(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " " + ingredients;
    }
}
