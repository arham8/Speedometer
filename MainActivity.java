package com.example.live_location_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Button onetime, live;
    TextView title, latitude, longitude, Altitude, Speed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onetime = findViewById(R.id.lastLocation);
        live = findViewById(R.id.btnlive);
        title = findViewById(R.id.txtTitle);
        latitude = findViewById(R.id.txtLat);
        longitude = findViewById(R.id.txtLng);
        Altitude = findViewById(R.id.txtAltitude);
        Speed = findViewById(R.id.TxtSpeed);


        onetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int p = ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION);

                if (p != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            MainActivity.this,
                            new String[]
                                    {Manifest.permission.ACCESS_FINE_LOCATION},
                            1);

                } else {
                    //TODO
                    double lat, lng;

                    LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                    Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    double l1 = loc.getLatitude();
                    double l2 = loc.getLongitude();
                    double l3 = loc.getAltitude();
                    double l4 = loc.getSpeed();

                    title.setText("One time:");
                    latitude.setText("" + l1);
                    longitude.setText("" + l2);
                    Altitude.setText("" + l3);
                    Speed.setText("" + l4);


                }


            }
        });


        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int p = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
                if (p != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                } else {
                    //TODO
                    double lat, lng;

                    LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                    Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    double l1 = loc.getLatitude();
                    double l2 = loc.getLongitude();
                    double l3 = loc.getAltitude();
                    double l4 = loc.getSpeed();

                    title.setText("One time:");
                    latitude.setText("" + l1);
                    longitude.setText("" + l2);
                    Altitude.setText("" + l3);
                    Speed.setText("" + l4);

                }
            }


        });

    }


}

