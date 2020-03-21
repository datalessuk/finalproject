package mark.harrison.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class homeScreen extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users"); // Database for users
    //For the beer Search
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Beers");
    //End of database reffs

    private String mUserName;
    private TextView mSearchBar;
    private String mSeachResult;

    Button mLocationButton;
    Button mBeerButtonTwo;
    Button mBeerButtonThree;
    Button mBeerButtonFour;
    Button mSignOutButton;
    private TextView mWelcomeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mWelcomeText = (TextView)findViewById(R.id.welcomeText);
        mSearchBar = (TextView)findViewById(R.id.searchBar);
        String mUserID = firebaseAuth.getCurrentUser().getUid(); // Get the user id



        ref.child(mUserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUserName = dataSnapshot.child("mUserName").getValue().toString();
                //Toast.makeText(homeScreen.this,"User name is "+ mUserName,Toast.LENGTH_SHORT).show();
                mWelcomeText.setText("Welcome Back " +mUserName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mLocationButton = (Button)findViewById(R.id.locationButton);
        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),locationActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        mBeerButtonThree = (Button)findViewById(R.id.cameraButton);
        mBeerButtonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),cameraActivity.class);
                view.getContext().startActivity(intent);
            }
        });

    mBeerButtonFour = (Button)findViewById(R.id.beerInfoButton2);
    mBeerButtonFour.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(),allBeers.class);
            view.getContext().startActivity(intent);
        }
    });
    mSignOutButton = (Button)findViewById(R.id.signOutbutton);
    mSignOutButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

                mAuth.getInstance().signOut();
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                view.getContext().startActivity(intent);

            }




    });



        mSearchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                mSeachResult = mSearchBar.getText().toString();
                Query query = ref.child("Beers").equalTo(mSeachResult);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            Beers beers = ds.getValue(Beers.class);
                            String beerClass = beers.getmBrewery();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                return false;
            }
        });

    }


}
