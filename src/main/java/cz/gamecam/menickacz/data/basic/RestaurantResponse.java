/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.gamecam.menickacz.data.basic;

import cz.gamecam.menickacz.data.RestaurantDetail;

/**
 *
 * @author Erik Juríček
 */
public class RestaurantResponse extends Response {

    private RestaurantDetail restaurant;

    public RestaurantResponse() {
    }

    public RestaurantDetail getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDetail restaurant) {
        this.restaurant = restaurant;
    }

}
