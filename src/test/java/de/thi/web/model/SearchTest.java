package de.thi.web.model;

import de.thi.cocktails.web.model.Search;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SearchTest {

    /**
     * class under test
     */
    Search search;

    @Before
    public void setUp() throws Exception {
        search = new Search();
    }

    @Test
    public void thatSearchNavigatesToListResults() throws Exception {
        String result = search.doSearch();
        assertEquals("listResults", result);
    }

    @Test
    public void thatResultIsNotEmpty() throws Exception {
        search.doSearch();
        assertThat("result should contain cocktails", search.getResult(), not(empty()));
    }

}