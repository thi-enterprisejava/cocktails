package de.thi.cocktails.web.model;

import de.thi.cocktails.repository.CocktailRepository;
import de.thi.cocktails.domain.Cocktail;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class SelectedCocktail implements Serializable {

    private final CocktailRepository cocktailRepository;
    private final FacesContext facesContext;

    @Inject
    public SelectedCocktail(CocktailRepository cocktailRepository,
                            FacesContext facesContext) {
        this.cocktailRepository = cocktailRepository;
        this.facesContext = facesContext;
    }

    private Cocktail cocktail;
    private String newIngredient;

    private String cocktailName;

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

    public String getCocktailName() {
        return cocktailName;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
    }


    //***********************************************************************
    // Action Methods
    //***********************************************************************

    public void init() {
        System.out.println("SelectedCocktail#init");
        List<Cocktail> list = cocktailRepository.findByName(cocktailName);
        if(list.size() > 0) {
            System.out.println("Found cocktail: " + list.get(0));
            cocktail = list.get(0);
        } else {
            System.out.println("No cocktail " + cocktailName + " found.");
        }
    }

    public String doSave() {
        System.out.println("doSave");

        cocktailRepository.add(cocktail);

        // TODO read from resource bundle.
        facesContext.addMessage(null, new FacesMessage("Cocktail added"));

        return "details.xhtml?faces-redirect=true&cocktail="+cocktail.getName();
    }

    public String doAddIngredient() {
        System.out.println("doAddIngredient");

        cocktail.addIngredient(newIngredient);
        newIngredient=null;

        return null;

    }


}
