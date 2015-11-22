package de.thi.cocktails.web.model;

import de.thi.cocktails.domain.Cocktail;
import de.thi.cocktails.service.CocktailService;
import de.thi.cocktails.service.CocktailServiceEjb;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class SelectedCocktail implements Serializable {

    private final CocktailService cocktailService;
    private final FacesContext facesContext;

    @Inject
    public SelectedCocktail(CocktailService cocktailService,
                            FacesContext facesContext) {
        this.cocktailService = cocktailService;
        this.facesContext = facesContext;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("New cocktail");
        cocktail = new Cocktail();
    }

    private Cocktail cocktail;
    private String newIngredient;

    private Long cocktailId;

    //***********************************************************************
    // Getter / Setter
    //***********************************************************************

    public Cocktail getCocktail() {
        return cocktail;
    }

    public void setCocktail(Cocktail cocktail) {
        this.cocktail = cocktail;
    }

    public void setNewIngredient(String newIngredient) {
        this.newIngredient = newIngredient;
    }

    public String getNewIngredient() {
        return newIngredient;
    }

    public Long getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(Long cocktailId) {
        this.cocktailId = cocktailId;
    }


    //***********************************************************************
    // Action Methods
    //***********************************************************************

    public void init() {
        System.out.println("SelectedCocktail#init");

        Cocktail foundCocktail = cocktailService.findById(cocktailId);
        if(foundCocktail != null) {
            System.out.println("Found cocktail: " + foundCocktail);
            cocktail = foundCocktail;
        } else {
            System.out.println("No cocktail for id " + cocktailId + " found.");
        }
    }

    public String doSave() {
        System.out.println("doSave");

        cocktailService.add(cocktail);

        return "details.xhtml?faces-redirect=true&cocktail="+cocktail.getName();
    }

    public String doAddIngredient() {
        System.out.println("doAddIngredient");

        cocktail.addIngredient(newIngredient);
        newIngredient=null;

        return null;

    }


}
