package com.prashanth.movieinfoservice.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prashanth.movieinfoservice.models.Movie;

@RestController
@RequestMapping("/movies")
public class MovieResource
{
    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") final String movieId)
    {
        return new Movie(movieId, "Test name");
    }

}
