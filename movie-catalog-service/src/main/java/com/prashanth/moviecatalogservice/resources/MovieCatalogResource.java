package com.prashanth.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.prashanth.moviecatalogservice.models.CatalogItem;
import com.prashanth.moviecatalogservice.models.Movie;
import com.prashanth.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource
{

    // 1. Get all rated movie IDs (call the rating-data-service to get the list of rated movie IDs)
    // 2. For each movie ID, call movie-info-service and get details
    // 3. Put them all together

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") final String userId)
    {

        // final UserRating userRatings = restTemplate.getForObject("http://localhost:8083/ratingsdata/user/" + userId, UserRating.class);
        final UserRating userRatings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);

        return userRatings.getUserRating().stream().map(rating ->
        {
            // final Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            final Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(), "Test description", rating.getRating());

        }).collect(Collectors.toList());

        // return Collections.singletonList(new CatalogItem("Transformers", "test", 4));

    }

}
// ***WebClient usage - alternate to RestTemplate***
// final Movie movie = webClientBuilder.build() // builder pattern and gives the client
// .get() // get method
// .uri("http://localhost:8082/movies/" + rating.getMovieId()) // url that we need to access
// .retrieve() // go do the fetch
// .bodyToMono(Movie.class) // what ever body you get back -> covert that to provided class
// .block(); // locks until the request is done asynchronous concept.
