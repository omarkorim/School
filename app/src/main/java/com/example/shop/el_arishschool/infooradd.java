package com.example.shop.el_arishschool;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class infooradd extends AppCompatActivity implements View.OnClickListener{
    private CardView card3view11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infooradd);


        card3view11 = (CardView)findViewById(R.id.card3_view11);
        card3view11.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent x ;

        switch (v.getId()) {
            case R.id.card3_view11 : x = new Intent(getApplicationContext(),searchanddisplay.class);
                startActivity(x);break;
        }
    }
}
