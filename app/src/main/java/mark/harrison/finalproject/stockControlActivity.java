package mark.harrison.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class stockControlActivity extends AppCompatActivity {


    // List of all beers
    //When one is pressed Add to the Shop database
    // The location shop will be the beers ins stock at that shop only
    // when you press that beer it will say in stock or not if not you can check in and say in stock yes or no

    private String itemValue;

    private ListView mBeerList;
    private Button mCheckInButton;
    private Switch mInStockSwitch;
    private TextView mShopName;
    ArrayList<String> mAllBeers = new ArrayList<>();

    //Shop Name
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Shops");




    final DatabaseReference zonesRef = FirebaseDatabase.getInstance().getReference("Beers");
    DatabaseReference Reference = FirebaseDatabase.getInstance().getReference().child("Beers");

    String beerClassName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_control);
        //Switch
        mInStockSwitch = (Switch)findViewById(R.id.InStockswitch);
        mCheckInButton = (Button)findViewById(R.id.checkInButton);
        mShopName =(TextView)findViewById(R.id.shopNameText);

        //Getting Shop name
        final Intent intent = this.getIntent();
        final String data = intent.getStringExtra("ShopName");

        //Making the shop Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Shop").child(data);


        mBeerList = (ListView)findViewById(R.id.allBeersList);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mAllBeers);
        mBeerList.setAdapter(arrayAdapter);
        arrayAdapter.clear();
        //List of all beers

        //Shop Name Text Setter
        mShopName.setText(data.toString());

        Reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayAdapter.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //arrayAdapter.add(snapshot.getValue().toString());
                    Beers beers = snapshot.getValue(Beers.class);
                    beerClassName = beers.getmName();
                    arrayAdapter.add(beerClassName);

                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();

            }
        });
        mBeerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posisiton, long l) {

                int itemPosition     = posisiton;
                view.getFocusables(posisiton);
                view.setSelected(true);

                itemValue = (String) mBeerList.getItemAtPosition(posisiton);


            }
        });
        mCheckInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Boolean mInStock = false;
                        if(mInStockSwitch.isChecked()){
                            mInStock = true;
                        }
                        else{
                            mInStock = false;
                        }
                        Shops shops = new Shops(itemValue,mInStock);
                        myRef.child(itemValue).setValue(shops);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        throw databaseError.toException();
                    }
                });
                Intent intent = new Intent(stockControlActivity.this,homeScreen.class);
                view.getContext().startActivity(intent);

            }
        });



    }
}
