package com.main.screenflicks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.screenflicks.models.Movie;
import com.main.screenflicks.services.MovieService;

import utils.CustomizedResponse;

@RestController
public class MovieController {
	
	@Autowired
	private MovieService service;
	
	@GetMapping("/movies")
	public ResponseEntity<CustomizedResponse<List<Movie>>> getMovies()
	{
		var customizedResponse = new CustomizedResponse<List<Movie>>("list of movies and shows", service.getMovies());
		return new ResponseEntity<CustomizedResponse<List<Movie>>>(customizedResponse,HttpStatus.OK);
	}
	
	@PostMapping("/movies")
	public void addMovie(@RequestBody Movie movie)
	{
		service.addMovie(movie);
	}
	
	@GetMapping("/movies/title")
	public ResponseEntity<CustomizedResponse<List<Movie>>> getMoviesByTitle(@RequestParam(value="title") String title)
	{
		var customizedResponse = new CustomizedResponse<List<Movie>>("list of movies and shows", service.getMoviesByTitle(title));
		return new ResponseEntity<CustomizedResponse<List<Movie>>>(customizedResponse,HttpStatus.OK);
	}
	
	@GetMapping("/movies/featured/movie")
	public ResponseEntity<CustomizedResponse<List<Movie>>> getFeaturedMovies()
	{
		var customizedResponse = new CustomizedResponse<List<Movie>>("Get featured movies", service.getFeaturedMovies());
		return new ResponseEntity<CustomizedResponse<List<Movie>>>(customizedResponse,HttpStatus.OK);
	}
	
	@GetMapping("/movies/featured/tvshow")
	public ResponseEntity<CustomizedResponse<List<Movie>>> getFeaturedTVShows()
	{
		var customizedResponse = new CustomizedResponse<List<Movie>>("Get featured tv shows", service.getFeaturedTVShows());
		return new ResponseEntity<CustomizedResponse<List<Movie>>>(customizedResponse,HttpStatus.OK);
	}
	
	@GetMapping("/movies/id")
	public ResponseEntity<CustomizedResponse<Movie>> getMovieById(@RequestParam(value="id") String id)
	{
		Movie movie = service.getMovieByID(id);
		if(movie == null) {
			CustomizedResponse<Movie> errorResponse = new CustomizedResponse<>("Movie not found for id: " + id, null);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
		System.out.print(movie.getTitle());
		var customizedResponse = new CustomizedResponse<Movie>("Get featured tv shows", movie);
		return new ResponseEntity<CustomizedResponse<Movie>>(HttpStatus.OK);
	}
	
	@PutMapping("/movies/{id}")
    public ResponseEntity<CustomizedResponse<Movie>> updateMovie(@RequestParam(value="id") String id, @RequestBody Movie updatedMovie) {
        Movie existingMovie = service.getMovieByID(id);
        if (existingMovie == null) {
            CustomizedResponse<Movie> errorResponse = new CustomizedResponse<>("Movie not found for id: " + id, null);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        // Update the existing movie with the provided information
        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setCategory(updatedMovie.getCategory());
        existingMovie.setYear(updatedMovie.getYear());
        existingMovie.setImageSrc(updatedMovie.getImageSrc());
        existingMovie.setDescription(updatedMovie.getDescription());
        existingMovie.setRating(updatedMovie.getRating());
        existingMovie.setType(updatedMovie.getType());
        existingMovie.setActors(updatedMovie.getActors());
        existingMovie.setPrice(updatedMovie.getPrice());
        existingMovie.setFeatured(updatedMovie.getFeatured());

        // Save the updated movie
        service.updateMovie(existingMovie);

        // Return a response with the updated movie
        CustomizedResponse<Movie> response = new CustomizedResponse<>("Movie updated successfully", existingMovie);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
