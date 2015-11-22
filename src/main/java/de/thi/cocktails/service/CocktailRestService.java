package de.thi.cocktails.service;

import de.thi.cocktails.domain.Cocktail;
import de.thi.cocktails.exception.CocktailAlreadyExistsException;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

@Path("/cocktails")
public class CocktailRestService {

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
