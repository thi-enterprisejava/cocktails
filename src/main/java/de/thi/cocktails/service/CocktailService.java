package de.thi.cocktails.service;

import de.thi.cocktails.domain.Cocktail;
import de.thi.cocktails.exception.CocktailAlreadyExistsException;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

@Stateless
public class CocktailService {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public CocktailService() {
        System.out.println("Create new CocktailService instance");
    }


    /**
     * @throws CocktailAlreadyExistsException
     */
    public Cocktail add(Cocktail cocktail) {

        if(findByName(cocktail.getName()).size() > 0) {
            throw new CocktailAlreadyExistsException(cocktail.getName());
        }

        em.persist(cocktail);

        return cocktail;
    }

    public List<Cocktail> findAll() {
        TypedQuery<Cocktail> query = em.createQuery("SELECT c FROM Cocktail as c", Cocktail.class);
        return query.getResultList();
    }

    public Cocktail findById(Long id) {
        return em.find(Cocktail.class, id);
    }

    public List<Cocktail> findByName(String name) {
        TypedQuery<Cocktail> query = em.createQuery("SELECT c FROM Cocktail as c WHERE c.name LIKE :name", Cocktail.class);
        query.setParameter("name", name + "%");
        return query.getResultList();
    }


    @Asynchronous
    public Future<Cocktail> getRandom() {

        List<Cocktail> cocktailList = findAll();
        int count = cocktailList.size();

        Random random = new Random();
        int randomNumber = random.nextInt(count);

        return new AsyncResult<>(cocktailList.get(randomNumber));
    }

}
