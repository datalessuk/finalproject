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

public class ReviewActivity extends AppCompatActivity {


    private TextView mReviewText;
    private Button mRewviewButton;

    //Test
    private Button mRatingButton;
    private RatingBar mRatingBar;

    String reviewText;
    long reviewID = 0;

    long reviewScore;

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
        final DatabaseReference beerratingRef1 = beerRatingRef.child(data).child("mRating");


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

        /*m##RatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beerratingRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/
            mRatingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    beerratingRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            });





    }
}




