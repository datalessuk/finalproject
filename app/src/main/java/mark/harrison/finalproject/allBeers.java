package mark.harrison.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class allBeers extends AppCompatActivity {


    private ListView listView;


    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<String> mAllBeers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_beers);

        //database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("Beers");
        DatabaseReference Reference = FirebaseDatabase.getInstance().getReference().child("Beers");

        listView =(ListView)findViewById(R.id.allBeersList);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mAllBeers);
        listView.setAdapter(arrayAdapter);
        //arrayAdapter.clear();

        Reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //arrayAdapter.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                //arrayAdapter.add(snapshot.getValue().toString());
                    Beers beers = snapshot.getValue(Beers.class);
                    String txt = beers.getmBarcode() + " : " + beers.getmBrewery() + " : " + beers.getmName();

                    arrayAdapter.add(txt);
                }
            arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
