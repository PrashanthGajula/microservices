package com.prashanth.ratingsdataservice.model;

import java.util.List;

public class UserRating
{
    List<Rating> userRating;

    public List<Rating> getUserRating()
    {
        return userRating;
    }

    public void setUserRating(final List<Rating> userRating)
    {
        this.userRating = userRating;
    }
}
