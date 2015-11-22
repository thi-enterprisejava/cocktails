package de.thi.cocktails.service;

import de.thi.cocktails.domain.Cocktail;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/cocktails")
public class CocktailServiceRest {

    @EJB
    CocktailService cocktailService;

    @GET
    @Produces("application/json")
    public List<Cocktail> findAll() {
        return cocktailService.findAll();
    }

    @GET
    @Path("/{cocktailId}")
    @Produces("application/json")
    public Cocktail findById(@PathParam("cocktailId") Long id) {
        return cocktailService.findById(id);
    }

    @POST
    @Consumes("application/json")
    public Cocktail post(Cocktail cocktail) {
        return cocktailService.add(cocktail);
    }

}
