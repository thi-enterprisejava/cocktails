package de.thi.cocktails.mock;

import de.thi.cocktails.domain.Cocktail;
import de.thi.cocktails.service.CocktailService;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;

@SessionScoped
public class MockedCocktailService implements CocktailService, Serializable {

    @Override
    public Cocktail add(Cocktail cocktail) {
        return null;
    }

    @Override
    public List<Cocktail> findAll() {
        return null;
    }

    @Override
    public Cocktail findById(Long id) {
        return null;
    }

    @Override
    public List<Cocktail> findByName(String name) {
        return null;
    }

    @Override
    public Future<Cocktail> getRandom() {
        return null;
    }
}
