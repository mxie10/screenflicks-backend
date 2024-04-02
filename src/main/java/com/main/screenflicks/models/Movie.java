package com.main.screenflicks.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movies")
public class Movie {
	
	@Id
	private String id;
	private String title;
	private String category;
	private String year;
	private String imageSrc;
	private String description;
	private String rating;
	private String[] type;
	private String[] actors;
	private String price;
	private Boolean featured;
	
	public Movie() {
		
	}
	
	public Movie(String id, String title,String category, String year, String imageSrc, String description, String rating,
			String[] type, String[] actors, String price, Boolean featured) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.imageSrc = imageSrc;
		this.description = description;
		this.rating = rating;
		this.type = type;
		this.actors = actors;
		this.price = price;
		this.featured = featured;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getImageSrc() {
		return imageSrc;
	}
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String[] getType() {
		return type;
	}
	public void setType(String[] type) {
		this.type = type;
	}
	public String[] getActors() {
		return actors;
	}
	public void setActors(String[] actors) {
		this.actors = actors;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	public Boolean getFeatured() {
		return featured;
	}

	public void setFeatured(Boolean featured) {
		this.featured = featured;
	}
	
}
