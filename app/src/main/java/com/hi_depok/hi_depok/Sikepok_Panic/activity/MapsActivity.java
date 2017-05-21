package com.hi_depok.hi_depok.Sikepok_Panic.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hi_depok.hi_depok.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements LocationListener, OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    double latitude, longitude, lat, lng;
    boolean connected = false;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker, placeMarker;
    LocationRequest mLocationRequest;
    String GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.3/sikepokpanic_json.php";
    String JSON_ID = "id_tempat_sehat";
    String JSON_ALAMAT = "alamat_tempat_sehat";
    String JSON_JENIS = "nama_jenis";
    String JSON_KORDINAT = "koordinat_tempat_sehat";
    String JSON_KECAMATAN = "kecamatan_tempat_sehat";
    String JSON_NAME = "nama_tempat_sehat";
    String JSON_FOTO = "foto_tempat_sehat";
    String JSON_KETERANGAN = "keterangan_tempat_sehat";
    String JSON_OPERASIONAL = "operasional_tempat_sehat";
    String JSON_NOTLP = "no_telp_tempat_sehat";
    String placeId, placeName, placeDeskripsi, placeJenis, placeKecamatan, placeLokasi, placeNoTlp, placeFoto, placeOperasional, placeKordinat, latlong;
    JsonArrayRequest jsonArrayRequest ;
    RequestQueue requestQueue ;
    HashMap<Marker, String> hashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokpanic_maps);
        //Check if Google Play Services Available or not
        if (!CheckGooglePlayServices()) {
            Log.d("onCreate", "Finishing test case since Google Play Services are not available");
            finish();
        }
        else {
            Log.d("onCreate","Google Play Services available.");
        }
        String tempatSehat[] = { "Semua Tempat", "Puskesmas", "Klinik", "Apotek",
                "Bidan", "Tempat Khitan", "Tempat Pijat" };
        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinPlaces);
        spinner.setItems(tempatSehat);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                switch (position) {
                    case 0:
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.3/sikepokpanic_json.php";
                        break;
                    case 1:
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.3/sikepokpanic_json.php?kategori=Puskesmas";
                        break;
                    case 2:
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.3/sikepokpanic_json.php?kategori=Klinik";
                        break;
                    case 3:
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.3/sikepokpanic_json.php?kategori=Apotek";
                        break;
                    case 4:
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.3/sikepokpanic_json.php?kategori=Bidan";
                        break;
                    case 5:
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.3/sikepokpanic_json.php?kategori=Tempat%20Khitan";
                        break;
                    case 6:
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.3/sikepokpanic_json.php?kategori=Tempat%20Pijat";
                        break;
                    default:
                        break;
                }

                JSON_DATA_WEB_CALL();
            }
        });
        JSON_DATA_WEB_CALL();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            showLocationSettings();
        }

    }

    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    public void JSON_DATA_WEB_CALL() {
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){
        mMap.clear();
        hashMap = new HashMap<>();
        for(int i = 0; i<array.length(); i++) {
            JSONObject json = null;
            try {

                json = array.getJSONObject(i);
                placeId = json.getString(JSON_ID);
                placeName = json.getString(JSON_NAME);
                placeKordinat = json.getString(JSON_KORDINAT);
                Log.d("onPostExecute","Entered into showing locations");
                MarkerOptions markerOptions = new MarkerOptions();
                String[] kordinat = placeKordinat.split(",");
                if(placeKordinat.contains(",")) {
                    lat = Double.parseDouble(kordinat[0]);
                    lng = Double.parseDouble(kordinat[1].trim());
                }
                else{
                    lat = 0.0;
                    lng = 0.0;
                }

//

                LatLng endPoint = new LatLng(lat, lng);
                markerOptions.position(endPoint);
                markerOptions.title(placeName);
                if(json.getString(JSON_JENIS).equals("Apotek")){
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_04));
                }
                else if(json.getString(JSON_JENIS).equals("Puskesmas")){
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_10));
                }
                else if(json.getString(JSON_JENIS).equals("Bidan")){
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_02));
                }
                else if(json.getString(JSON_JENIS).equals("Klinik")){
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_05));
                }
                else if(json.getString(JSON_JENIS).equals("Tempat Pijat")){
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_08));
                }
                else if(json.getString(JSON_JENIS).equals("Tempat Khitan")){
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_11));
                }

                placeMarker = mMap.addMarker(markerOptions);
                hashMap.put(placeMarker, placeId);
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        String id = hashMap.get(marker);
                        Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                        intent.putExtra(DetailActivity.EXTRA_POSITION, id);
                        Log.d("MapsActivity", "get id: " + placeId);
                        startActivity(intent);
                    }
                });

            } catch (JSONException e) {

                e.printStackTrace();
            }
        }

        if(latlong!=null){
            LatLng latLng = new LatLng(latitude, longitude);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Lokasi Kamu");
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_13));
            mCurrLocationMarker = mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
        }

    }
    private void showLocationSettings() {
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), "Location Error: GPS Disabled!",
                        Snackbar.LENGTH_LONG)
                .setAction("Enable", new View.OnClickListener() {
                    @Override                    public void onClick(View v) {

                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });
        snackbar.setActionTextColor(Color.RED);
        snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);

        snackbar.show();
    }
    public void toList(View view) {
        if(latlong!=null){
            Intent intent = new Intent(getBaseContext(), MenuActivity.class);
            intent.putExtra("LatLong", latlong);
            startActivity(intent);
        }
        else{
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Menunggu untuk mendapatkan lokasi anda!", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }

    private boolean CheckGooglePlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();
            }
            return false;
        }
        return true;
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
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
//        mLocationRequest.setInterval(1000);
//        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }


    @Override
    public void onConnectionSuspended(int i) {

    }


    @Override
    public void onLocationChanged(Location location) {
        Log.d("onLocationChanged", "entered");
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        //Place current location marker
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        latlong = String.valueOf(latitude) + "," + String.valueOf(longitude);
        LatLng startPoint = new LatLng(latitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(startPoint);

        markerOptions.title("Lokasi Kamu");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_13));

        mCurrLocationMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(startPoint));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));

        connected = true;
        Log.d("onLocationChanged", String.format("latitude:%.3f longitude:%.3f",latitude,longitude));

        //stop location updates
//        if (mGoogleApiClient != null) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
//            Log.d("onLocationChanged", "Removing Location Updates");
//        }
        Log.d("onLocationChanged", "Exit");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MapsActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}