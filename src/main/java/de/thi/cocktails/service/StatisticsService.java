package de.thi.cocktails.service;

import de.thi.cocktails.domain.Cocktail;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import java.util.List;

@Singleton
public class StatisticsService {

    @EJB
    CocktailService cocktailService;

    @Schedule(minute = "*", hour = "*", persistent = false)
    public void logStatistics() {
        List<Cocktail> cocktailList = cocktailService.findAll();
        System.out.println("Currently in database: " + cocktailList.size());
    }

}
