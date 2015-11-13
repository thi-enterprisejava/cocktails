package de.thi.cocktails.web.model;

import de.thi.cocktails.repository.CocktailRepositoryMock;
import de.thi.cocktails.domain.Cocktail;
import de.thi.cocktails.repository.CocktailRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SearchTest {

    /**
     * class under test
     */
    Search search;

    @Before
    public void setUp() throws Exception {
        CocktailRepository mockedCocktailRepository = new CocktailRepositoryMock();
        search = new Search(mockedCocktailRepository);
    }

    @Test
    public void thatSearchNavigatesToListResults() throws Exception {
        String result = search.doSearch();
        assertEquals("listResults", result);
    }

    @Test
    public void thatResultIsNotEmpty() throws Exception {
        search.doSearch();
        assertNotNull("result should contain cocktails", search.getResult());
    }

    @Test
    public void thatResultsContainsAllCocktailsFromRepository() throws Exception {
        search.doSearch();
        assertEquals(1, search.getResult().size());
        assertEquals(new Cocktail("Gin Tonic"), search.getResult().get(0));
    }


}