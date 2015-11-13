package de.thi.cocktails.web.model;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

@Dependent
public class FacesContextProducer {

    @Produces
    public FacesContext produceFacesContext() {
        return FacesContext.getCurrentInstance();
    }

}
