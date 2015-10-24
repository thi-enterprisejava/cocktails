package de.thi.cocktails.web.model;

import de.thi.cocktails.web.domain.Cocktail;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Model for Cocktail search.
 */
@Named
@SessionScoped
public class Search implements Serializable {

    private String searchString;
    private List<Cocktail> result;

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public List<Cocktail> getResult() {
        return result;
    }



    public String doSearch() {

        System.out.println("doSearch");

        // Simulate search result
        result = Arrays.asList(
                new Cocktail("Zombie"),
                new Cocktail("Gin Tonic"),
                new Cocktail("Cuba Libre")
        );
        return "listResults";
    }

}
