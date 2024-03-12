package microservice.ratingsdataservice.comtroller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservice.ratingsdataservice.model.Rating;

@RestController
@RequestMapping("/ratings")
public class RatingsServiceController {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}
}
