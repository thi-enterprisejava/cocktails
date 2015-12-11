package de.thi.cocktails.web.model;

import de.thi.cocktails.domain.Cocktail;
import de.thi.cocktails.exception.CocktailAlreadyExistsException;
import de.thi.cocktails.fixture.CocktailFixture;
import de.thi.cocktails.security.AuthenticatedUser;
import de.thi.cocktails.service.CocktailService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.ejb.EJB;
import java.io.File;
import java.net.URL;
import java.util.List;

import static de.thi.cocktails.fixture.CocktailFixture.aCocktail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class SearchIntegrationTest {

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addAsWebResource(new File("src/main/webapp/search.xhtml"))
                .addAsWebResource(new File("src/main/webapp/listResults.xhtml"))
                .addAsWebResource(new File("src/main/webapp/template.xhtml"))
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/faces-config.xml"))
                .addAsResource(new File("src/main/resources/messages.properties"))
                .addClass(Search.class)
                .addClass(Cocktail.class)
                .addClass(CocktailService.class)
                .addClass(CocktailAlreadyExistsException.class)
                .addClass(AuthenticatedUser.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("cocktailtest-ds.xml")
                ;
        System.out.println(webArchive.toString(Formatters.VERBOSE));
        return webArchive;
    }

    @Drone
    WebDriver browser;

    @ArquillianResource
    URL deploymentUrl;

    @Test
    @RunAsClient
    public void searchEmpty() throws Exception{
        browser.get(deploymentUrl.toExternalForm() + "/search.xhtml");
        System.out.println(browser.getPageSource());
        assertEquals("Cocktails", browser.getTitle());
        browser.findElement(By.id("searchText")).sendKeys("Zombie");
        browser.findElement(By.id("searchButton")).click();
        System.out.println(browser.getPageSource());
    }

    @Test
    @RunAsClient
    @Ignore
    public void searchCocktail() throws Exception{

        browser.get(deploymentUrl.toExternalForm() + "/search.xhtml");
        System.out.println(browser.getPageSource());
        assertEquals("Cocktails", browser.getTitle());
        browser.findElement(By.id("searchText")).sendKeys("Tequila");
        browser.findElement(By.id("searchButton")).click();
        System.out.println(browser.getPageSource());

        List<WebElement> resultheadings = browser.findElements(By.className("resultheadings"));
        assertNotNull(resultheadings);
        assertNotEquals("should have elements", 0, resultheadings.size());
    }


}