package mark.harrison.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class homeScreen extends AppCompatActivity {
    private FirebaseAuth mAuth;

    Button mBeerButton;
    Button mBeerButtonTwo;
    Button mBeerButtonThree;
    Button mBeerButtonFour;
    Button mSignOutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mBeerButton = (Button)findViewById(R.id.beerInfoButton2);
        mBeerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),allBeers.class);
                view.getContext().startActivity(intent);
            }
        });

        mBeerButtonTwo = (Button)findViewById(R.id.createBeerButton);
        mBeerButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),createBeer.class);
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

    mBeerButtonFour = (Button)findViewById(R.id.allbeers);
    mBeerButtonFour.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(),beerInformation.class);
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
