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
public class City {

    private String cityName;
    private String cityUrl;

    public City(String cityName, String cityUrl) {
        this.cityName = cityName;
        this.cityUrl = cityUrl;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityUrl() {
        return cityUrl;
    }

    public void setCityUrl(String cityUrl) {
        this.cityUrl = cityUrl;
    }

}
