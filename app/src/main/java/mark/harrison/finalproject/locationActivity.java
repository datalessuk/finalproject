package mark.harrison.finalproject;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static android.telephony.CellLocation.requestLocationUpdate;
import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class locationActivity extends AppCompatActivity {

    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationRequest mLocationRequest;

    private final int LOCATION_PERMISSION = 1001;
    private Location mLocation;
    private LocationCallback mLocationCallback;

    private TextView mLong;
    private TextView mLatt;
    private static final int MY_PERMISSIONS_REQUEST_FIND_LOCATION =101;
    private static final int mProximityRadius = 10000;

    private double mLatitude;
    private double mLongitude;
    private String test;

    private ListView mPlacesList;
    private Button mTestButton;
    //Placees
    private final String mLocationType= "supermarket";
    private RequestQueue mQueue;
    private Context mApplicationContext;

    ArrayList<String> dataList = new ArrayList<String>();
    //RequestQueue queue = Volley.newRequestQueue(this);
    RequestQueue requestQueue;



    // API KEY For places



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        final Context context=getApplicationContext();

        mLong = (TextView) findViewById(R.id.longg);
        mLatt = (TextView) findViewById(R.id.latt);
        mPlacesList = (ListView) findViewById(R.id.placesListview);
        mTestButton = (Button)findViewById(R.id.button);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {



                        mLatt.setText(String.valueOf(location.getLatitude()));
                        mLong.setText(String.valueOf(location.getLongitude()));

                        mLatitude = location.getLatitude();
                        mLongitude = location.getLongitude();
                        test= finalURL(mLatitude,mLongitude);



                    }


                }
            });

        }
        else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSIONS_REQUEST_FIND_LOCATION);

        }

        mTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObject jsonObject = new JsonParser().parse(test).getAsJsonObject();
                






                /*RequestQueue queue = Volley.newRequestQueue(locationActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, test, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(locationActivity.this,"reply is: "+response,Toast.LENGTH_SHORT).show();


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(stringRequest);
                try {

                    JSONArray jObj = new JSONArray(test);
                    //JSONObject jObj = new JSONObject(stringRequest.toString());
                    //JSONArray jsonArray = jObj.getJSONArray("results");
                    for(int x =0; x <=jObj.length();x++){
                        JSONObject jsonObject = jObj.getJSONObject(x);
                        String placeName = "--NA--";
                        String vicinity= "--NA--";
                        String latitude= "";
                        String longitude="";
                        String reference="";
                        //placeName = jsonObject.getString("name");
                       // vicinity = .getString("vicinity");
                        dataList.add(jsonObject.getString("name"));
                        //Toast.makeText(locationActivity.this,":"+placeName,Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_FIND_LOCATION:

                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //permission granted we are not doing anything

                }else {
                    Toast.makeText(locationActivity.this,"Sorry you need to have pimmisions",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }

    }

    private String finalURL(double pLatitude , double pLongitude){
        StringBuilder googlePlaceURL = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceURL.append("location="+pLatitude+","+pLongitude);
        googlePlaceURL.append("&radius="+mProximityRadius);
        googlePlaceURL.append("&type="+mLocationType);
        googlePlaceURL.append("&key="+"AIzaSyAJ5R0rp7sj62zCghm2K1nrFP5tKmK6Udo");


        Log.d("Full url is: ", ""+ googlePlaceURL.toString());
        return googlePlaceURL.toString();



    }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    private HashMap<String, String> getPlace(JSONObject jPlace)
    {

        HashMap<String, String> place = new HashMap<String, String>();
        String placeName = "-NA-";
        String vicinity = "-NA-";
        String latitude = "";
        String longitude = "";
        String reference = "";

        try {
            // Extracting Place name, if available
            if (!jPlace.isNull("name")) {
                placeName = jPlace.getString("name");
            }

            // Extracting Place Vicinity, if available
            if (!jPlace.isNull("vicinity")) {
                vicinity = jPlace.getString("vicinity");
            }

            latitude = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lng");
            reference = jPlace.getString("reference");

            place.put("place_name", placeName);
            place.put("vicinity", vicinity);
            place.put("lat", latitude);
            place.put("lng", longitude);
            place.put("reference", reference);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return place;
    }
    private List<HashMap<String, String>> getPlaces(JSONArray jPlaces) {
        int placesCount = jPlaces.length();
        List<HashMap<String, String>> placesList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> place = null;

        /** Taking each place, parses and adds to list object */
        for (int i = 0; i < placesCount; i++) {
            try {
                /** Call getPlace with place JSON object to parse the place */
                place = getPlace((JSONObject) jPlaces.get(i));
                placesList.add(place);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return placesList;
    }


}






