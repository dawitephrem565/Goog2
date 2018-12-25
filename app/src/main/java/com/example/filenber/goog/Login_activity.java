package com.example.filenber.goog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by filenber on 02/12/2018.
 */

public class Login_activity extends AppCompatActivity {
    Button login_btn ;
    EditText autoCompleteTextView;
    EditText phone;
    SharedPrefManager sharedPrefManager;
    ProgressDialog progressDialog;
    public static final String URL_LOGIN = "http://192.168.137.1/gogo/login.php";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        login_btn = (Button)findViewById(R.id.login_btn);
        autoCompleteTextView = (EditText)findViewById(R.id.login_country_auto_text);
        phone=(EditText)findViewById(R.id.login_phone_number);
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, bottomnavigation.class));
            return;
        }
    progressDialog = new ProgressDialog(this);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
userLogin();
            }
        });

    }
    private void userLogin(){
        final String country = autoCompleteTextView.getText().toString();
        final String phonelogin = phone.getText().toString();

        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){
                                SharedPrefManager.getInstance(getApplicationContext())
                                        .userLogin(

                                                obj.getString("username"),
                                                obj.getString("phone"),
                                                obj.getString("id")

                                        );


//                                Toast.makeText(getBaseContext(), sharedPrefManager.getUsername().toString(),Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), bottomnavigation.class));
                                finish();
                            }else{
                                Toast.makeText(
                                        getApplicationContext(),
                                       phonelogin.toString(),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(
                                getApplicationContext(),
                                "eroro happning",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", country);
                params.put("phone", phonelogin);
                return params;
            }

        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}
