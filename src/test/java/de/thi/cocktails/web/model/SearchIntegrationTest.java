package de.thi.cocktails.web.model;

import de.thi.cocktails.domain.Cocktail;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class SearchIntegrationTest {

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addAsWebResource(new File("src/main/webapp/search.xhtml"))
                .addAsWebResource(new File("src/main/webapp/listResults.xhtml"))
                .addAsWebResource(new File("src/main/webapp/template.xhtml"))
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/faces-config.xml"))
                .addAsResource(new File("src/main/resources/messages.properties"))
                .addClass(Search.class)
                .addClass(Cocktail.class)
                ;
        System.out.println(webArchive.toString(Formatters.VERBOSE));
        return webArchive;
    }

    @Drone
    WebDriver browser;

    @ArquillianResource
    URL deploymentUrl;


    @Test
    public void searchEmpty() throws Exception{
        browser.get(deploymentUrl.toExternalForm() + "/search.xhtml");
        System.out.println(browser.getPageSource());
        assertEquals("Cocktails", browser.getTitle());
        browser.findElement(By.id("searchText")).sendKeys("Zombie");
        browser.findElement(By.id("searchButton")).click();
        System.out.println(browser.getPageSource());
    }

}