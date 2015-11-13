package de.thi.cocktails.repository;

import de.thi.cocktails.domain.Cocktail;
import de.thi.cocktails.repository.CocktailRepository;
import de.thi.cocktails.repository.CocktailRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CocktailRepositoryImplTest {

    CocktailRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new CocktailRepositoryImpl();
        repository.add(new Cocktail("Gin Tonic"));
        repository.add(new Cocktail("Mai Tai"));
        repository.add(new Cocktail("Zombie"));
    }

    @Test
    public void thatFindAllContainsAllElements() throws Exception {
        List<Cocktail> result = repository.findAll();
        assertEquals(3, result.size());
    }

    @Test
    public void thatOnlyMatchingCocktailsAreFound() throws Exception {
        List<Cocktail> result = repository.findByName("Gin");
        assertEquals(1, result.size());
        assertEquals("Gin Tonic", result.get(0).getName());
    }
}