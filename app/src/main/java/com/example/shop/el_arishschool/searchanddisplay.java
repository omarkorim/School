package com.example.shop.el_arishschool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class searchanddisplay extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //define variables
    ListView listView;
    SimpleAdapter adapter;
    ProgressDialog loading;
    EditText search_lv_et;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.li_student_info);

        //define views
        listView = (ListView) findViewById(R.id.list_view_student);
        listView.setOnItemClickListener(this);
        search_lv_et=(EditText)findViewById(R.id.et_lv_search);

        //function get items
        getItems();
    }

    //This is the part where data is transafeered from Your Android phone to Sheet by using HTTP Rest API calls
    private void getItems() {
        loading =  ProgressDialog.show(this,"الرجاء الانتظار","جاري تحميل قاعدة بيانات الطلاب",false,true);
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
            JSONObject jobj = new JSONObject(jsonResposnce);
            JSONArray jarray = jobj.getJSONArray("items");

            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jo = jarray.getJSONObject(i);

                //here i got the value from my data base to display it
                String num = jo.getString("num");
                String name = jo.getString("name");
                String status = jo.getString("status");
                String religion = jo.getString("religion");
                String nationality = jo.getString("nationality");
                String turnto = jo.getString("turnto");
                String birthday = jo.getString("birthday");
                String result = jo.getString("result");
                String from = jo.getString("from");
                String branch = jo.getString("branch");
                String sitnum = jo.getString("sitnum");
                String nationalityid = jo.getString("nationalityid");

                //here we pass params by using hashma
                HashMap<String, String> item = new HashMap<>();
                item.put("num",num);
                item.put("name",name);
                item.put("status",status);
                item.put("religion",religion);
                item.put("nationality",nationality);
                item.put("turnto",turnto);
                item.put("birthday",birthday);
                item.put("result",result);
                item.put("from",from);
                item.put("branch",branch);
                item.put("sitnum",sitnum);
                item.put("nationalityid",nationalityid);

                if(sitnum.equals("301")) {
                    list.add(item);
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(searchanddisplay.this,"الرجاء التحقق من الأتصال بالأنترنت!",Toast.LENGTH_LONG).show();
        }

        //to display data in thier own text view depends in adapter
        adapter = new SimpleAdapter(this,list,R.layout.lir_student_info
                ,new String[]{"num","name","status","religion","nationality","turnto","birthday","result","from","branch","sitnum","nationalityid"}
                ,new int[]{R.id.tv_lv_num,R.id.tv_lv_name});

        listView.setAdapter(adapter);
        loading.dismiss();

        //for search part it depends on filter
        search_lv_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchanddisplay.this.adapter.getFilter().filter(s);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    //here to show data of specific student in student detalils activity
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //here to move from this activity to student details activity
        Intent intent = new Intent(this, studentdetails.class);
        // store data in hash map
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String num = map.get("num").toString();
        String name = map.get("name").toString();
        String status = map.get("status").toString();
        String religion = map.get("religion").toString();
        String nationality = map.get("nationality").toString();
        String turnto = map.get("turnto").toString();
        String birthday = map.get("birthday").toString();
        String result = map.get("result").toString();
        String from = map.get("from").toString();
        String branch = map.get("branch").toString();
        String sitnum = map.get("sitnum").toString();
        String nationalityid = map.get("nationalityid").toString();

        //to pass data from activity to another
        intent.putExtra("num",num);
        intent.putExtra("name",name);
        intent.putExtra("status",status);
        intent.putExtra("religion",religion);
        intent.putExtra("nationality",nationality);
        intent.putExtra("turnto",turnto);
        intent.putExtra("birthday",birthday);
        intent.putExtra("result",result);
        intent.putExtra("from",from);
        intent.putExtra("branch",branch);
        intent.putExtra("sitnum",sitnum);
        intent.putExtra("nationalityid",nationalityid);


        startActivity(intent);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
