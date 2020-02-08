/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.gamecam.menickacz.data.basic;

import cz.gamecam.menickacz.data.basic.Restaurant;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Erik Juríček
 */
public class ListRestaurantsResponse extends Response {

    List<Restaurant> restaurants;
    private int restaurantsCount;

    public ListRestaurantsResponse() {
        restaurants = new ArrayList<>();
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        if (restaurants != null) {
            setRestaurantsCount(restaurants.size());
        }
    }

    public int getRestaurantsCount() {
        return restaurantsCount;
    }

    public void setRestaurantsCount(int restaurantsCount) {
        this.restaurantsCount = restaurantsCount;
    }

}
