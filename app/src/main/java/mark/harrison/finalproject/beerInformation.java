package mark.harrison.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
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
import java.util.Map;

public class beerInformation extends AppCompatActivity {

    //Database Reference
    //DatabaseReference Reference = FirebaseDatabase.getInstance().getReference().child("Beers");
    //DatabaseReference Reference = FirebaseDatabase.getInstance().getReference().child("Beers")
    //Textviews

     // Working



    TextView mBeername;
    TextView mBeerBrewery;
    TextView mBeerPercentage;
    TextView mBeerFlavours;

    Button mReviewButton;
    TextView mBeerReviewsTextview;
    RatingBar mRatingBar;

    private ListView mListView;
    int mRatingCount;
    float mRatingTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = this.getIntent();

        final String data = intent.getStringExtra("Name");
        //For the Beers
        DatabaseReference zonesRef = FirebaseDatabase.getInstance().getReference("Beers");
        DatabaseReference zone1Ref = zonesRef.child(data);

        //For the reviews
        DatabaseReference beerReviewsRef = FirebaseDatabase.getInstance().getReference("Reviews");
        DatabaseReference selectedReviewRef = beerReviewsRef.child(data);
        //final DatabaseReference childofreview = selectedReviewRef.getRoot();

        //For the Rating
        DatabaseReference beerRating = FirebaseDatabase.getInstance().getReference("Ratings");
        final DatabaseReference beerFinalRating = beerRating.child(data);




        setContentView(R.layout.activity_beer_information);

        mBeername = (TextView)findViewById(R.id.beerNameText);
        mBeerBrewery = (TextView) findViewById(R.id.beerBreweryText);
        mBeerPercentage = (TextView)findViewById(R.id.beerPercentageText);
        mBeerFlavours = (TextView)findViewById(R.id.beerFlavoursText);
        mListView = (ListView) findViewById(R.id.beerReviewsList);
        mRatingBar = (RatingBar)findViewById(R.id.finalRatingBar);
        ArrayList<String> mAllReviews = new ArrayList<>();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mAllReviews);
        mListView.setAdapter(arrayAdapter);
        arrayAdapter.clear();


        zone1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //for(DataSnapshot ds : dataSnapshot.getChildren()){
                    //Beers beers = ds.getValue(Beers.class);
                    //mBeername.setText(beers.getmName().toString());
                   //String name = ds.child("mName").getValue(String.class);
                    Beers beers = dataSnapshot.getValue(Beers.class);
                    String beerclass = beers.getmName();
                    String brewery = beers.getmBrewery();
                    String percentage = beers.getmPercentage();
                    String flavours =beers.getmFlavours();

                    mBeername.setText("Beer: "+beerclass);
                    mBeerBrewery.setText("Brewery: " +brewery);
                    mBeerPercentage.setText("Percentage " +percentage+"%");
                    mBeerFlavours.setText("Beer Flavours: "+flavours);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mReviewButton = (Button)findViewById(R.id.addReviewButton);
        mReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reviewIntent = new Intent(beerInformation.this, ReviewActivity.class);
                reviewIntent.putExtra("beerName", data);
                startActivity(reviewIntent);

            }
        });
        selectedReviewRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayAdapter.clear();// This clears the list when you go back after adding a review
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //arrayAdapter.add(snapshot.getValue().toString());
                    beerReviews mBeerReviews =snapshot.getValue(beerReviews.class);
                    String mBeers = mBeerReviews.getmReviews();
                    arrayAdapter.add(mBeers);


                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /*beerFinalRating.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    mRatingCount = (int) dataSnapshot.getChildrenCount();
                    //Toast.makeText(beerInformation.this,"total is: "+ mRatingCount,Toast.LENGTH_SHORT).show();
                }
                else {
                    //Toast.makeText(beerInformation.this,"Sorry",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

       /* beerFinalRating.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    mRatingCount = (int) dataSnapshot.getChildrenCount();
                    //Toast.makeText(beerInformation.this,"total is: "+ mRatingCount,Toast.LENGTH_SHORT).show();

                    Map<String,Object>  map = (Map<String, Object>) ds.getValue();
                    Object value = map.get("mBeerRatings");
                    float pValue = Float.parseFloat(String.valueOf(value));
                    mRatingTotal += pValue;
                    float finalrating= GetBeerRating(mRatingCount,mRatingTotal);
                    mRatingBar.setRating(finalrating);
                    Toast.makeText(beerInformation.this,"The total is : " + finalrating,Toast.LENGTH_SHORT).show();
                    //Toast.makeText(beerInformation.this,"The Total is: "+ mRatingTotal,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



    }
    public float GetBeerRating(int pCountTotal,float pRatingTotal ){
        float mRating = pRatingTotal / pCountTotal;
        return  mRating;

    }

}
