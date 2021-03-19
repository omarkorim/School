package com.example.shop.el_arishschool;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private GridLayout gridLayout;
    private CardView cardview12;
    CardView cardview32;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      cardview12 = (CardView)findViewById(R.id.card_view12);
      cardview12.setOnClickListener(this);

        cardview32 = (CardView)findViewById(R.id.card_view32);
        cardview32.setOnClickListener(this);


    }

   @Override
    public void onClick(View v) {
        Intent i ;

        switch (v.getId()) {
            case R.id.card_view12 : i = new Intent(getApplicationContext(),infooradd.class);
                startActivity(i);break;

            case R.id.card_view32 : i = new Intent(getApplicationContext(),code_access.class);
                startActivity(i);break;
        }
        }
}
