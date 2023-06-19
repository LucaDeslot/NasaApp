package com.example.tpnoteandroid.model;

import java.io.Serializable;

public class City implements Serializable {

    private String name;
    private int id;

    private Weather weather;

    public City(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

}
