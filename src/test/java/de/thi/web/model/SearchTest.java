package de.thi.web.model;

import de.thi.cocktails.repository.CocktailRepository;
import de.thi.cocktails.repository.CocktailRepositoryImpl;
import de.thi.cocktails.web.model.Search;
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
        CocktailRepository cocktailRepository = new CocktailRepositoryImpl();
        search = new Search(cocktailRepository);
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

}