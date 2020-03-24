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



    //Test
    private Button mRatingButton;
    private RatingBar mRatingBar;

    String reviewText;
    long reviewID = 0;
    long reviewCounter =0;
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
        final DatabaseReference beerRatingRef = FirebaseDatabase.getInstance().getReference("Beers");
        final DatabaseReference beerratingRef1 = beerRatingRef.child(data);

        //final Map<String, Object> updates = new HashMap<String,Object>();

        //Next Go at the rating
        final DatabaseReference ratingRef = FirebaseDatabase.getInstance().getReference("Ratings");
        final DatabaseReference Ratingref1 = ratingRef.child(data);


        final DatabaseReference mFlavoursRef = beerRatingRef.child(data);



        mRewviewButton = (Button)findViewById(R.id.addReviewButton);
        mReviewText = (TextView)findViewById(R.id.beerReviewText);
        mFlavoursText= (TextView)findViewById(R.id.flavoursText);
        mRatingBar = (RatingBar)findViewById(R.id.beerRatingBar);


        zone1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    reviewID =(dataSnapshot.getChildrenCount());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();

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
                throw databaseError.toException();

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
                        throw databaseError.toException();

                    }
                });

                //#################### RATING SYSTEM ############################//

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
                        throw databaseError.toException();

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
                        throw databaseError.toException();

                    }
                });

                mFlavoursRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Beers beers = dataSnapshot.getValue(Beers.class);
                        String beerflavours = beers.getmFlavours();

                        Map<String, Object> updates = new HashMap<String,Object>();
                        String flavours = mFlavoursText.getText().toString();
                        updates.put("mFlavours",beerflavours + flavours + ",");

                        mFlavoursRef.updateChildren(updates);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        throw databaseError.toException();

                    }
                });
                reviewText = mReviewText.getText().toString();


                Intent intent = new Intent(ReviewActivity.this, allBeers.class);
                view.getContext().startActivity(intent);
            }
        });

    }




}




