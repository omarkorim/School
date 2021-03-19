package com.example.shop.el_arishschool;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class studentdetails extends AppCompatActivity  {

    //define variables
    TextView name_tv,num_tv, status_tv,religion_tv,nationality_tv,turnto_tv,birthday_tv,result_tv,from_tv,branch_tv,sitnum_tv,nationalityid_tv;
    Button buttonUpdateItem, buttonDeleteItem;
    String name,num,status,religion,nationality,turnto,birthday,result,from,branch,sitnum,nationalityid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.itemdetails);

        //get values from data base
        Intent intent = getIntent();
        num= intent.getStringExtra("num");
        name= intent.getStringExtra("name");
        status = intent.getStringExtra("status");
        religion = intent.getStringExtra("religion");
        nationality = intent.getStringExtra("nationality");
        turnto = intent.getStringExtra("turnto");
        birthday = intent.getStringExtra("birthday");
        result = intent.getStringExtra("result");
        from = intent.getStringExtra("from");
        branch = intent.getStringExtra("branch");
        sitnum = intent.getStringExtra("sitnum");
        nationalityid = intent.getStringExtra("nationalityid");

        //define views

        //Edit Text
        num_tv= (TextView)findViewById(R.id.tv_id);
        name_tv= (TextView)findViewById(R.id.tv_name);
        status_tv= (TextView)findViewById(R.id.tv_status);
        religion_tv= (TextView)findViewById(R.id.tv_religon);
        nationality_tv= (TextView)findViewById(R.id.tv_nationality);
        turnto_tv= (TextView)findViewById(R.id.tv_turnto);
        birthday_tv= (TextView)findViewById(R.id.tv_birthday);
        result_tv= (TextView)findViewById(R.id.tv_result);
        from_tv= (TextView)findViewById(R.id.tv_from);
        branch_tv= (TextView)findViewById(R.id.tv_branch);
        sitnum_tv= (TextView)findViewById(R.id.tv_sitnum);
        nationalityid_tv= (TextView)findViewById(R.id.tv_nationalityid);

        //set value in text view
        num_tv.setText(num);
        name_tv.setText(name);
        status_tv.setText(status);
        religion_tv.setText(religion);
        nationality_tv.setText(nationality);
        turnto_tv.setText(turnto);
        birthday_tv.setText(birthday);
        result_tv.setText(result);
        from_tv.setText(from);
        branch_tv.setText(branch);
        sitnum_tv.setText(sitnum);
        nationalityid_tv.setText(nationalityid);
    }
}