package com.example.tpnoteandroid.model;

import org.json.JSONObject;

public class Weather {

    private float temp;
    private int humidity;
    private int pressure;

    public Weather(float temp, int humidity, int pressure) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public Weather(JSONObject cityJSONObject) {
        try {
            this.temp = (float) cityJSONObject.getJSONObject("main").getDouble("temp");
            this.humidity = cityJSONObject.getJSONObject("main").getInt("humidity");
            this.pressure = cityJSONObject.getJSONObject("main").getInt("pressure");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTemp() {
        return Math.round(temp);
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

}
