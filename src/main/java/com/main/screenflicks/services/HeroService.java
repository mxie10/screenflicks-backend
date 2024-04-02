package com.main.screenflicks.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.screenflicks.models.Hero;
import com.main.screenflicks.models.HeroRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeroService {
	
	@Autowired
	private HeroRepository repository;
	
    // Assume this list as our in-memory database for demonstration purposes
    private List<Hero> heros = new ArrayList<>();

    // Method to retrieve all movies
    public List<Hero> getHeros() {
        return repository.findAll();
    }

    // Method to add a new movie
    public void addHero(Hero hero) {
    	repository.insert(hero);
    }
}
