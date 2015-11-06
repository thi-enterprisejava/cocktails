package de.thi.cocktails.web.model;

import javax.faces.context.FacesContext;
import javax.ws.rs.Produces;

public class FacesContextProducer {

    @Produces
    public FacesContext produceFacesContext() {
        return FacesContext.getCurrentInstance();
    }

}
