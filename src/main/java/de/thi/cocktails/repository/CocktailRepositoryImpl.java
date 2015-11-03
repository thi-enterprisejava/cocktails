package de.thi.cocktails.repository;

import de.thi.cocktails.domain.Cocktail;
import de.thi.cocktails.repository.CocktailRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CocktailRepositoryImpl implements CocktailRepository {

    private List<Cocktail> cocktails = new ArrayList<>();

    public CocktailRepositoryImpl() {
        System.out.println("Create new CocktailRepositoryImpl...");
    }

    @Override
    public void add(Cocktail cocktail) {
        cocktails.add(cocktail);
    }

    @Override
    public List<Cocktail> findAll() {
        return cocktails;
    }

    @Override
    public List<Cocktail> findByName(String name) {
        return cocktails
                .stream()
                .filter(cocktail -> cocktail.getName().contains(name))
                .collect(Collectors.toList());
    }

}
