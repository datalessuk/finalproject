package mark.harrison.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homeScreen extends AppCompatActivity {

    Button mBeerButton;
    Button mBeerButtonTwo;
    Button mBeerButtonThree;
    Button mBeerButtonFour;
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
    }




}
