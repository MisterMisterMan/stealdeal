package com.example.stealdeal.stealdeal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by bandana on 26.11.17.
 */

public class AddOfferActivity extends AppCompatActivity{

    private EditText offerTitle;
    private EditText offerPrice;
    private EditText offerDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer);
        offerTitle = findViewById(R.id.editText2);
        offerPrice = findViewById(R.id.editText6);
        offerDescription = findViewById(R.id.editText5);

    }

}