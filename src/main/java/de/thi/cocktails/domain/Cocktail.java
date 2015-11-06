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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cocktail cocktail = (Cocktail) o;

        if (name != null ? !name.equals(cocktail.name) : cocktail.name != null) return false;
        if (description != null ? !description.equals(cocktail.description) : cocktail.description != null)
            return false;
        return !(ingredients != null ? !ingredients.equals(cocktail.ingredients) : cocktail.ingredients != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        return result;
    }
}
