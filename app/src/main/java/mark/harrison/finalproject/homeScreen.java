package mark.harrison.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class homeScreen extends AppCompatActivity {

    Button mBeerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mBeerButton = (Button)findViewById(R.id.beerInfoButton);
        mBeerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),beerInformation.class);
                view.getContext().startActivity(intent);
            }
        });


    }




}
