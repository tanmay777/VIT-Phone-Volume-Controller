package com.example.tanmay.vitringtonecontroller.Entity.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tanmay.vitringtonecontroller.Boundary.API.APIConstants;
import com.example.tanmay.vitringtonecontroller.Entity.Actors.LoginDetailsModel;
import com.example.tanmay.vitringtonecontroller.Entity.Home.TopLevelActivity;
import com.example.tanmay.vitringtonecontroller.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText registrationNo,pwd;
    TextView view;
    Button login;
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        registrationNo=(EditText)findViewById(R.id.regisno);
        pwd=(EditText)findViewById(R.id.pwd);
        view=(TextView)findViewById(R.id.view);
        login=(Button)findViewById(R.id.login);

        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson=gsonBuilder.create();
    }

    @Override
    public void onStart()
    {
        super.onStart();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url= APIConstants.LOGIN_URL;
                Log.v("Before Volley request","Test1 ");
                final StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        view.setText(response);
                        LoginDetailsModel loginDetailsModel=gson.fromJson(response, LoginDetailsModel.class);
                        Log.v("Response",response);
                        Log.v("In Response","Test1 ");
                        Log.v("Response",loginDetailsModel.getStatus().getMessage());
                        if(loginDetailsModel.getStatus().getMessage().equals("Success"))
                        {
                            Intent TopLevelActivtyIntent=new Intent(getApplicationContext(), TopLevelActivity.class);
                            TopLevelActivtyIntent.putExtra("regNo",registrationNo.getText().toString());
                            TopLevelActivtyIntent.putExtra("psswd",pwd.getText().toString());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }){
                    @Override
                    protected Map<String,String> getParams()
                    {
                        Map<String, String>params = new HashMap<>();
                        params.put("regNo",registrationNo.getText().toString());
                        params.put("psswd",pwd.getText().toString());
                        return params;
                    }
                };
                Volley.newRequestQueue(getApplicationContext()).add(request);
            }
        });

    }

}
