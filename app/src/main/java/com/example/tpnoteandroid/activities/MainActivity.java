package com.example.tpnoteandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tpnoteandroid.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.meteoButton);
        EditText editText = findViewById(R.id.cityNameEditText);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, CityListActivity.class);
            intent.putExtra("CITY", editText.getText().toString());
            startActivity(intent);
        });
    }
}