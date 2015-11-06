package de.thi.cockails.repository;

import de.thi.cocktails.domain.Cocktail;
import de.thi.cocktails.repository.CocktailRepository;

import java.util.Arrays;
import java.util.List;

public class CocktailRepositoryMock implements CocktailRepository {

    @Override
    public void add(Cocktail cocktail) {
        return;
    }

    @Override
    public List<Cocktail> findAll() {
        return Arrays.asList(new Cocktail("Mojito"), new Cocktail("Gin Tonic"));
    }

    @Override
    public List<Cocktail> findByName(String name) {
        return Arrays.asList(new Cocktail("Gin Tonic"));
    }
}
