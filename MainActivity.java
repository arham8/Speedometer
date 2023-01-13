package com.example.speedoaltimeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView Latitude = findViewById(R.id.Latitude);
        TextView Longitude = findViewById(R.id.Longitude);
        TextView City = findViewById(R.id.City);
        TextView Country = findViewById(R.id.Country);
        TextView speed = findViewById(R.id.Speed);
        TextView altitude = findViewById(R.id.Altitude);
        Button Settings = findViewById(R.id.Settings);

        SharedPreferences pref = getDefaultSharedPreferences(this);
        String Spd = pref.getString("speed scale", null);
        String Alt = pref.getString("altitude scale", null);



        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Screen2.class);
                startActivity(i);
            }
        });


        int p = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (p != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    1);

        } else {


            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Location loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            double lat = loc.getLatitude();
            double lng = loc.getLongitude();

            double Speed_kilometer = loc.getSpeed();
            double Speed_miles = loc.getSpeed()/1.609;

            double Altitude_meters = loc.getAltitude();
            double Altitude_feet = loc.getAltitude()*3.281;


            Latitude.setText("Latitude: " +lat+ "°N");
            Longitude.setText("Longitude: " +lng+"°E");

            if(Spd.equals("kilometer")) {
                speed.setText("Your current speed is " + Speed_kilometer + " km/h");
            }else{
                speed.setText("Your current speed is "+Speed_miles+"mph");
            }

            if(Alt.equals("meters")) {
                altitude.setText("Your current altitude is " + Altitude_meters + " m");
            }else{
                altitude.setText("Your current altitude is " + Altitude_feet + " feet");
            }



            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            try {
                List<Address>
                        CurrentCity = geocoder.getFromLocation(lat, lng, 1);
                        String city = CurrentCity.get(0).getLocality();
                        City.setText("The city you are currently in is " + city);

                List<Address>
                        CurrentCountry = geocoder.getFromLocation(lat, lng, 1);
                        String country = CurrentCountry.get(0).getCountryName();
                        Country.setText("The country you are currently in is " + country);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }


}





