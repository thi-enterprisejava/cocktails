package de.thi.cocktails.service;

import de.thi.cocktails.domain.Cocktail;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.List;

@WebService
@Stateless
public class CocktailServiceWs {

    @EJB
    CocktailService cocktailService;

    public List<Cocktail> findAll() {
        return cocktailService.findAll();
    }
}
