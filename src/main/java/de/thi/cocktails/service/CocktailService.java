package de.thi.cocktails.service;

import de.thi.cocktails.domain.Cocktail;
import de.thi.cocktails.repository.CocktailRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.resource.spi.work.TransactionContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CocktailService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void add(Cocktail cocktail) {
        em.persist(cocktail);
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
}
