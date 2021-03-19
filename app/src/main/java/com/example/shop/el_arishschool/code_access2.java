package com.example.shop.el_arishschool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class code_access2 extends AppCompatActivity {
     private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_access2);

        //Tool Bar
        toolbar=(android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Access page");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
