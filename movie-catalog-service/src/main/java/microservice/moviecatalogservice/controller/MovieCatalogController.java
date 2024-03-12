package microservice.moviecatalogservice.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import microservice.moviecatalogservice.model.CatalogItem;
import microservice.moviecatalogservice.model.Movie;
import microservice.moviecatalogservice.model.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(String userId) {
		
		RestTemplate restTemplate = new RestTemplate();
		// .getForObject will take the JSON information, unmarshall it into Java code. Here, we store java code into the movie object
		// Movie movie = restTemplate.getForObject("http://localhost:8081/movies/foo", Movie.class);
		
		// get all rated movie IDs
		List<Rating> ratings = Arrays.asList(
				new Rating("1234", 4),
				new Rating("5678", 3)
		);
		// for each movie ID, call movie info service and get details
		
		// Put them all together
		 
		return ratings.stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8081/movies/" + rating.getMovieId(), Movie.class);
			// putting the two pieces from the separate microservices together. Two api calls, one for movie and one for rating
			return new CatalogItem(movie.getName(), "desc", rating.getRating());
			
		})
		.collect(Collectors.toList());	
	}
}
