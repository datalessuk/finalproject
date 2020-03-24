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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class inStockActivity extends AppCompatActivity {

    ArrayList<String> mAllBeers = new ArrayList<>();
    private ListView mBeerList;

    private TextView mBeerNameText;
    private TextView mBeerStockText;
    private Button mStockButton;

    String beerClassName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_stock);
        mBeerList = (ListView)findViewById(R.id.allBeersListView);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mAllBeers);
        mBeerList.setAdapter(arrayAdapter);
        arrayAdapter.clear();
        //For the details of the beer
        final DatabaseReference zonesRef = FirebaseDatabase.getInstance().getReference("Beers");
        //DatabaseReference zone1Ref = zonesRef.child(data);
        //For the List view of all beers
        DatabaseReference Reference = FirebaseDatabase.getInstance().getReference().child("Beers");
        //For the Shops database
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("Shop");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();




        final Intent intent = this.getIntent();
        final String data = intent.getStringExtra("Name");



        //For the beers that the selected shop has
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        final DatabaseReference myRef1 = database.getReference("Shop").child(data);
        //For the Stock checker
        final DatabaseReference myRefRating = database.getReference("Shop").child(data);
        //Text Fields
        mBeerNameText = (TextView)findViewById(R.id.beerNameText);
        mBeerStockText = (TextView)findViewById(R.id.beerInStockText);

        //Button
        mStockButton = (Button)findViewById(R.id.StockButton);



        //Values for the stock checker
        String mBeerNames;
        Boolean mInStock;

        myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayAdapter.clear();
                for(DataSnapshot ds :dataSnapshot.getChildren()){
                    Shops shops = ds.getValue(Shops.class);
                    String eachBeer = shops.getmBeerName();
                    arrayAdapter.add(eachBeer);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();

            }
        });
        mBeerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                int itemPosition = position;

                String  itemValue = (String) mBeerList.getItemAtPosition(position);
                final DatabaseReference myRefRating = database.getReference("Shop").child(data).child(itemValue);

                myRefRating.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Shops shops = dataSnapshot.getValue(Shops.class);
                        String mBeername = shops.getmBeerName();
                        mBeerNameText.setText(mBeername);

                        //Stock output
                        Boolean mInStock = shops.getmInStock();

                        if(mInStock ==false){
                            mBeerStockText.setText("Out of stock");
                        }else {
                            mBeerStockText.setText("In Stock");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        throw databaseError.toException();

                    }
                });

            }
        });

        mStockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(),stockControlActivity.class);
                intent1.putExtra("ShopName", data);
                view.getContext().startActivity(intent1);
            }
        });
    }
}
