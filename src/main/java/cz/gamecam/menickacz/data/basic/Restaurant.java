/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.gamecam.menickacz.data.basic;

/**
 *
 * @author Erik Juríček
 */
public class Restaurant {

    private String restaurantName;
    private String restaurantUrl;

    public Restaurant(String restaurantName, String restaurantUrl) {
        this.restaurantName = restaurantName;
        this.restaurantUrl = restaurantUrl;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantUrl() {
        return restaurantUrl;
    }

    public void setRestaurantUrl(String restaurantUrl) {
        this.restaurantUrl = restaurantUrl;
    }

}
