
package com.example.shop.el_arishschool;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shop.el_arishschool.R;

public class studentresult extends AppCompatActivity  {

    //define variables
    TextView name2_tv,result2_tv;
    String name,result,pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.itemdetails);

        //get values from data base
        Intent intent = getIntent();
        name= intent.getStringExtra("name");
        result= intent.getStringExtra("result");


        //define views

        //Edit Text
        name2_tv= (TextView)findViewById(R.id.tv_id);
        result2_tv= (TextView)findViewById(R.id.tv_name);


        //set value in text view
        name2_tv.setText(name);
        result2_tv.setText(result);

    }
}