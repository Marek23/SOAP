package pl.com.home.soap.repository;

import javax.annotation.PostConstruct;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import io.spring.guides.gs_producing_web_service.Movie;

@Component
public class MovieRepository {
	private ArrayList<Movie> movies;

	@PostConstruct
	public void init() {
		movies = new ArrayList<Movie>();

		Movie one   = new Movie();
		Movie two   = new Movie();
		Movie three = new Movie();

		one.setName("One");
		one.setLength(1);
		one.setMark("1");

		two.setName("Two");
		two.setLength(2);
		two.setMark("2");

		three.setName("Three");
		three.setLength(3);
		three.setMark("3");

		movies.add(one);
		movies.add(two);
		movies.add(three);
	}

	public Movie findMovie(String name) {
		Assert.notNull(name, "The country's name must not be null");

		Movie out = null;

		for (Movie m: movies) {
			if (name.equals(m.getName()))
				out = m;
		}

		return out;
	}

	public void addMovie(Movie movie) {
		Assert.notNull(movie, "The movie name must not be null");

		movies.add(movie);
	}
}
