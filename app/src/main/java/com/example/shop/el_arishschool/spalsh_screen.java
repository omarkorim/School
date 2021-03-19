package com.example.shop.el_arishschool;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class spalsh_screen extends AppCompatActivity {

    public static int splash_time_out=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashtomain = new Intent(spalsh_screen.this,MainActivity.class);
                startActivity(splashtomain);
                finish();
            }
        },splash_time_out);

    }
}
