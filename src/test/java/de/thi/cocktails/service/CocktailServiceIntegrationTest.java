package de.thi.cocktails.service;

import de.thi.cocktails.domain.Cocktail;
import de.thi.cocktails.exception.CocktailAlreadyExistsException;
import de.thi.cocktails.fixture.CocktailFixture;
import de.thi.cocktails.security.AuthenticatedUser;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

import static de.thi.cocktails.fixture.CocktailFixture.aCocktail;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class CocktailServiceIntegrationTest {

    @EJB
    CocktailService cocktailService;

    @EJB
    AuthenticatedUser authenticatedUser;

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(CocktailService.class)
                .addClass(Cocktail.class)
                .addClass(CocktailAlreadyExistsException.class)
                .addClass(AuthenticatedUser.class)
                .addClass(CocktailFixture.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("cocktailtest-ds.xml")
                ;
        System.out.println(webArchive.toString(Formatters.VERBOSE));
        return webArchive;
    }

    @Test
    public void thatCocktailCanBeAddedAsAuthenticatedUser() throws Exception {

        authenticatedUser.call(() -> {

            Cocktail tequilaSunrise = aCocktail();

            cocktailService.add(tequilaSunrise);

            List<Cocktail> cocktailList = cocktailService.findByName(tequilaSunrise.getName());
            assertNotEquals(0, cocktailList.size());
            assertNotNull(cocktailList.get(0).getId());

            return null;
        });

    }


    @Test(expected = EJBAccessException.class)
    public void thatCocktailCannotBeAddedAsAnonymous() {
        Cocktail tequilaSunrise = aCocktail();

        cocktailService.add(tequilaSunrise);

    }

    @Test
    @Ignore
    public void thatRandomCocktailIsReturned() throws Exception{
        addACocktail();

        Future<Cocktail> future1 = cocktailService.getRandom();
        Future<Cocktail> future2 = cocktailService.getRandom();

        assertNotNull("should find a random cocktail", future1.get());
        assertNotNull("random cocktail should have a name", future1.get().getName());
        assertNotNull("should find a second random cocktail", future2.get());
        assertNotNull("second random cocktail should have a name", future2.get().getName());
    }

    private void addACocktail() throws Exception {
        authenticatedUser.run(() -> cocktailService.add(aCocktail()));
    }

}