package mark.harrison.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class createBeer extends AppCompatActivity {




    //Fields
    Button mCreateBeerButton;
    private TextView beerNameInput;
    private TextView beerBreweryInput;
    private TextView beerBarcodeInput;
    private TextView beerPercentageInput;
    //Varible inputs
    String mBeername;
    String mBrewery;
    String mBarcode;
    //None user inputed yet ?
    boolean mStock;
    String mFlavours ="";
    String mReview="";
    String mPercentage;
    float mRating;
    int mRatingCounter =0;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Beers");// For the main beer class

    DatabaseReference myRefRewview = database.getReference("Reviews");


    //mDatabase = FirebaseDatabase.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_beer);



        beerNameInput = (TextView) findViewById(R.id.beerNameText);
        beerBreweryInput = (TextView)findViewById(R.id.beweryText);
        beerBarcodeInput = (TextView)findViewById(R.id.barcodeText);
        beerPercentageInput = (TextView)findViewById(R.id.percentageText);



            final Barcode barcode = getIntent().getParcelableExtra("barcode");
            //beerBarcodeInput.setText(barcode.displayValue);
            beerBarcodeInput.setText(barcode.displayValue);



        //if(!inputValidation.getInstance().whiteSpaceCheck(beerNameInput.getText().toString())){
            //beerNameInput.setError("Sorry this can not be blank");





    //dataSnapshot.hasChild(mBarcode)



        //Button Event
        mCreateBeerButton = findViewById(R.id.createBeerButton);
        mCreateBeerButton.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(final View view) {
                mBeername = beerNameInput.getText().toString();
                mBrewery = beerBreweryInput.getText().toString();
                mBarcode = beerBarcodeInput.getText().toString();
                mPercentage = beerPercentageInput.getText().toString();


                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(mBeername)){
                            //beerBarcodeInput.setText("Sorry we have that beer on record already");
                            Toast.makeText(createBeer.this,"Sorry we have that beer on record",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(createBeer.this, homeScreen.class);
                            view.getContext().startActivity(intent);
                        }
                        else {
                                //

                            Beers beers = new Beers(mBeername,mBrewery,mBarcode,mStock,mFlavours,mPercentage,mRating);

                            //myRef2.setValue(beers);


                            Toast.makeText(createBeer.this,"Beer has been created thank you",Toast.LENGTH_SHORT).show();
                            myRef.child(mBeername).setValue(beers);
                            Intent intent = new Intent(createBeer.this, homeScreen.class);
                            view.getContext().startActivity(intent);
                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                /*myRefRewview.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        beerReviews beerReviews = new beerReviews(mReview);
                        myRefRewview.child(mBeername).setValue(beerReviews);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/

            }


        });



    }
}
