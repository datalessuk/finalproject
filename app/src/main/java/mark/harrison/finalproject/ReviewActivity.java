package mark.harrison.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ReviewActivity extends AppCompatActivity {


    private TextView mReviewText;
    private Button mRewviewButton;
    private TextView mFlavoursText;

    private Button mReviewButtonTest;

    //Test
    private Button mRatingButton;
    private RatingBar mRatingBar;

    String reviewText;
    long reviewID = 0;

    long reviewCounter =0;



    int mRatingCount;
    float mRatingTotal;

    //float pValue;
    float finalrating;

    float anotherRatong;
    float mCurrentRating;

    float total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Intent intent = this.getIntent();


        final String data = intent.getStringExtra("beerName");
        DatabaseReference zonesRef = FirebaseDatabase.getInstance().getReference("Reviews");
       final DatabaseReference zone1Ref = zonesRef.child(data);

        //For the rating
        DatabaseReference beerRatingRef = FirebaseDatabase.getInstance().getReference("Beers");
        final DatabaseReference beerratingRef1 = beerRatingRef.child(data);

        //final Map<String, Object> updates = new HashMap<String,Object>();

        //Next Go at the rating
        final DatabaseReference ratingRef = FirebaseDatabase.getInstance().getReference("Ratings");
        final DatabaseReference Ratingref1 = ratingRef.child(data);

        //For the final Rating
        DatabaseReference beerRating = FirebaseDatabase.getInstance().getReference("Ratings");
        final DatabaseReference beerFinalRating = beerRating.child(data);


        mRewviewButton = (Button)findViewById(R.id.addReviewButton);
        mReviewText = (TextView)findViewById(R.id.beerReviewText);
        mFlavoursText= (TextView)findViewById(R.id.flavoursText);

        mRatingBar = (RatingBar)findViewById(R.id.beerRatingBar);

        mReviewButtonTest = (Button)findViewById(R.id.reviewTestbutton);

        zone1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    reviewID =(dataSnapshot.getChildrenCount());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        Ratingref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    reviewCounter = (dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mRewviewButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                reviewText = mReviewText.getText().toString();
                zone1Ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        beerReviews reviews = new beerReviews(reviewText);
                        zone1Ref.child(String.valueOf(reviewID+1)).setValue(reviews);

                        float vRating = mRatingBar.getRating();
                        mCurrentRating += mRatingBar.getRating();
                        beersRatings beersRatings = new beersRatings(vRating);
                        Ratingref1.child(String.valueOf(reviewCounter+1)).setValue(beersRatings);



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });












                Intent intent = new Intent(ReviewActivity.this, allBeers.class);
                view.getContext().startActivity(intent);
            }
        });

        mReviewButtonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beerratingRef1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //for(DataSnapshot ds : dataSnapshot.getChildren()){
                        //Beers beers = ds.getValue(Beers.class);
                        //mBeername.setText(beers.getmName().toString());
                        //String name = ds.child("mName").getValue(String.class);
                        Beers beers = dataSnapshot.getValue(Beers.class);
                        mCurrentRating = beers.getmRating();

                        float currentRating = mRatingBar.getRating();
                        total= mCurrentRating + currentRating;// Old rating + new Rating



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                zone1Ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        beerReviews reviews = new beerReviews(reviewText);
                        zone1Ref.child(String.valueOf(reviewID+1)).setValue(reviews);

                        float vRating = mRatingBar.getRating();

                        beersRatings beersRatings = new beersRatings(vRating);
                        Ratingref1.child(String.valueOf(reviewCounter+1)).setValue(beersRatings);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                beerFinalRating.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        float sum =0;
                        for(DataSnapshot ds : dataSnapshot.getChildren()) {
                            Map<String,Object>  map = (Map<String, Object>) ds.getValue();
                            Object value = map.get("mBeerRatings");
                            float pValue = Float.parseFloat(String.valueOf(value));
                            sum +=pValue;
                            Toast.makeText(ReviewActivity.this,"Total is: " +sum,Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                /*beerFinalRating.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren()) {
                            mRatingCount = (int) dataSnapshot.getChildrenCount();
                            //Toast.makeText(ReviewActivity.this,"total is: "+ mRatingCount,Toast.LENGTH_SHORT).show();

                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object value = map.get("mBeerRatings");
                            float pValue = Float.parseFloat(String.valueOf(value));
                            Toast.makeText(ReviewActivity.this,"Total is: " +pValue,Toast.LENGTH_SHORT).show();
                            //total += pValue;
                            //Toast.makeText(ReviewActivity.this,"is "+total,Toast.LENGTH_SHORT).show();

                            //mRatingBar.setRating(finalrating);
                            //finalrating = GetBeerRating(mRatingCount, mCurrentRating);
                            finalrating = GetBeerRating(mRatingCount,total);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/

                beerratingRef1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        HashMap<String, Object> result = new HashMap<>();
                        //String flavours = mFlavoursText.getText().toString();
                        //result.put("mFlavours",flavours);
                        result.put("mRating",finalrating);
                        //Beers beers = new Beers(flavours);
                        beerratingRef1.updateChildren(result);
                        //result.put("mRating",mRating);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                reviewText = mReviewText.getText().toString();



            }
        });



        //Toast.makeText(ReviewActivity.this, "The total is : " + finalrating, Toast.LENGTH_SHORT).show();




        /*mRatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ratingref1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        float vRating = mRatingBar.getRating();


                        beersRatings beersRatings = new beersRatings(vRating);
                        Ratingref1.child(String.valueOf(reviewCounter+1)).setValue(beersRatings);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });*/

            /*mRatingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    beerratingRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            HashMap<String, Object> result = new HashMap<>();
                            //int mf =(int)reviewCounter;


                            //float rating = mRatingBar.getRating();
                            //String mRating = String.valueOf(rating);
                            //String rating = "6";
                            //result.put("mRating",mRating);
                           // Beers beers = new Beers(rating);
                            //beers.setmRating(rating);
                            //beerratingRef1.setValue(beers);
                            //beerratingRef1.updateChildren(result);


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            });*/





    }
    public float GetBeerRating(int pCountTotal,float pRatingTotal ){
        float mRating = pRatingTotal/ pCountTotal;
        return  mRating;

    }

}




