package de.thi.cocktails.web.model;

import de.thi.cocktails.web.domain.Cocktail;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class SelectedCocktail implements Serializable {

    private Cocktail cocktail;
    private String newIngredient;

    public SelectedCocktail() {
        this.cocktail = new Cocktail();
    }

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


    //***********************************************************************
    // Action Methods
    //***********************************************************************

    public String doShow(Cocktail cocktail) {
        this.cocktail = cocktail;
        return "details";
    }

    public String doSave() {
        System.out.println("doSave");

        // TODO read from resource bundle.
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cocktail added"));

        return "details";
    }

    public String doAddIngredient() {
        System.out.println("doAddIngredient");

        cocktail.addIngredient(newIngredient);
        newIngredient=null;

        return null;

    }


}
