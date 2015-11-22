package de.thi.cocktails.web.model;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Named
@SessionScoped
public class User implements Serializable {
    public void logout() throws Exception {
        System.out.println("Logout...");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession();
        externalContext.setResponseStatus(401);
        externalContext.getResponseOutputWriter().write("<html><head><meta http-equiv='refresh' content='0;search.xhtml'></head></html>");
        facesContext.responseComplete();
    }
}
