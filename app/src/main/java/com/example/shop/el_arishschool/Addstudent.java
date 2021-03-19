package com.example.shop.el_arishschool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Addstudent extends AppCompatActivity implements View.OnClickListener {

    //define variables
    EditText num_et,name_et,status_et,religion_et,nationality_et,turnto_et,birthday_et,result_et,from_et,branch_et,sitnum_et,nationalityid_et;
    Button addstudent_bu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info_student);

        //define View

        //Edit Text
        num_et = (EditText)findViewById(R.id.et_numberofstudent);
        name_et = (EditText)findViewById(R.id.et_name);
        status_et = (EditText)findViewById(R.id.et_status);
        religion_et = (EditText)findViewById(R.id.et_religon);
        nationality_et = (EditText)findViewById(R.id.et_nationality);
        turnto_et = (EditText)findViewById(R.id.et_schoolto);
        birthday_et = (EditText)findViewById(R.id.et_birthday);
        result_et = (EditText)findViewById(R.id.et_result);
        from_et = (EditText)findViewById(R.id.et_fromschool);
        branch_et = (EditText)findViewById(R.id.et_branch);
        sitnum_et = (EditText)findViewById(R.id.et_setnum);
        nationalityid_et = (EditText)findViewById(R.id.et_nationalitynum);

        //Buttons
        addstudent_bu = (Button)findViewById(R.id.bu_addstudent);
        addstudent_bu.setOnClickListener(this);

    }

    //This is the part where data is transafeered from Your Android phone to Sheet by using HTTP Rest API calls
    private void   addItemToSheet() {
        final ProgressDialog loading = ProgressDialog.show(this,"اضافة طالب","بالرجاء الأنتظار");
        final String num = num_et.getText().toString().trim();
        final String name = name_et.getText().toString().trim();
        final String status = status_et.getText().toString().trim();
        final String religion = religion_et.getText().toString().trim();
        final String nationality = nationality_et.getText().toString().trim();
        final String turnto = turnto_et.getText().toString().trim();
        final String birthday = birthday_et.getText().toString().trim();
        final String result = result_et.getText().toString().trim();
        final String from = from_et.getText().toString().trim();
        final String branch = branch_et.getText().toString().trim();
        final String sitnum = sitnum_et.getText().toString().trim();
        final String nationalityid = nationalityid_et.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbymF6H5Xj-5shVnZ-ISQaoOoTB6cKmn1reQm1JZrKNr_Ayc248/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(Addstudent.this,response,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params
                parmas.put("action","addstudent");
                parmas.put("num",num);
                parmas.put("name",name);
                parmas.put("status",status);
                parmas.put("religion",religion);
                parmas.put("nationality",nationality);
                parmas.put("turnto",turnto);
                parmas.put("birthday",birthday);
                parmas.put("result",result);
                parmas.put("from",from);
                parmas.put("branch",branch);
                parmas.put("sitnum",sitnum);
                parmas.put("nationalityid",nationalityid);


                return parmas;
            }
        };

        //if no internet conection
        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    //Define what to do when button is clicked
    @Override
    public void onClick(View v) {

        if(v==addstudent_bu){
            addItemToSheet();
        }
    }
}
