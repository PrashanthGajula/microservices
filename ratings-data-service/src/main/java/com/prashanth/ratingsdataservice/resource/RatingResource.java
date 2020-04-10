package com.prashanth.ratingsdataservice.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prashanth.ratingsdataservice.model.Rating;
import com.prashanth.ratingsdataservice.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource
{
    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") final String movieId)
    {
        return new Rating(movieId, 4);
    }

    @RequestMapping("/user/{userId}")
    public UserRating getUserRating(@PathVariable("userId") final String userId)
    {
        final List<Rating> ratings = Arrays.asList(new Rating("1234", 4), new Rating("5678", 5));
        final UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }

}
