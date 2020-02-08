/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.gamecam.menickacz.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Erik Juríček
 */
@Path("/menickacz")
@Produces(MediaType.APPLICATION_JSON)
public interface Menicka {

    //Get all cities in menicka.cz
    @GET
    @Path("/listcities")
    ListCityResponse getCities();

    //Get list restaurants in some city
    @GET
    @Path("/listrestaurants")
    ListRestaurantsResponse getRestaurants(@QueryParam("city-url") String cityUrl);

    //Get restaurant info
    @GET
    @Path("/getrestaurant")
    RestaurantResponse getRestaurant(@QueryParam("restaurant-url") String restaurantUrl);

    //Get restaurant info by restaurant name
    @GET
    @Path("/getrestaurantbyname")
    RestaurantResponse getRestaurantByName(@QueryParam("restaurant-name") String restaurantName);

    //Get restaurants in city by city name
    @GET
    @Path("/listrestaurantsbyname")
    ListRestaurantsResponse getRestaurantsByName(@QueryParam("city-name") String cityName);
}
