package de.thi.cocktails.fixture;

import de.thi.cocktails.domain.Cocktail;

import java.util.UUID;

public class CocktailFixture {

    public static Cocktail aCocktail() {
        Cocktail tequilaSunrise = new Cocktail("Tequila Sunrise " + UUID.randomUUID().toString());
        tequilaSunrise.addIngredient("Tequila");
        tequilaSunrise.addIngredient("Orangensaft");
        tequilaSunrise.addIngredient("Grenadine");
        tequilaSunrise.addIngredient("Orange");
        tequilaSunrise.setDescription("Mixen und genießen!");
        return tequilaSunrise;
    }
}
