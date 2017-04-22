package com.example.tanmay.vitringtonecontroller.Entity.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tanmay.vitringtonecontroller.Boundary.API.APIConstants;
import com.example.tanmay.vitringtonecontroller.Entity.Actors.LoginModel;
import com.example.tanmay.vitringtonecontroller.Entity.Home.TopLevelActivity;
import com.example.tanmay.vitringtonecontroller.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText registrationNo,pwd;
    TextView view;
    Button login;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        registrationNo = (EditText) findViewById(R.id.regisno);
        pwd = (EditText) findViewById(R.id.pwd);
        view = (TextView) findViewById(R.id.view);
        login = (Button) findViewById(R.id.login);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showProgressDialog();
                String url= APIConstants.LOGIN_URL;
                Log.v("Before Volley request","Test1 ");
                final StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        LoginModel loginModel=new Gson().fromJson(response, LoginModel.class);
                        if(loginModel.getStatus().getMessage().equals("Success"))
                        {
                            Intent TopLevelActivtyIntent=new Intent(getApplicationContext(), TopLevelActivity.class);
                            TopLevelActivtyIntent.putExtra("regNo",registrationNo.getText().toString());
                            TopLevelActivtyIntent.putExtra("psswd",pwd.getText().toString());
                            startActivity(TopLevelActivtyIntent);
                        }
                        else if(loginModel.getStatus().getMessage().equals("Invalid Credentials"))
                        {
                            Toast.makeText(getApplicationContext(),"Invalid Credential",Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Enter the credentials first",Toast.LENGTH_SHORT).show();
                        //hideProgressDialog();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Error Occurred",Toast.LENGTH_SHORT).show();
                        hideProgressDialog();
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

    void showProgressDialog(){
        if(mProgressDialog==null){
            mProgressDialog=new ProgressDialog(getApplicationContext());
            mProgressDialog.setMessage("Loading");
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    private void hideProgressDialog(){
        if(mProgressDialog!=null && mProgressDialog.isShowing()){
            mProgressDialog.hide();
        }
    }

}
