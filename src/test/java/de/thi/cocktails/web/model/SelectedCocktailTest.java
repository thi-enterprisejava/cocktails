package de.thi.cocktails.web.model;

import de.thi.cocktails.domain.Cocktail;
import de.thi.cocktails.service.CocktailService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.faces.context.FacesContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SelectedCocktailTest {

    /**
     * class under test
     */
    SelectedCocktail selectedCocktail;

    CocktailService mockedCocktailService;

    @Before
    public void setUp() throws Exception {
        mockedCocktailService = mock(CocktailService.class);
        FacesContext mockedFacesContext = mock(FacesContext.class);
        selectedCocktail = new SelectedCocktail(mockedCocktailService, mockedFacesContext);
    }

    @Test
    public void thatInitLoadsAvailableCocktail() throws Exception {
        selectedCocktail.setCocktailId(1L);
        when(mockedCocktailService.findById(1L))
                .thenReturn(new Cocktail("Zombie"));

        selectedCocktail.init();

        Cocktail cocktail = selectedCocktail.getCocktail();
        assertNotNull(cocktail);
        assertEquals(new Cocktail("Zombie"), cocktail);
        verify(mockedCocktailService).findById(1L);
    }

    @Test
    public void thatNoCocktailIsLoadedWhenCocktailIdNotFound() throws Exception {
        selectedCocktail.setCocktailId(2L);
        when(mockedCocktailService.findById(2L))
                .thenReturn(null);

        selectedCocktail.init();

        Cocktail cocktail = selectedCocktail.getCocktail();
        assertNull(cocktail);
    }

    @Test
    public void thatCocktailCanBeSaved() throws Exception {
        Cocktail maitai = new Cocktail("Mai Tai");
        maitai.addIngredient("Rum");
        maitai.addIngredient("Ananassaft");
        maitai.setDescription("Mischen");
        selectedCocktail.setCocktail(maitai);

        selectedCocktail.doSave();

        ArgumentCaptor<Cocktail> argumentCaptor = ArgumentCaptor.forClass(Cocktail.class);
        verify(mockedCocktailService).add(argumentCaptor.capture());
        Cocktail cocktailSubmittedToRepository = argumentCaptor.getValue();
        assertEquals("Mai Tai", cocktailSubmittedToRepository.getName());
    }
}