package mark.harrison.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import org.w3c.dom.Text;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class allBeers extends AppCompatActivity {


    private ListView listView;
    private ListView mListView;

    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<String> mAllBeers = new ArrayList<>();
    String beerCode;

    String hello;

    List listA = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_beers);
        mListView =(ListView) findViewById(R.id.allBeersList);

        DatabaseReference Reference = FirebaseDatabase.getInstance().getReference().child("Beers");
        //DatabaseReference zone1Ref = Reference.child(beerCode);

        listView =(ListView)findViewById(R.id.allBeersList);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mAllBeers);
        listView.setAdapter(arrayAdapter);
        arrayAdapter.clear();



        Reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //arrayAdapter.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //arrayAdapter.add(snapshot.getValue().toString());
                    Beers beers = snapshot.getValue(Beers.class);
                    String beerClass = beers.getmBrewery() + "  " + beers.getmName();

                    arrayAdapter.add(beerClass);



                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                Intent intent = new Intent(allBeers.this, beerInformation.class);
                intent.putExtra("Name", itemValue);
                startActivity(intent);





                // Show Toast
                Toast.makeText(getApplicationContext(), "Position:"+itemPosition+"  Item Clicked: " +itemValue , Toast.LENGTH_LONG).show();

                //Send item value over !




            }
        });

    }
}
