package com.example.stealdeal.stealdeal;

import android.content.Intent;
import android.database.DataSetObserver;
import android.location.Location;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private SearchView searchView;
    public static ArrayList<Offer> offerList;
    public static ArrayList<Offer> offers;
    private MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        offers = new ArrayList<>();
        listView = findViewById(R.id.mainScreenListView);
        searchView = findViewById(R.id.mainScreenSearchView);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("offers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                offers = new ArrayList<>();
                for (DataSnapshot offerDataSnapshot : dataSnapshot.getChildren()){
                    Offer offer = offerDataSnapshot.getValue(Offer.class);
                    offers.add(offer);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        for (Offer offer : offers){
            Log.d("offers", offer.getName());
        }

        //TODO creat real Offers
        String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };

        offerList = new ArrayList<Offer>();
        for(int i = 0; i <= values.length - 1; i++){
        //    offerList.add(new Offer(i, values[i], new Location(""), "dummy"));
        }

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);#
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.mainscreenlistview_item, R.id.caption, values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

                Intent intent = new Intent(mainActivity, OfferDetails.class);
                //intent.putExtra("Offer", offerList.get(itemPosition));
                intent.putExtra("offerID", itemPosition);
                startActivity(intent);
            }
        });

    FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent myIntent = new Intent(MainActivity.this, AddOfferActivity.class);
            // myIntent.putExtra("key", value); //Optional parameters
            MainActivity.this.startActivity(myIntent);
            //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //       .setAction("Action", null).show();
        }

        });
}
}
