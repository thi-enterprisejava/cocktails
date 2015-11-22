package de.thi.cocktails.service;

import de.thi.cocktails.domain.Cocktail;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Asynchronous;
import javax.ejb.Local;
import java.util.List;
import java.util.concurrent.Future;

@Local
public interface CocktailService {

    Cocktail add(Cocktail cocktail);

    List<Cocktail> findAll();

    Cocktail findById(Long id);

    List<Cocktail> findByName(String name);

    Future<Cocktail> getRandom();
}
