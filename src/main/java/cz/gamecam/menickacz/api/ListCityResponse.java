/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.gamecam.menickacz.api;

import cz.gamecam.menickacz.data.basic.City;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Erik Juríček
 */
public class ListCityResponse extends Response {

    List<City> cities;
    private int cityCount;

    public ListCityResponse() {
        cities = new ArrayList<>();
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
        if (cities != null) {
            setCityCount(cities.size());
        }
    }

    public int getCityCount() {
        return cityCount;
    }

    public void setCityCount(int cityCount) {
        this.cityCount = cityCount;
    }

}
