package com.prashanth.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.prashanth.moviecatalogservice.models.CatalogItem;
import com.prashanth.moviecatalogservice.models.Movie;
import com.prashanth.moviecatalogservice.models.Rating;
import com.prashanth.moviecatalogservice.models.UserRating;
import com.prashanth.moviecatalogservice.service.MovieInfo;
import com.prashanth.moviecatalogservice.service.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource
{
    @Autowired
    UserRatingInfo userRatingInfo;

    @Autowired
    MovieInfo movieInfo;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") final String userId)
    {
        //Get all rated movie IDs
        // final UserRating userRatings = restTemplate.getForObject("http://localhost:8083/ratingsdata/user/" + userId, UserRating.class);
        UserRating userRatings = userRatingInfo.getUserRating(userId);

        //For each User ratings, get the Catalog Item
        return userRatings.getRatings().stream()
            .map(rating -> movieInfo.getCatalogItem(rating))
            .collect(Collectors.toList());
    }
}

// @Autowired
// private WebClient.Builder webClientBuilder;
// ***WebClient usage - alternate to RestTemplate***
// final Movie movie = webClientBuilder.build() // builder pattern and gives the client
// .get() // get method
// .uri("http://localhost:8082/movies/" + rating.getMovieId()) // url that we need to access
// .retrieve() // go do the fetch
// .bodyToMono(Movie.class) // what ever body you get back -> covert that to provided class
// .block(); // locks until the request is done asynchronous concept.
