package mark.harrison.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class createBeer extends AppCompatActivity {



    //Fields
    Button mCreateBeerButton;
    private TextView beerName;
    private TextView beerBrewery;
    private TextView beerBarcode;
    //Varible inputs
    String mBeername;
    String mBrewery;
    String mBarcode;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Beers");


    //mDatabase = FirebaseDatabase.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_beer);



        beerName = (TextView) findViewById(R.id.beerNameText);
        beerBrewery = (TextView)findViewById(R.id.beweryText);
        beerBarcode = (TextView)findViewById(R.id.barcodeText);




        mCreateBeerButton = findViewById(R.id.createBeerButton);
        mCreateBeerButton.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {


                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        mBeername = beerName.getText().toString();
                        mBrewery = beerBrewery.getText().toString();
                        mBarcode = beerBarcode.getText().toString();

                        Beers beers = new Beers(mBeername,mBrewery,mBarcode);
                        //myRef2.setValue(beers);
                        myRef.child(mBeername).setValue(beers);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });



    }
}
