package de.thi.cocktails.web.model;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

@RequestScoped
public class FacesContextProducer {

    @Produces
    public FacesContext produceFacesContext() {
        return FacesContext.getCurrentInstance();
    }

}
