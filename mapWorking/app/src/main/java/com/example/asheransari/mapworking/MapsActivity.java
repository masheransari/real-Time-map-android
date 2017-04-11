package com.example.asheransari.mapworking;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    //1
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //2
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //3


        //3.2
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        //5 just for cjeck..
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            //6 ab hum es sub ko if me rkah denge..

            //3.1 error aye ga ab esko solve kren ge 3.2 me
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                //who is the projvider like GPS, minimum time interval, minimum distance hoga esme, ur 4th apka listner hai...
                public void onLocationChanged(Location location) {
                    //4 yeha hum location se lonitude ur latitude maloom krlen ge..
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    //9... creating the class, LatLng..
                    LatLng latLng = new LatLng(latitude, longitude);
                    //10.... instantiate the class, geocoder class...
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    //11...
//                    geocoder.getFromLocation(latitude,longitude,1);
                    //12 it contain error because it return the arrayList..
                    try {
                        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                        //it would pe maximum which is "1 - 5"
                        //13... ab hum country name etc denge...
                        String str = addresses.get(0).getLocality() + ",";
                        str += addresses.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10.8f));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                //agar location change hogi to yeha pe pata chale ge...
                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        }
        //7...
        else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            8...
//yeha hum paste krden ge, bus ek change yeh hai ke es me hum ne GPS pe kra hai..
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
//                    this all are copy from up...



                    //4 yeha hum location se lonitude ur latitude maloom krlen ge..
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    //9... creating the class, LatLng..
                    LatLng latLng = new LatLng(latitude, longitude);
                    //10.... instantiate the class, geocoder class...
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    //11...
//                    geocoder.getFromLocation(latitude,longitude,1);
                    //12 it contain error because it return the arrayList..
                    try {
                        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                        //it would pe maximum which is "1 - 5"
                        //13... ab hum country name etc denge...
                        String str = addresses.get(0).getLocality() + ",";
                        str += addresses.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10.8f));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
////        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        //for zooming...
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10.2f));
    }
}
