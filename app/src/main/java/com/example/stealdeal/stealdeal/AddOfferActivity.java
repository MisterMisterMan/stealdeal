package com.example.stealdeal.stealdeal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by bandana on 26.11.17.
 */

public class AddOfferActivity extends AppCompatActivity{

    private EditText offerTitle;
    private EditText offerPrice;
    private EditText offerDescription;
    private EditText distance;
    private Button addbutton;
    private DatabaseReference mFirebaseDatabaseReference;


    public static final String OFFERS_CHILD = "offers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer);
        offerTitle = findViewById(R.id.editText2);
        offerPrice = findViewById(R.id.editText6);
        offerDescription = findViewById(R.id.editText5);
        distance = findViewById(R.id.editText8);

        addbutton = (Button) findViewById(R.id.button2);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Offer offer = new Offer(offerTitle.getText().toString(), offerPrice.getText().toString(),
               //         offerDescription.getText().toString(), distance.getText().toString());
               // mFirebaseDatabaseReference.child(OFFERS_CHILD).push().setValue(offer);
            }
        });
    }
}