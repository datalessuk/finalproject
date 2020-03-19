package mark.harrison.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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



    List<Place> nearByPlaces = new ArrayList<Place>();
    ArrayAdapter myAdapter;

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

                RequestQueue queue = Volley.newRequestQueue(locationActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, test, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        nearByPlaces = (ArrayList) parseGoogleParse(response);

                        List<String> temp = new ArrayList<>();
                        myAdapter = new ArrayAdapter(locationActivity.this,android.R.layout.simple_list_item_1, temp);

                        for(int x =0; x <nearByPlaces.size();x++){

                            temp.add( x,nearByPlaces.get(x).getmName());

                        }
                        myAdapter = new ArrayAdapter(locationActivity.this,android.R.layout.simple_list_item_1,temp);
                        mPlacesList.setAdapter(myAdapter);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(stringRequest);
            }

        });
        mPlacesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int itemPosition   = position;
                String  itemValue    = (String) mPlacesList.getItemAtPosition(position);
                Toast.makeText(locationActivity.this,"Clicked"+itemPosition+"Clicked"+ itemValue,Toast.LENGTH_SHORT).show();

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




    private static ArrayList parseGoogleParse(final String response) {

        ArrayList temp = new ArrayList();
        try {

            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(response);

            // make an jsonObject in order to parse the response
            if (jsonObject.has("results")) {

                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    Place place = new Place();
                    if (jsonArray.getJSONObject(i).has("name")) {
                        place.setmName(jsonArray.getJSONObject(i).optString("name"));


                            if(jsonArray.getJSONObject(i).has("vicinity")){
                                place.setmVicinity(jsonArray.getJSONObject(i).optString("vicinity"));
                            }

                    }
                    temp.add(place);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
        return temp;

    }



}






