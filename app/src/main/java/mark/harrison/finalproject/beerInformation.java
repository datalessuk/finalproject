package mark.harrison.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class beerInformation extends AppCompatActivity {

    //Database Reference
    //DatabaseReference Reference = FirebaseDatabase.getInstance().getReference().child("Beers");
    //DatabaseReference Reference = FirebaseDatabase.getInstance().getReference().child("Beers")
    //Textviews

     // Working



    TextView mBeername;
    TextView mBeerBrewery;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();

        String data = intent.getStringExtra("Name");

        DatabaseReference zonesRef = FirebaseDatabase.getInstance().getReference("Beers");
        DatabaseReference zone1Ref = zonesRef.child(data);
        DatabaseReference zone1NameRef = zone1Ref.child("mName");
        DatabaseReference zone2 =  zone1Ref.child("mBrewery");

        //final ArrayList my = new ArrayList();




        setContentView(R.layout.activity_beer_information);

        mBeername = (TextView)findViewById(R.id.BeerNameText);
        mBeerBrewery = (TextView) findViewById(R.id.BreweryText);

        zone1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //for(DataSnapshot ds : dataSnapshot.getChildren()){
                    //Beers beers = ds.getValue(Beers.class);
                    //mBeername.setText(beers.getmName().toString());
                   //String name = ds.child("mName").getValue(String.class);
                    Beers beers = dataSnapshot.getValue(Beers.class);
                    String beerclass = beers.getmName();
                    String b = beers.getmBrewery();
                    mBeername.setText(beerclass);
                    mBeerBrewery.setText(b);
                    //my.add(beers);
                    //String beername = ds.child("mBarcode").getValue(String.class);
                    ////mBeername.setText(beername);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //zone1Ref.addChildEventListener(new ChildEventListener() {
         /*   @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Beers beers = dataSnapshot.getValue(Beers.class);
                mBeername.setText(beers.getmName().toString());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        /* zone1NameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //String beerName = dataSnapshot.child("5010038460160").getValue().toString();
                String beerName = dataSnapshot.getValue(String.class);
                //String b = dataSnapshot.child("5010038460160").getValue().toString();
                mBeername.setText(beerName);

                //Toast.makeText(beerInformation.this,beerName ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        zone2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String beerBrewery = dataSnapshot.getValue(String.class);
                mBeerBrewery.setText(beerBrewery);
                Toast.makeText(beerInformation.this,beerBrewery,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



    }


}
