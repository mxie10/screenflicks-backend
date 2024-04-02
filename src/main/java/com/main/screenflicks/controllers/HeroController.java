package com.main.screenflicks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.screenflicks.models.Hero;
import com.main.screenflicks.services.HeroService;

@RestController
public class HeroController {
	
	@Autowired
	private HeroService service;
	
	@GetMapping("/heros")
	public List<Hero> getHeros()
	{
		return service.getHeros();
	}
	
	@PostMapping("/hero")
	public void addHero(@RequestBody Hero hero)
	{
		service.addHero(hero);
	}
}
