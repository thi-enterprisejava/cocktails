package de.thi.cocktails.web.model;

import de.thi.cocktails.repository.CocktailRepositoryMock;
import de.thi.cocktails.domain.Cocktail;
import de.thi.cocktails.service.CocktailService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class SearchTest {

    /**
     * class under test
     */
    Search search;
    private CocktailService mockedCocktailService;

    @Before
    public void setUp() throws Exception {
        mockedCocktailService = Mockito.mock(CocktailService.class);
        search = new Search(mockedCocktailService);
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
        when(mockedCocktailService.findByName(any()))
                .thenReturn(Arrays.asList(new Cocktail("Gin Tonic")));

        search.doSearch();
        assertEquals(1, search.getResult().size());
        assertEquals(new Cocktail("Gin Tonic"), search.getResult().get(0));
    }


}