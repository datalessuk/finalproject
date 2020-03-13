package mark.harrison.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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


    Button mBeerButton;
    Button mBeerButtonTwo;
    Button mBeerButtonThree;
    Button mBeerButtonFour;
    Button mSignOutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        String mUserID = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Users");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String mUsername = dataSnapshot.child("mUserName").getValue().toString();
                Toast.makeText(homeScreen.this,"User name is "+ mUsername,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //String name = user.getUid();
        //DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("mUserName");

        //Toast.makeText(homeScreen.this,": " +name,Toast.LENGTH_SHORT).show();
        //FirebaseUser user = FirebaseAuth.getInstance();

        /*FirebaseAuth mAuth = FirebaseAuth.getInstance();

        zonesRef.child(mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("mUserName").getValue(String.class);
                Toast.makeText(homeScreen.this,"you are:" + name,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        //Toast.makeText(homeScreen.this,"is " + userid,Toast.LENGTH_LONG).show();

        /*ref.child(mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String data = dataSnapshot.child("mUserName").getValue(String.class);
                //Toast.makeText(homeScreen.this,"is " + data,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        //Toast.makeText(homeScreen.this,"is:" + user,Toast.LENGTH_LONG).show();



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
