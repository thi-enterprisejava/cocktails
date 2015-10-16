package de.thi.cocktails.web.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Functional data model of a cocktail.
 */
public class Cocktail implements Serializable {

    private String name;
    private String description;
    private List<String> ingredients;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
