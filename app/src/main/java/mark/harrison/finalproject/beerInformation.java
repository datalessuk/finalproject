package mark.harrison.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class beerInformation extends AppCompatActivity {

    //Database Reference
    DatabaseReference Reference = FirebaseDatabase.getInstance().getReference().child("Beers");
    //DatabaseReference Reference = FirebaseDatabase.getInstance().getReference().child("Beers")
    //Textviews
    TextView mBeername;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_information);

        mBeername = (TextView)findViewById(R.id.beerNameText);
        Reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String beerName = dataSnapshot.child("5010038460160").getValue().toString();
                //String b = dataSnapshot.child("5010038460160").getValue().toString();
                mBeername.setText(beerName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }


}
