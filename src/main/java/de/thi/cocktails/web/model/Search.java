package de.thi.cocktails.web.model;

import de.thi.cocktails.domain.Cocktail;
import de.thi.cocktails.service.CocktailService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Model for Cocktail search.
 */
@Named
@SessionScoped
public class Search implements Serializable {

    private CocktailService cocktailService;

    @Inject
    public Search(CocktailService cocktailService) {
        System.out.println("Create new Search instance");
        this.cocktailService = cocktailService;
    }

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

        result = cocktailService.findByName(searchString);

        return "listResults";
    }

}
