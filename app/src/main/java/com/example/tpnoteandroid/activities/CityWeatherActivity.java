package com.example.tpnoteandroid.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tpnoteandroid.R;
import com.example.tpnoteandroid.model.City;
import com.example.tpnoteandroid.model.Weather;
import com.example.tpnoteandroid.service.WeatherAPIService;
import com.example.tpnoteandroid.view.HumidityView;
import com.example.tpnoteandroid.view.PressionView;
import com.example.tpnoteandroid.view.TempView;

public class CityWeatherActivity extends AppCompatActivity {

    private City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);
        this.city = (City) getIntent().getSerializableExtra("CITY");
        TextView cityName = findViewById(R.id.cityName);
        cityName.setText(this.city.getName());
        setWeatherView();
    }

    public void setWeatherView(){

        if (city != null) {
            WeatherAPIService weatherAPIService = WeatherAPIService.getInstance(this);
            weatherAPIService.fetchCityWeather(city, response -> {
                Weather weather = response.getWeather();
                TextView temperature = findViewById(R.id.temperature);
                TextView humidity = findViewById(R.id.humidity);
                TextView pressure = findViewById(R.id.pression);
                temperature.setText("Température : " + weather.getTemp() + " °C");
                humidity.setText("Humidité : " + weather.getHumidity() + " %");
                pressure.setText("Pression : " + weather.getPressure() + " hPa");
                TempView tempView = findViewById(R.id.tempView);
                PressionView pressionView = findViewById(R.id.pressionView);
                HumidityView humidityView = findViewById(R.id.humidityView);
                tempView.setTemp(weather.getTemp());
                pressionView.setPressure(weather.getPressure());
                humidityView.setHumidity(weather.getHumidity());
            });
        }
    }
}