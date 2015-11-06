package de.thi.cocktails.web.model;

import de.thi.cocktails.domain.Cocktail;
import de.thi.cocktails.repository.CocktailRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SelectedCocktailTest {

    /**
     * class under test
     */
    SelectedCocktail selectedCocktail;

    CocktailRepository mockedCocktailRepository;

    @Before
    public void setUp() throws Exception {
        mockedCocktailRepository = mock(CocktailRepository.class);
        FacesContext mockedFacesContext = mock(FacesContext.class);
        selectedCocktail = new SelectedCocktail(mockedCocktailRepository, mockedFacesContext);
    }

    @Test
    public void thatInitLoadsAvailableCocktail() throws Exception {
        selectedCocktail.setCocktailName("Zombie");
        when(mockedCocktailRepository.findByName("Zombie"))
                .thenReturn(Arrays.asList(new Cocktail("Zombie")));

        selectedCocktail.init();

        Cocktail cocktail = selectedCocktail.getCocktail();
        assertNotNull(cocktail);
        assertEquals(new Cocktail("Zombie"), cocktail);
        verify(mockedCocktailRepository).findByName("Zombie");
    }

    @Test
    public void thatNoCocktailIsLoadedWhenCocktailNameNotFound() throws Exception {
        selectedCocktail.setCocktailName("Zombie");
        when(mockedCocktailRepository.findByName("Zombie"))
                .thenReturn(new ArrayList<>());

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
        verify(mockedCocktailRepository).add(argumentCaptor.capture());
        Cocktail cocktailSubmittedToRepository = argumentCaptor.getValue();
        assertEquals("Mai Tai", cocktailSubmittedToRepository.getName());
    }
}