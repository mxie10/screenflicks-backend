package com.main.screenflicks.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("heros")
public class Hero {
	@Id
	private String id;
	private String title;
	private String actors[];
	
	public Hero() {}

	public Hero(String id, String title, String[] actors) {
		super();
		this.id = id;
		this.title = title;
		this.actors = actors;
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

	public String[] getActors() {
		return actors;
	}

	public void setActors(String[] actors) {
		this.actors = actors;
	};
}
