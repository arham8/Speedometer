package com.example.speedoaltimeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class Screen2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        Button Kilometer, Miles, Meters, Foot;

        Kilometer = findViewById(R.id.Kilometer);
        Miles = findViewById(R.id.Miles);
        Meters = findViewById(R.id.Meters);
        Foot = findViewById(R.id.Feet);

        Kilometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor e = pref.edit();
                e.putString("speed scale", "kilometer");
                e.commit();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        Miles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor e = pref.edit();
                e.putString("speed scale", "miles");
                e.commit();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        Meters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor e = pref.edit();
                e.putString("altitude scale", "meters");
                e.commit();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        Foot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor e = pref.edit();
                e.putString("altitude scale", "feet");
                e.commit();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }
}