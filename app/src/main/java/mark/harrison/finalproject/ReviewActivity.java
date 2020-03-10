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

    //Test
    private Button mRatingButton;
    private RatingBar mRatingBar;

    String reviewText;
    long reviewID = 0;

    long reviewCounter =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Intent intent = this.getIntent();
        final String data = intent.getStringExtra("beerName");
        DatabaseReference zonesRef = FirebaseDatabase.getInstance().getReference("Reviews");
       final DatabaseReference zone1Ref = zonesRef.child(data);

        //For the rating
        //DatabaseReference beerRatingRef = FirebaseDatabase.getInstance().getReference("Beers");
        //final DatabaseReference beerratingRef1 = beerRatingRef.child(data);

        //final Map<String, Object> updates = new HashMap<String,Object>();

        //Next Go at the rating
        final DatabaseReference ratingRef = FirebaseDatabase.getInstance().getReference("Ratings");
        final DatabaseReference Ratingref1 = ratingRef.child(data);


        mRewviewButton = (Button)findViewById(R.id.addReviewButton);
        mReviewText = (TextView)findViewById(R.id.beerReviewText);

        mRatingButton = (Button)findViewById(R.id.ratingButton);
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

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        mRatingButton.setOnClickListener(new View.OnClickListener() {
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
        });

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
}




