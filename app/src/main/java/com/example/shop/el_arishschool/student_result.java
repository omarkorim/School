
package com.example.shop.el_arishschool;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop.el_arishschool.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class student_result extends AppCompatActivity  {

    //define variables
    //define variables
    String name,result;
    ProgressDialog loading;
    EditText passwordEditText;
    TextView name2_tv,result2_tv;
    ListView listView;
    SimpleAdapter adapter;

    String userinputpass=code_access.getVariable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        setContentView(R.layout.li_student_result);
        listView = (ListView) findViewById(R.id.list_view_result);
      getresult();
    }

    //This is the part where data is transafeered from Your Android phone to Sheet by using HTTP Rest API calls
    private void getresult() {
        loading =  ProgressDialog.show(this,"الرجاء الانتظار","جاري تحميل نتيجة الطالب",false,true);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbymF6H5Xj-5shVnZ-ISQaoOoTB6cKmn1reQm1JZrKNr_Ayc248/exec?action=getItems",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseItems(response); }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { }
        });

        //check internet cnnection
        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest); }

    // here we get values from data base

    private void parseItems(String jsonResposnce) {


    ArrayList<HashMap<String, String>> list = new ArrayList<>();

        try {
            Toast.makeText(student_result.this,userinputpass, Toast.LENGTH_LONG).show();
            JSONObject jobj1 = new JSONObject(jsonResposnce);
            JSONArray jarray1 = jobj1.getJSONArray("items");

            for (int i = 0; i < jarray1.length(); i++) {
                JSONObject jo = jarray1.getJSONObject(i);

                //here i got the value from my data base to display it
                String name = jo.getString("name");
                String result = jo.getString("result");
                String pass = jo.getString("pass");

                //here we pass params by using hashma
                HashMap<String, String> item2 = new HashMap<>();
                item2.put("name", name);
                item2.put("result", result);
                item2.put("pass",pass);

                if(pass.equals(userinputpass)) {
                    list.add(item2);
                    break;
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(student_result.this,"الرجاء التحقق من الأتصال بالأنترنت!", Toast.LENGTH_LONG).show();
        }

        //to display data in thier own text view depends in adapter
        adapter = new SimpleAdapter(this,list,R.layout.lir_student_result
                ,new String[]{"name","result"}
                ,new int[]{R.id.tv2_lv_num,R.id.tv2_lv_name});
        listView.setAdapter(adapter);
        loading.dismiss();


    }


}