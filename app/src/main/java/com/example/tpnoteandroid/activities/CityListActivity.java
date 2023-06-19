package com.example.tpnoteandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tpnoteandroid.R;
import com.example.tpnoteandroid.model.City;
import com.example.tpnoteandroid.service.WeatherAPIService;
import com.example.tpnoteandroid.utils.MyListAdapter;

import org.json.JSONException;

import java.util.ArrayList;

public class CityListActivity extends AppCompatActivity {

    private TextView cityName;
    private ListView cityListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        String city = getIntent().getStringExtra("CITY");

        this.cityName = findViewById(R.id.cityName);
        this.cityListView = findViewById(R.id.cityList);
        this.cityName.setText(city);
        WeatherAPIService weatherApiService = WeatherAPIService.getInstance(this);
        System.out.println("CityListActivity2");
        weatherApiService.fetchCities(city, response -> {
            try {
                showCities((ArrayList<City>) response);
            } catch (JSONException e) {
                Toast.makeText(this, getText(R.string.error)+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        this.cityListView.setOnItemClickListener((parent, view, position, id) -> {
            City selectedCity = (City) parent.getItemAtPosition(position);
            Intent intent = new Intent(this, CityWeatherActivity.class);
            intent.putExtra("CITY", selectedCity);
            startActivity(intent);
        });

    }

    private void showCities(ArrayList<City> cities) throws JSONException {
        ArrayAdapter<City> adapter = new MyListAdapter(this, cities);
        cityListView.setAdapter(adapter);
    }
}