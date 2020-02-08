/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.gamecam.menickacz.data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Erik Juríček
 */
public class RestaurantDetail {

    public enum State {
        OPEN,
        CLOSE
    };

    private String name;
    private State state;    //not work now
    private String address;
    private Map<String, String> openHours;
    private Contact contact;  //not work now
    private Map<Date, Menu> menus;
    private List<String> mealTickets;
    private List<String> extendInfos;
    private List<String> creditCards;
    private boolean wifi;

    public RestaurantDetail() {
        menus = new HashMap<Date, Menu>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getOpenHours() {
        return openHours;
    }

    public void setOpenHours(Map<String, String> openHours) {
        this.openHours = openHours;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Map<Date, Menu> getMenus() {
        return menus;
    }

    public void setMenus(Map<Date, Menu> menus) {
        this.menus = menus;
    }

    public List<String> getMealTickets() {
        return mealTickets;
    }

    public void setMealTickets(List<String> mealTickets) {
        this.mealTickets = mealTickets;
    }

    public List<String> getExtendInfos() {
        return extendInfos;
    }

    public void setExtendInfos(List<String> extendInfos) {
        this.extendInfos = extendInfos;
    }

    public List<String> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<String> creditCards) {
        this.creditCards = creditCards;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
