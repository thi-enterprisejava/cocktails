package de.thi.cocktails.service;

import de.thi.cocktails.domain.Cocktail;
import de.thi.cocktails.exception.CocktailAlreadyExistsException;
import de.thi.cocktails.interceptor.ProfilingInterceptor;
import de.thi.cocktails.repository.CocktailRepository;
import de.thi.cocktails.repository.CocktailRepositoryImpl;
import de.thi.cocktails.web.model.Search;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class CocktailServiceIntegrationTest {

    @EJB
    CocktailService cocktailService;

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(CocktailService.class)
                .addClass(Cocktail.class)
                .addClass(CocktailAlreadyExistsException.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                ;
        System.out.println(webArchive.toString(Formatters.VERBOSE));
        return webArchive;
    }


    @Test
    public void thatCocktailCanBeAdded() {
        Cocktail tequilaSunrise = new Cocktail("Tequila Sunrise " + new Date());
        tequilaSunrise.addIngredient("Tequila");
        tequilaSunrise.addIngredient("Orangensaft");
        tequilaSunrise.addIngredient("Grenadine");
        tequilaSunrise.addIngredient("Orange");
        tequilaSunrise.setDescription("Mixen und genießen!");

        cocktailService.add(tequilaSunrise);

        List<Cocktail> cocktailList = cocktailService.findByName(tequilaSunrise.getName());
        assertNotEquals(0, cocktailList.size());
        assertNotNull(cocktailList.get(0).getId());
    }

    @Test
    public void thatRandomCocktailIsReturned() throws Exception{

        Future<Cocktail> future1 = cocktailService.getRandom();
        Future<Cocktail> future2 = cocktailService.getRandom();

        System.out.println("Found cocktail: " + future1.get().getName());
        System.out.println("Found cocktail: " + future2.get().getName());
    }

}