package com.example.shop.el_arishschool;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class code_access extends Activity{

    //define variables
    String name,result;
    ProgressDialog loading;
    EditText passwordEditText;
    TextView name2_tv,result2_tv;
    ListView listView;
    SimpleAdapter adapter;
    private  static String userpassword1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        setContentView(R.layout.activity_code_access);



        //define views
        //Text View
        name2_tv=(TextView)findViewById(R.id.tv_name2);
        result2_tv=(TextView)findViewById(R.id.tv_result2);
        passwordEditText = (EditText) findViewById(R.id.et_pass);


        //Button
        Button sumbitButton = (Button) findViewById(R.id.bu_checkpass);


        sumbitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                userpassword1 =passwordEditText.getText().toString(); // Assigning a value;
                Intent coodeToresult=new Intent(code_access.this,student_result.class);
                startActivity(coodeToresult);

            }
        });
    }

    public static String getVariable()
    {
        return userpassword1;
    }

}

