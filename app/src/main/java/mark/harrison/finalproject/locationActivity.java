package mark.harrison.finalproject;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

import org.w3c.dom.Text;

import java.util.Collections;
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

    //Placees





    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        mLong = (TextView) findViewById(R.id.longg);
        mLatt = (TextView) findViewById(R.id.latt);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        mLatt.setText(String.valueOf(location.getLatitude()));
                        mLong.setText(String.valueOf(location.getLongitude()));

                    }


                }
            });

        }
        else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSIONS_REQUEST_FIND_LOCATION);

        }



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
}




