package de.thi.cocktails.repository;

import de.thi.cocktails.domain.Cocktail;

import java.io.Serializable;
import java.util.List;

public interface CocktailRepository extends Serializable {

    void add(Cocktail cocktail);

    List<Cocktail> findAll();

    List<Cocktail> findByName(String name);
}
