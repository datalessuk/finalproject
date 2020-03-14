package mark.harrison.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class homeScreen extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

    private String mUserName;

    Button mBeerButton;
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
    }





}
