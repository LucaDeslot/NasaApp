package com.example.tpnoteandroid.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tpnoteandroid.R;
import com.example.tpnoteandroid.model.City;

import java.util.List;

public class MyListAdapter extends ArrayAdapter<City> {

    private List<City> cities;
    private LayoutInflater inflater;

    public MyListAdapter(@NonNull Context context, @NonNull List<City> cities) {
        super(context, -1, cities);
        this.cities = cities;
        this.inflater = LayoutInflater.from(getContext());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.list_item_city, parent, false);
        City city = cities.get(position);
        TextView cityName = view.findViewById(R.id.cityNameListItem);
        cityName.setText(city.getName());
        return view;
    }
}