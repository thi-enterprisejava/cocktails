package de.thi.cocktails.exception;

public class CocktailAlreadyExistsException extends RuntimeException {

    public CocktailAlreadyExistsException(String cocktailName) {
        super("Cocktail with name " + cocktailName + " already exists.");
    }

}
