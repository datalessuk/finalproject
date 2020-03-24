package mark.harrison.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class topRatedActivity extends AppCompatActivity {

    private ListView mTopRatedList;
    ArrayList<String> mAllBeers = new ArrayList<>();
    DatabaseReference Reference = FirebaseDatabase.getInstance().getReference().child("Beers");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_rated);

        mTopRatedList =(ListView)findViewById(R.id.topRatedList);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(topRatedActivity.this,android.R.layout.simple_list_item_1,mAllBeers);
        mTopRatedList.setAdapter(arrayAdapter);
        arrayAdapter.clear();

        Reference.orderByChild("mRating").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //arrayAdapter.add(snapshot.getValue().toString());
                    Beers beers = snapshot.getValue(Beers.class);
                    String beerClass = beers.getmName();
                    Float beerRating = beers.getmRating();


                    arrayAdapter.add(beerClass + " User rating is " + beerRating);
                }
                Collections.reverse(mAllBeers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();

            }
        });


    }
}
