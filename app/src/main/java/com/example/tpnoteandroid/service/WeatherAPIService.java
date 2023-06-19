package com.example.tpnoteandroid.service;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tpnoteandroid.model.City;
import com.example.tpnoteandroid.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherAPIService {

    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/find?q=CITY&units=metric&appid=d041897bb0e9ecf30de085339892ccc0";
    private Context context;
    private RequestQueue requestQueue;

    private JSONArray cityListJSONArray;

    private static WeatherAPIService instance;

    private WeatherAPIService(Context context) {
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public static WeatherAPIService getInstance(Context context) {
        if (instance == null) {
            instance = new WeatherAPIService(context);
        }
        return instance;
    }

//    public void fetchAsteroidPeriod(Asteroid asteroid, Response.Listener<Double> responseListener) {
//        System.out.println(asteroid.getUrl());
//        JsonObjectRequest arrayRequest = new JsonObjectRequest(Request.Method.GET, asteroid.getUrl(), null, response -> {
//            Double period = null;
//            try {
//                System.out.println("try1");
//                period = response.getJSONObject("orbital_data").getDouble("orbital_period");
//            } catch (JSONException e) {
//                System.out.println("Error2");
//                System.out.println(e.getMessage());
//                e.printStackTrace();
//            }
//            responseListener.onResponse(period);
//        }, error -> {
//            System.out.println("Error");
//            error.printStackTrace();
//            Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show();
//        });
//        System.out.println("Error1");
//        requestQueue.add(arrayRequest);
//    }

    public void fetchCities(String city, Response.Listener<List<City>> responseListener) {

        String url = WEATHER_API_URL.replace("CITY", city);
        System.out.println("firstTest");
        JsonObjectRequest arrayRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {

            ArrayList<City> cities = new ArrayList<>();
            try {
                this.cityListJSONArray = response.getJSONArray("list");
                for (int i = 0; i < cityListJSONArray.length(); i++) {
                    City newCity = new City(cityListJSONArray.getJSONObject(i).getInt("id"), cityListJSONArray.getJSONObject(i).getString("name"));
                    cities.add(newCity);
                }
            } catch (JSONException e) {
                Toast.makeText(context, "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            responseListener.onResponse(cities);
        }, error -> {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        });

        requestQueue.add(arrayRequest);
    }

    public void fetchCityWeather(City city, Response.Listener<City> responseListener) {
        for(int i = 0; i < this.cityListJSONArray.length(); i++) {
            try {
                JSONObject cityJSONObject = this.cityListJSONArray.getJSONObject(i);
                if (cityJSONObject.getInt("id") == city.getId()) {
                    Weather weather = new Weather(cityJSONObject);
                    city.setWeather(weather);
                    responseListener.onResponse(city);
                }
            } catch (JSONException e) {
                Toast.makeText(context, "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
