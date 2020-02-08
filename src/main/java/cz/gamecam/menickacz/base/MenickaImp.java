/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.gamecam.menickacz.base;

import cz.gamecam.menickacz.api.ListCityResponse;
import cz.gamecam.menickacz.api.ListRestaurantsResponse;
import cz.gamecam.menickacz.api.Menicka;
import cz.gamecam.menickacz.api.Response;
import cz.gamecam.menickacz.api.RestaurantResponse;
import cz.gamecam.menickacz.data.Item;
import cz.gamecam.menickacz.data.Menu;
import cz.gamecam.menickacz.data.RestaurantDetail;
import cz.gamecam.menickacz.data.basic.City;
import cz.gamecam.menickacz.data.basic.Restaurant;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

/**
 *
 * @author Erik Juríček
 */
@Startup
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class MenickaImp implements Menicka {

    //Not work now, we can cache some requests and save some time by getting info from remote host
    private Map<String, ListCityResponse> cityRequestCache;    //autoclean cache
    private Map<String, ListRestaurantsResponse> restaurantsRequestCache;    //autoclean cache
    private Map<String, RestaurantResponse> restaurantRequestCache;    //autoclean cache
    private Map<String, String> citiesCache;
    private Map<String, String> restaurantsCache;

    Logger log = Logger.getLogger(MenickaImp.class.getName());

    @PostConstruct
    public void start() {
        citiesCache = new HashMap<>();
        restaurantsCache = new HashMap<>();
        cacheAll();
    }

    @PreDestroy
    public void stop() {

    }

    @Override
    public ListCityResponse getCities() {
        ListCityResponse response = new ListCityResponse();
        try {
            List<City> cities = new ArrayList<>();
            Document doc = Jsoup.connect("http://menicka.cz").postDataCharset("CP1250").get();

            Elements newsHeadlines = doc.select("ul#changecity a");
            for (Element headline : newsHeadlines) {
                String cityName = selectValue(headline);
                cities.add(new City(cityName.substring(2), headline.absUrl("href")));
            }

            response.setCities(cities);
            response.setResponseReason(Response.ResponseType.SUCCESS);

        } catch (Exception e) {
            log.log(Level.WARNING, "downloadCity fail", e);
            response.setResponseReason(Response.ResponseType.FAILED);
        }

        return response;
    }

    @Override
    public ListRestaurantsResponse getRestaurants(String cityUrl) {
        ListRestaurantsResponse response = new ListRestaurantsResponse();
        try {
            List<Restaurant> restaurants = new ArrayList<>();

            Document doc = Jsoup.connect(cityUrl).postDataCharset("CP1250").get();

            Elements newsHeadlines = doc.select("ul#cityroll a");
            for (Element headline : newsHeadlines) {
                String restaurantName = selectValue(headline);
                restaurants.add(new Restaurant(restaurantName.substring(2), headline.absUrl("href")));
            }

            response.setRestaurants(restaurants);
            response.setResponseReason(Response.ResponseType.SUCCESS);

        } catch (Exception e) {
            log.log(Level.WARNING, "downloadRestaurants fail", e);
            response.setResponseReason(Response.ResponseType.FAILED);
        }
        return response;
    }

    @Override
    public RestaurantResponse getRestaurant(String restaurantUrl) {
        RestaurantResponse response = new RestaurantResponse();
        try {
            RestaurantDetail restaurant = new RestaurantDetail();

            Document doc = Jsoup.connect(restaurantUrl).postDataCharset("CP1250").get();

            //Get name
            Elements profile = doc.getElementsByClass("profile");
            Elements h1s = profile.select("h1");
            String restaurantName = selectValue(h1s.first());
            restaurant.setName(restaurantName);

            //Get address
            Elements adEl = doc.getElementsByClass("adresa");
            String adress = MenickaImp.this.selectValue(adEl.first());
            restaurant.setAddress(adress);

            //We can fill lots of another items
            //Get menu
            Elements menu = doc.getElementsByClass("menicka");
            for (Element element : menu) {
                Menu saveMenu = new Menu();
                Elements day = element.getElementsByClass("nadpis");
                Elements items = element.select("ul li");
                Date dateSave = createDateFromString(MenickaImp.this.selectValue(day.first()));
                saveMenu.setDate(dateSave);

                for (Element item : items) {
                    String name = MenickaImp.this.selectValue(item.getElementsByClass("polozka").first());
                    String price = MenickaImp.this.selectValue(item.getElementsByClass("cena").first());

                    Item saveItem = new Item();
                    saveItem.setName(name);
                    saveItem.setPrice(price);
                    saveMenu.add(saveItem);
                }

                restaurant.getMenus().put(dateSave, saveMenu);
                response.setRestaurant(restaurant);
                response.setResponseReason(Response.ResponseType.SUCCESS);
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "getRestaurant fail", e);
            response.setResponseReason(Response.ResponseType.FAILED);
        }
        return response;
    }

    @Override
    public RestaurantResponse getRestaurantByName(String restaurantName) {
        RestaurantResponse response = new RestaurantResponse();
        try {
            if (restaurantsCache.containsKey(restaurantName)) {
                response = getRestaurant(restaurantsCache.get(restaurantName));
            } else {
                response.setResponseReason(Response.ResponseType.NOT_FOUND);
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "getRestaurantByName fail", e);
            response.setResponseReason(Response.ResponseType.FAILED);
        }
        return response;
    }

    @Override
    public ListRestaurantsResponse getRestaurantsByName(String cityName) {
        ListRestaurantsResponse response = new ListRestaurantsResponse();
        try {
            if (citiesCache.containsKey(cityName)) {
                response = getRestaurants(citiesCache.get(cityName));
            } else {
                response.setResponseReason(Response.ResponseType.NOT_FOUND);
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "getRestaurantsByName fail", e);
            response.setResponseReason(Response.ResponseType.FAILED);
        }
        return response;
    }

    public String selectValue(Element mainElement) {
        StringBuilder sb = new StringBuilder("");

        if (mainElement != null) {
            for (Node childNode : mainElement.childNodes()) {
                sb.append(selectValue(childNode));
            }
        }

        return sb.toString();
    }

    public String selectValue(Node main) {
        StringBuilder sb = new StringBuilder("");
        if (main instanceof TextNode) {
            TextNode node = (TextNode) main;
            sb.append(node.text());
        } else {
            for (Node child : main.childNodes()) {
                sb.append(selectValue(child));
            }
        }
        return sb.toString();
    }

    public Date createDateFromString(String stringDate) {
        String digits = stringDate.replaceAll("[^0-9.]", "");
        String goodForm = digits.replaceAll("\\.", "/");

        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(goodForm);
        } catch (ParseException ex) {
            Logger.getLogger(MenickaImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }

    @Schedule(hour = "*", minute = "*/30", second = "00", info = "Cache scheduler", persistent = false)
    public void cacheAll() {
        try {
            Thread thread = new Thread() {
                public void run() {
                    log.info("Cache start");
                    Map<String, String> citiesCacheNew = new HashMap<>();
                    Map<String, String> restaurantsCacheNew = new HashMap<>();

                    ListCityResponse cities = getCities();
                    for (City city : cities.getCities()) {
                        citiesCacheNew.put(city.getCityName(), city.getCityUrl());
                        ListRestaurantsResponse restaurants = getRestaurants(city.getCityUrl());
                        for (Restaurant restaurant : restaurants.getRestaurants()) {
                            restaurantsCacheNew.put(restaurant.getRestaurantName(), restaurant.getRestaurantUrl());
                        }
                    }

                    //Clear and replace
                    citiesCache.clear();
                    citiesCache.putAll(citiesCacheNew);

                    restaurantsCache.clear();
                    restaurantsCache.putAll(restaurantsCacheNew);

                    log.info("Cache finish");
                }
            };

            thread.start();
        } catch (Exception e) {
        }
    }
}
