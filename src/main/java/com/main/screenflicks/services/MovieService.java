package com.main.screenflicks.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.main.screenflicks.models.Movie;
import com.main.screenflicks.models.MovieRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	
	@Autowired 
	private MongoTemplate mongoTemplate;
	
    // Assume this list as our in-memory database for demonstration purposes
    private List<Movie> movies = new ArrayList<>();

    // Method to retrieve all movies
    public List<Movie> getMovies() {
    	Query query = new Query();
    	query.addCriteria(Criteria.where("category").is("movie"));
        return repository.findAll();
    }
    
    // Method to retrieve all tvshows
    public List<Movie> getTVShows() {
    	Query query = new Query();
    	query.addCriteria(Criteria.where("category").is("tvshow"));
        return repository.findAll();
    }

    // Method to add a new movie
    public void addMovie(Movie movie) {
    	repository.insert(movie);
    }
    
    // Method to get movies or TV shows by title
    public List<Movie> getMoviesByTitle(String title) {
    	Query query = new Query();
    	query.addCriteria(Criteria.where("title").is(title));
    	List<Movie> movies = mongoTemplate.find(query,Movie.class);
    	return movies; 
    }
    
    // Method to get featured movies
    public List<Movie> getFeaturedMovies() {
    	Query query = new Query();
    	query.addCriteria(Criteria.where("category").is("movie").and("featured").is(true));
    	List<Movie> movies = mongoTemplate.find(query,Movie.class);
    	return movies; 
    }
    
    public List<Movie> getFeaturedTVShows() {
    	Query query = new Query();
    	query.addCriteria(Criteria.where("category").is("tvshow").and("featured").is(true));
    	List<Movie> movies = mongoTemplate.find(query,Movie.class);
    	return movies; 
    }
    
    public Movie getMovieByID(String id) {
    	Query query = new Query();
    	query.addCriteria(Criteria.where("id").is(id));
    	Movie movie= mongoTemplate.findOne(query,Movie.class);
    	return movie; 
    }
    
    public void updateMovie(Movie movie) {
        mongoTemplate.save(movie);
    }
    
    public void deleteMovieById(String id) {
        repository.deleteById(id);
    }
}

