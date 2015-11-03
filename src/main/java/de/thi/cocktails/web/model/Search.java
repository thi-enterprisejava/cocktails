package de.thi.cocktails.web.model;

import de.thi.cocktails.repository.CocktailRepository;
import de.thi.cocktails.domain.Cocktail;

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

    private CocktailRepository cocktailRepository;

    @Inject
    public Search(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
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

        result = cocktailRepository.findByName(searchString);

        return "listResults";
    }

}
